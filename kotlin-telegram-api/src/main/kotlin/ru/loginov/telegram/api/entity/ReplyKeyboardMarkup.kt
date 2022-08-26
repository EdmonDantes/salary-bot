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
 * This object represents a custom keyboard with reply options.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ReplyKeyboardMarkup(
        /**
         * Array of button rows, each represented by an Array of KeyboardButton objects
         */
        @JsonProperty(value = "keyboard", required = true) val keyboardButtons: List<List<KeyboardButton>>,
        /**
         * Requests clients to resize the keyboard vertically for optimal fit
         * (e.g., make the keyboard smaller if there are just two rows of buttons).
         * Defaults to false, in which case the custom keyboard
         * is always of the same height as the app's standard keyboard.
         *
         * *Optional*
         */
        @JsonProperty(value = "resize_keyboard", required = false) val shouldResizeOnClient: Boolean?,
        /**
         * Requests clients to hide the keyboard as soon as it's been used.
         * The keyboard will still be available, but clients will automatically
         * display the usual letter-keyboard in the chat – the user can press
         * a special button in the input field to see the custom keyboard again.
         * Defaults to false.
         *
         * *Optional*
         */
        @JsonProperty(value = "one_time_keyboard", required = false) val oneTimeKeyboard: Boolean?,
        /**
         * The placeholder to be shown in the input field when the keyboard is active; 1-64 characters
         *
         * *Optional*
         */
        @JsonProperty(value = "input_field_placeholder", required = false) val inputPlaceholder: String?,
        /**
         * Use this parameter if you want to show the keyboard to specific users only.
         * Targets:
         * 1) users that are @mentioned in the text of the Message object;
         * 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
         *
         * *Optional*
         */
        @JsonProperty(value = "selective", required = false) val selective: Boolean?
)