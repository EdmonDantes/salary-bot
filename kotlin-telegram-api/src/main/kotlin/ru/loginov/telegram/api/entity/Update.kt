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
 * This object represents an incoming update.
 * At most one of the optional parameters can be present in any given update.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Update(
        /**
         * The update's unique identifier.
         * Update identifiers start from a certain positive number and increase sequentially.
         * This ID becomes especially handy if you're using Webhooks,
         * since it allows you to ignore repeated updates or to restore the correct update sequence,
         * should they get out of order.
         * If there are no new updates for at least a week, then identifier
         * of the next update will be chosen randomly instead of sequentially.
         */
        @JsonProperty(value = "update_id", required = true) val id: Long,
        /**
         * New incoming message of any kind — text, photo, sticker, etc.
         *
         * *Optional*
         */
        @JsonProperty(value = "message", required = false) val message: Message? = null,
        /**
         * New version of a message that is known to the bot and was edited
         *
         * *Optional*
         */
        @JsonProperty(value = "edited_message", required = false) val editedMessage: Message? = null,
        /**
         * New incoming channel post of any kind — text, photo, sticker, etc.
         *
         * *Optional*
         */
        @JsonProperty(value = "channel_post", required = false) val channelPost: Message? = null,
        /**
         * New version of a channel post that is known to the bot and was edited
         *
         * *Optional*
         */
        @JsonProperty(value = "edited_channel_post", required = false) val editedChannelPost: Message? = null,
        /**
         * New incoming inline query
         *
         * *Optional*
         */
        @JsonProperty(value = "inline_query", required = false) val inlineQuery: InlineQuery? = null,
        /**
         * The result of an inline query that was chosen by a user and sent to their chat partner.
         *
         * *Optional*
         */
        @JsonProperty(value = "chosen_inline_result", required = false) val chosenInlineResult: ChosenInlineResult? = null,
        /**
         * New incoming callback query
         *
         * *Optional*
         */
        @JsonProperty(value = "callback_query", required = false) val callbackQuery: CallbackQuery?,
        /**
         * New incoming shipping query. Only for invoices with flexible price
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "shipping_query", required = false) val shippingQuery: ShippingQuery?,
        /**
         * New incoming pre-checkout query. Contains full information about checkout
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "pre_checkout_query", required = false) val preCheckoutQuery: PreCheckoutQuery?,
        /**
         * New poll state. Bots receive only updates about stopped polls and polls, which are sent by the bot
         *
         * *Optional*
         */
        @JsonProperty(value = "poll", required = false) val poll: Poll? = null,
        /**
         * A user changed their answer in a non-anonymous poll.
         * Bots receive new votes only in polls that were sent by the bot itself.
         *
         * *Optional*
         */
        @JsonProperty(value = "poll_answer", required = false) val pollAnswer: PollAnswer? = null,
        /**
         * The bot's chat member status was updated in a chat.
         * For private chats, this update is received only when the bot is blocked or unblocked by the user.
         *
         * *Optional*
         */
        @JsonProperty(value = "my_chat_member", required = false) val myChatMember: ChatMemberUpdated? = null,
        /**
         * A chat member's status was updated in a chat.
         * The bot must be an administrator in the chat
         * and must explicitly specify [chatMember] in the list of allowed_updates to receive these updates.
         *
         * *Optional*
         */
        @JsonProperty(value = "chat_member", required = false) val chatMember: ChatMemberUpdated? = null,
        /**
         * A request to join the chat has been sent.
         * The bot must have the [ChatPermissions.canInviteUsers] administrator right in the chat to receive these updates.
         *
         * *Optional*
         */
        @JsonProperty(value = "chat_join_request", required = false) val chatJoinRequest: ChatJoinRequest? = null,
)