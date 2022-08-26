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
 * This object represents an incoming inline query.
 * When the user sends an empty query, your bot could return some default or trending results.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class InlineQuery(
        /**
         * Unique identifier for this query
         */
        @JsonProperty(value = "message", required = true) val id: String,
        /**
         * Sender
         */
        @JsonProperty(value = "from", required = true) val from: User,
        /**
         * Text of the query (up to 256 characters)
         */
        @JsonProperty(value = "query", required = true) val query: String,
        /**
         * Offset of the results to be returned, can be controlled by the bot
         */
        @JsonProperty(value = "offset", required = true) val offset: String,
        /**
         * Type of the chat, from which the inline query was sent.
         * Can be either “sender” for a private chat with the inline query sender, “private”, “group”, “supergroup”, or “channel”.
         * The chat type should be always known for requests sent from official clients and most third-party clients,
         * unless the request was sent from a secret chat
         *
         * *Optional*
         */
        @JsonProperty(value = "chat_type", required = false) val chatType: String?,
        /**
         * Sender location, only for bots that request user location
         *
         * *Optional*
         */
        @JsonProperty(value = "location", required = false) val location: Location?,
)