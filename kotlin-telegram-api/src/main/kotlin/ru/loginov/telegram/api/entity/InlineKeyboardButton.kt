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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class InlineKeyboardButton(
        /**
         * Label text on the button
         */
        @JsonProperty(value = "text", required = true) var text: String,
        /**
         * HTTP or tg:// url to be opened when the button is pressed.
         * Links tg://user?id=<user_id> can be used to mention a user by their ID without using a username,
         * if this is allowed by their privacy settings.
         *
         * *Optional*
         */
        @JsonProperty(value = "url", required = false) var url: String? = null,
        /**
         * Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes
         *
         * *Optional*
         */
        @JsonProperty(value = "callback_data", required = false) var callbackData: String? = null,
        /**
         * Description of the Web App that will be launched when the user presses the button.
         * The Web App will be able to send an arbitrary message on behalf of
         * the user using the method answerWebAppQuery. Available only in private chats between a user and the bot.
         *
         * *Optional*
         */
        //@JsonProperty(value = "web_app", required = false) var web_app: WebAppInfo?,
        /**
         * An HTTP URL used to automatically authorize the user.
         * Can be used as a replacement for the Telegram Login Widget.
         *
         * *Optional*
         */
        //@JsonProperty(value = "login_url", required = false) var login_url: LoginUrl?,
        /**
         * If set, pressing the button will prompt the user to select one of their chats,
         * open that chat and insert the bot's username and the specified inline query in the input field.
         * Can be empty, in which case just the bot's username will be inserted.
         *
         * Note: This offers an easy way for users to start using your bot in
         * inline mode when they are currently in a private chat with it.
         * Especially useful when combined with switch_pm… actions – in this case
         * the user will be automatically returned to the chat they switched from,
         * skipping the chat selection screen.
         *
         * *Optional*
         */
        @JsonProperty(value = "switch_inline_query", required = false) var switchInlineQuery: String? = null,
        /**
         * If set, pressing the button will insert the bot's username and
         * the specified inline query in the current chat's input field.
         * Can be empty, in which case only the bot's username will be inserted.
         *
         * This offers a quick way for the user to open your bot in inline mode
         * in the same chat – good for selecting something from multiple options.
         *
         * *Optional*
         */
        @JsonProperty(value = "switch_inline_query_current_chat", required = false) var switchInlineQueryCurrentChat: String? = null,
        /**
         * Description of the game that will be launched when the user presses the button
         *
         * Note: This type of button must always be the first button in the first row.
         *
         * *Optional*
         */
        //@JsonProperty(value = "callback_game", required = false) var callback_game: CalbackGame?,
        /**
         * This type of button must always be the first button in the first row.
         *
         * NOTE: This type of button must always be the first button in the first row and can only be used in invoice messages.
         *
         * *Optional*
         */
        @JsonProperty(value = "pay", required = false) var pay: Boolean? = null,

        )