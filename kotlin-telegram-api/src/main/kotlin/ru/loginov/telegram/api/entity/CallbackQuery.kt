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

package ru.loginov.telegram.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This object represents an incoming callback query from a callback button in an inline keyboard.
 * If the button that originated the query was attached to a message sent by the bot, the field message will be present.
 * If the button was attached to a message sent via the bot (in inline mode),
 * the field [inlineMessageId] will be present.
 * Exactly one of the fields [data] or [gameShortName] will be present.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class CallbackQuery(
        /**
         * Unique identifier for this query
         */
        @JsonProperty(value = "id", required = true) val id: String,
        /**
         * Sender
         */
        @JsonProperty(value = "from", required = true) val user: User,
        /**
         * Message with the callback button that originated the query.
         * Note that message content and message date will not be available if the message is too old
         *
         * *Optional*
         */
        @JsonProperty(value = "message", required = false) val message: Message?,
        /**
         * Identifier of the message sent via the bot in inline mode, that originated the query.
         *
         * *Optional*
         */
        @JsonProperty(value = "inline_message_id", required = false) val inlineMessageId: Long?,
        /**
         * Global identifier, uniquely corresponding to the chat to which the message with the callback button was sent.
         * Useful for high scores in games.
         */
        @JsonProperty(value = "chat_instance", required = true) val chatInstance: String,
        /**
         * Data associated with the callback button.
         * Be aware that a bad client can send arbitrary data in this field.
         *
         * *Optional*
         */
        @JsonProperty(value = "data", required = false) val data: String?,
        /**
         * Short name of a Game to be returned, serves as the unique identifier for the game
         *
         * *Optional*
         */
        @JsonProperty(value = "game_short_name", required = false) val gameShortName: String?
)