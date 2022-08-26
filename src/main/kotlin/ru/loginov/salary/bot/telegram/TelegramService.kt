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

package ru.loginov.salary.bot.telegram

import io.ktor.client.features.HttpRequestTimeoutException
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.loginov.telegram.api.TelegramAPI
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import kotlin.concurrent.thread
import kotlin.math.max

@Service
class TelegramService(
    private val telegram: TelegramAPI,
    private val handler: TelegramHandler,
    @Value("\${bot.telegram.update.timeout.sec:5}") private val longPollingTimeoutSec: Long = 5,
) {

    @Volatile
    private var isContinue = true

    private val fetcherThread = thread(name = "Telegram Update Fetcher", start = false) {
        var lastSeq: Long? = null
        var i = 0
        while (isContinue) {
            lastSeq = fetchUpdates(lastSeq)
            i++
        }
    }


    private fun fetchUpdates(lastSeq: Long?): Long? {
        val updates =
            try {
                runBlocking {
                    telegram.getUpdates {
                        offset = lastSeq
                        timeoutSec = longPollingTimeoutSec
                    }
                }
            } catch (e: Exception) {
                if (e !is InterruptedException && e !is HttpRequestTimeoutException) {
                    LOGGER.warn("Can not get updates for telegram bot", e)
                }
                emptyList()
            }

        if (updates.isEmpty()) {
            return lastSeq
        }

        var newLastSeq = lastSeq ?: Long.MIN_VALUE

        updates.forEach { update ->
            newLastSeq = max(newLastSeq , update.id + 1)
            try {
                handler.handle(update)
            } catch (e: Exception) {
                LOGGER.error("Can not execute 'onUpdate' for handler: ${handler.javaClass}", e)
            }
        }

        return newLastSeq
    }

    @PostConstruct
    fun postConstruct() {
        fetcherThread.start()
    }


    @PreDestroy
    fun preDestroy() {
        isContinue = false
        try {
            if (fetcherThread.isAlive) {
                fetcherThread.join(DEFAULT_TIMEOUT_STOPPING)
                fetcherThread.interrupt()
                fetcherThread.join(DEFAULT_TIMEOUT_INTERRUPT)
            }
        } catch (e: Exception) {
            LOGGER.error("Can not stop fetcher thread", e)
        }
    }

    companion object {
        private const val DEFAULT_TIMEOUT_STOPPING: Long = 5000
        private const val DEFAULT_TIMEOUT_INTERRUPT: Long = 5000

        private val LOGGER: Logger = LoggerFactory.getLogger(TelegramService::class.java)
    }



}