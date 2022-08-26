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
 * Upon receiving a message with this object, Telegram clients will remove the current custom keyboard
 * and display the default letter-keyboard.
 * By default, custom keyboards are displayed until a new keyboard is sent by a bot.
 * An exception is made for one-time keyboards that are hidden immediately
 * after the user presses a button.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ReplyKeyboardRemove(
        /**
         * Requests clients to remove the custom keyboard
         * (user will not be able to summon this keyboard;
         * if you want to hide the keyboard from sight but keep it accessible,
         * use [ReplyKeyboardMarkup.oneTimeKeyboard])
         */
        @JsonProperty(value = "remove_keyboard", required = true) val removeKeyboard: Boolean,
        /**
         * Use this parameter if you want to remove the keyboard for specific users only.
         * Targets:
         * 1) users that are @mentioned in the text of the Message object;
         * 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
         *
         * *Optional*
         */
        @JsonProperty(value = "selective", required = false) val selective: Boolean?,
)