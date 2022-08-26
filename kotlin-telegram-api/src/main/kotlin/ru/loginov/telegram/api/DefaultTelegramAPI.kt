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

package ru.loginov.telegram.api

import com.fasterxml.jackson.core.type.TypeReference
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import ru.loginov.http.HttpClient
import ru.loginov.telegram.api.entity.BotCommand
import ru.loginov.telegram.api.entity.Message
import ru.loginov.telegram.api.entity.Update
import ru.loginov.telegram.api.entity.User
import ru.loginov.telegram.api.exception.ResponseErrorException
import ru.loginov.telegram.api.request.AnswerCallbackQueryRequest
import ru.loginov.telegram.api.request.DeleteMessageRequest
import ru.loginov.telegram.api.request.EditMessageReplyMarkupRequest
import ru.loginov.telegram.api.request.GetMyCommandsRequest
import ru.loginov.telegram.api.request.GetUpdatesRequest
import ru.loginov.telegram.api.request.SendMessageRequest
import ru.loginov.telegram.api.request.SetMyCommandsRequest
import ru.loginov.telegram.api.response.TelegramResponse
import kotlin.math.min

class DefaultTelegramAPI(
        private val client: HttpClient,
        private val baseUrl: String,
) : TelegramAPI {
    override suspend fun answerCallbackQuery(request: AnswerCallbackQueryRequest.() -> Unit) {
        client.requestJson<Boolean>(
                HttpMethod.Post,
                "answerCallbackQuery",
                AnswerCallbackQueryRequest().also(request)
        )
    }

    override suspend fun deleteMessage(request: DeleteMessageRequest.() -> Unit) {
        client.requestJson<Boolean>(HttpMethod.Post, "deleteMessage", DeleteMessageRequest().also(request))
    }

    override suspend fun editMessageReplyMarkup(request: EditMessageReplyMarkupRequest.() -> Unit): Message? =
            EditMessageReplyMarkupRequest().let {
                request(it)
                client.requestJson<Message>(HttpMethod.Post, "editMessageReplyMarkup", it)
            }


    override suspend fun getMyCommands(request: GetMyCommandsRequest.() -> Unit): List<BotCommand> =
            GetMyCommandsRequest().let {
                request(it)
                client.requestJson<List<BotCommand>>(HttpMethod.Post, "getMyCommands", it) ?: emptyList()
            }

    override suspend fun getMe(): User? =
            client.requestJson<User>(HttpMethod.Get, "getMe")

    override suspend fun getUpdates(request: GetUpdatesRequest.() -> Unit): List<Update> =
            GetUpdatesRequest().let {
                request(it)
                client.requestJson<List<Update>>(HttpMethod.Post, "getUpdates", it, (it.timeoutSec ?: 5) * 1000 * 5)
                        ?: emptyList()
            }

    override suspend fun setMyCommands(request: SetMyCommandsRequest.() -> Unit) {
        client.requestJson<Boolean>(HttpMethod.Post, "setMyCommands", SetMyCommandsRequest().also(request), 10000)
    }

    override suspend fun sendMessage(request: SendMessageRequest.() -> Unit): Message? =
            SendMessageRequest().let {
                request(it)
                client.requestJson<Message>(HttpMethod.Post, "sendMessage", it)
            }

    override suspend fun sendMessageWithoutLimit(request: SendMessageRequest.() -> Unit): List<Message> {
        val request = SendMessageRequest().also(request)

        if (request.text == null) {
            return client.requestJson<Message>(HttpMethod.Post, "sendMessage", request)?.let { listOf(it) }
                    ?: emptyList()
        }

        val result = ArrayList<Message>()
        var index = 0
        while (request.text!!.length - index > 0) {
            val msg =
                    sendMessage {
                        chatId = request.chatId
                        parseMode = request.parseMode
                        disableWebPagePreview = request.disableWebPagePreview
                        disableNotification = request.disableNotification
                        protectContent = request.protectContent
                        allowSendingWithoutReply = request.allowSendingWithoutReply
                        if (index == 0) {
                            replyToMessageId = request.replyToMessageId
                        }

                        val prepareText = request.text!!.substring(
                                index,
                                min(index + MAX_MESSAGE_TEXT_LENGTH, request.text!!.length)
                        )
                        text = if (prepareText.length == MAX_MESSAGE_TEXT_LENGTH) {
                            val lastIndex = prepareText.lastIndexOf('\n')
                                    .let { if (it != -1) it else prepareText.lastIndexOf(' ') }
                                    .let { if (it != -1) it else prepareText.length }

                            prepareText.substring(0, lastIndex)
                        } else {
                            prepareText
                        }
                        index += text!!.length + 1

                        if (index >= request.text!!.length) {
                            keyboard = request.keyboard
                        }
                    }
            if (msg != null) {
                result.add(msg)
            }
        }

        return result
    }

    private fun <T> checkAnswer(response: TelegramResponse<T>?): T? {
        if (response == null) {
            return null
        }

        if (response.isSuccess) {
            return response.result
        }

        throw ResponseErrorException(response.description)
    }

    private fun generateUrl(methodName: String): String = "$baseUrl/$methodName"

    private suspend inline fun <reified T> HttpClient.requestJson(
            method: HttpMethod,
            methodName: String,
            body: Any? = null,
            timeout: Long? = null
    ): T? {
        val response = requestJson(
                method,
                generateUrl(methodName),
                object : TypeReference<TelegramResponse<T>>() {},
                body,
                emptyMap(),
                emptyMap(),
                null,
                timeout
        )
        return checkAnswer(response.first)
    }

    private suspend inline fun <reified T> HttpClient.requestWithJsonResponse(
            method: HttpMethod,
            methodName: String,
            body: ByteArray? = null,
            queryParameters: Map<String, String> = emptyMap(),
            contentType: ContentType? = null
    ): T? {
        val response = requestWithJsonResponse(
                method,
                generateUrl(methodName),
                object : TypeReference<TelegramResponse<T>>() {},
                body,
                queryParameters,
                contentType
        )

        return checkAnswer(response.first)
    }

    companion object {
        private const val MAX_MESSAGE_TEXT_LENGTH = 4096
    }
}