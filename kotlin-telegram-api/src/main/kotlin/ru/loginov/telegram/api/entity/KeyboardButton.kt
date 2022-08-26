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
 * This object represents one button of the reply keyboard.
 * For simple text buttons String can be used instead of this object to specify text of the button.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class KeyboardButton(
        /**
         * Text of the button. If none of the optional fields are used,
         * it will be sent as a message when the button is pressed
         */
        @JsonProperty(value = "text", required = true) val text: String,
        /**
         * If True, the user's phone number will be sent as a contact when the button is pressed.
         * Available in private chats only.
         *
         * *Optional*
         */
        @JsonProperty(value = "request_contact", required = false) val requiredContact: Boolean? = null,
        /**
         * If True, the user's current location will be sent when the button is pressed.
         * Available in private chats only.
         *
         * *Optional*
         */
        @JsonProperty(value = "request_location", required = false) val requestLocation: Boolean? = null,
        /**
         * If specified, the user will be asked to create a poll and send it to the bot when the button is pressed.
         * Available in private chats only.
         *
         * *Optional*
         */
        @JsonProperty(value = "request_poll", required = false) val request_poll: KeyboardButtonPollType?,
//        @JsonProperty(value = "web_app", required = false) val web_app: WebAppInfo?,
)