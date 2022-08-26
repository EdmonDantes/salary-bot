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
 * Represents a result of an inline query that was chosen by the user and sent to their chat partner.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ChosenInlineResult(
        /**
         * The unique identifier for the result that was chosen
         */
        @JsonProperty(value = "result_id", required = true) val id: String,
        /**
         * The user that chose the result
         */
        @JsonProperty(value = "from", required = true) val from: User,
        /**
         * Sender location, only for bots that require user location
         *
         * *Optional*
         */
        @JsonProperty(value = "location", required = false) val location: Location?,
        /**
         * Identifier of the sent inline message. Available only if there is an inline keyboard attached to the message.
         * Will be also received in callback queries and can be used to edit the message.
         *
         * *Optional*
         */
        @JsonProperty(value = "inline_message_id", required = false) val inlineMessageId: String?,
        /**
         * The query that was used to obtain the result
         */
        @JsonProperty(value = "query", required = true) val query: String,
)