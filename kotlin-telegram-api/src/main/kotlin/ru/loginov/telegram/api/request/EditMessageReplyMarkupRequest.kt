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

package ru.loginov.telegram.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.loginov.telegram.api.entity.InlineKeyboardMarkup
import ru.loginov.telegram.api.entity.builder.InlineKeyboardMarkupBuilder

/**
 * This object represents request for method [ru.loginov.telegram.api.TelegramAPI.editMessageReplyMarkup]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class EditMessageReplyMarkupRequest {

    @JsonProperty(value = "chat_id", required = false)
    var chatId: Long? = null

    @JsonProperty(value = "message_id", required = false)
    var messageId: Long? = null

    @JsonProperty(value = "inline_message_id", required = false)
    var inlineMessageId: Long? = null

    @JsonProperty(value = "reply_markup", required = false)
    var keyboard: InlineKeyboardMarkup? = null

    fun inlineKeyboard(block: InlineKeyboardMarkupBuilder.() -> Unit): EditMessageReplyMarkupRequest = apply {
        keyboard = InlineKeyboardMarkupBuilder().also(block).build()
    }

}