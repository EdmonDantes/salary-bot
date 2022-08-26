/*
 * Copyright (c) 2022. Ilia Loginov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.loginov.http

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.timeout
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod

class HttpClient(engineFactory: HttpClientEngineFactory<*> = CIO) {

    private val mapper: ObjectMapper = ObjectMapper()

    init {
        mapper.registerModule(
                KotlinModule.Builder()
                        .withReflectionCacheSize(1024)
                        .configure(KotlinFeature.NullToEmptyCollection, false)
                        .configure(KotlinFeature.NullToEmptyMap, false)
                        .configure(KotlinFeature.NullIsSameAsDefault, false)
                        .configure(KotlinFeature.SingletonSupport, false)
                        .configure(KotlinFeature.StrictNullChecks, true)
                        .build()
        )
    }

    private val client: HttpClient = HttpClient(engineFactory) {
        expectSuccess = false
        install(HttpTimeout)
    }

    suspend fun request(
            method: HttpMethod,
            url: String,
            body: ByteArray? = null,
            queryParameters: Map<String, String> = emptyMap(),
            contentType: ContentType? = null,
            headersUser: Map<String, String> = emptyMap(),
            connectionTimeout: Long? = null,
            requestTimeout: Long? = null
    ): HttpResponse =
            client.request {
                this.method = method

                this.url(url)

                if (queryParameters.isNotEmpty()) {
                    queryParameters.forEach {
                        parameter(it.key, it.value)
                    }
                }

                if (headersUser.isNotEmpty()) {
                    headers {
                        headersUser.forEach { (key, value) ->
                            if (key.isNotEmpty()) {
                                append(key, value)
                            }
                        }
                    }
                }

                if (body != null) {
                    this.body = CustomOutgoingContext(contentType, body)
                }

                timeout {
                    requestTimeoutMillis = requestTimeout ?: DEFAULT_REQUEST_TIMEOUT
                    connectTimeoutMillis = connectionTimeout ?: DEFAULT_CONNECTION_TIMEOUT
                    socketTimeoutMillis =
                            (requestTimeout ?: DEFAULT_REQUEST_TIMEOUT) + (connectionTimeout
                                    ?: DEFAULT_CONNECTION_TIMEOUT)
                }
            }


    suspend fun <T> requestWithJsonResponse(
            method: HttpMethod,
            url: String,
            responseTypeReference: TypeReference<T>,
            body: ByteArray? = null,
            queryParameters: Map<String, String> = emptyMap(),
            contentType: ContentType? = null,
            headersUser: Map<String, String> = emptyMap(),
            connectionTimeout: Long? = null,
            requestTimeout: Long? = null
    ): Pair<T?, HttpResponse> {
        val response = request(
                method,
                url,
                body,
                queryParameters,
                contentType,
                headersUser,
                connectionTimeout,
                requestTimeout
        )
        return mapper.readValue(response.readBytes(), responseTypeReference) to response
    }

    suspend fun requestWithJsonRequest(
            method: HttpMethod,
            url: String,
            body: Any? = null,
            queryParameters: Map<String, String> = emptyMap(),
            headersUser: Map<String, String> = emptyMap(),
            connectionTimeout: Long? = null,
            requestTimeout: Long? = null
    ): HttpResponse = request(
            method,
            url,
            body?.let { mapper.writeValueAsBytes(it) },
            queryParameters,
            ContentType.Application.Json,
            headersUser,
            connectionTimeout,
            requestTimeout
    )

    suspend fun <T> requestJson(
            method: HttpMethod,
            url: String,
            responseTypeReference: TypeReference<T>,
            body: Any? = null,
            queryParameters: Map<String, String> = emptyMap(),
            headersUser: Map<String, String> = emptyMap(),
            connectionTimeout: Long? = null,
            requestTimeout: Long? = null
    ): Pair<T?, HttpResponse> =
            requestWithJsonResponse(
                    method,
                    url,
                    responseTypeReference,
                    body?.let { mapper.writeValueAsBytes(it) },
                    queryParameters,
                    ContentType.Application.Json,
                    headersUser,
                    connectionTimeout,
                    requestTimeout
            )


    companion object {
        private val DEFAULT_CONNECTION_TIMEOUT: Long = 2_000L
        private val DEFAULT_REQUEST_TIMEOUT: Long = 10_000L
    }
}

suspend inline fun <reified T> ru.loginov.http.HttpClient.requestWithJsonResponse(
        method: HttpMethod,
        url: String,
        body: ByteArray? = null,
        queryParameters: Map<String, String> = emptyMap(),
        contentType: ContentType? = null,
        headersUser: Map<String, String> = emptyMap(),
        connectionTimeout: Long? = null,
        requestTimeout: Long? = null
): Pair<T?, HttpResponse> = this.requestWithJsonResponse(
        method,
        url,
        object : TypeReference<T>() {},
        body,
        queryParameters,
        contentType,
        headersUser,
        connectionTimeout,
        requestTimeout
)

suspend inline fun <reified T> ru.loginov.http.HttpClient.requestJson(
        method: HttpMethod,
        url: String,
        body: ByteArray? = null,
        queryParameters: Map<String, String> = emptyMap(),
        headersUser: Map<String, String> = emptyMap(),
        connectionTimeout: Long? = null,
        requestTimeout: Long? = null
): Pair<T?, HttpResponse> = this.requestJson(
        method,
        url,
        object : TypeReference<T>() {},
        body,
        queryParameters,
        headersUser,
        connectionTimeout,
        requestTimeout
)
