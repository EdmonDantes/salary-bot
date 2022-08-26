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

import ru.loginov.telegram.api.entity.BotCommand
import ru.loginov.telegram.api.entity.Message
import ru.loginov.telegram.api.entity.Update
import ru.loginov.telegram.api.entity.User
import ru.loginov.telegram.api.request.AnswerCallbackQueryRequest
import ru.loginov.telegram.api.request.DeleteMessageRequest
import ru.loginov.telegram.api.request.EditMessageReplyMarkupRequest
import ru.loginov.telegram.api.request.GetMyCommandsRequest
import ru.loginov.telegram.api.request.GetUpdatesRequest
import ru.loginov.telegram.api.request.SendMessageRequest
import ru.loginov.telegram.api.request.SetMyCommandsRequest

interface TelegramAPI {

    /**
     * Use this method to send answers to callback queries sent from inline keyboards.
     * The answer will be displayed to the user as a notification at the top of the chat screen or as an alert.
     */
    suspend fun answerCallbackQuery(request: AnswerCallbackQueryRequest.() -> Unit)
    suspend fun deleteMessage(request: DeleteMessageRequest.() -> Unit)
    suspend fun editMessageReplyMarkup(request: EditMessageReplyMarkupRequest.() -> Unit): Message?
    suspend fun getMyCommands(request: GetMyCommandsRequest.() -> Unit): List<BotCommand>
    suspend fun getMe(): User?
    suspend fun getUpdates(request: GetUpdatesRequest.() -> Unit): List<Update>
    suspend fun setMyCommands(request: SetMyCommandsRequest.() -> Unit)
    suspend fun sendMessage(request: SendMessageRequest.() -> Unit): Message?
    suspend fun sendMessageWithoutLimit(request: SendMessageRequest.() -> Unit) : List<Message>

}