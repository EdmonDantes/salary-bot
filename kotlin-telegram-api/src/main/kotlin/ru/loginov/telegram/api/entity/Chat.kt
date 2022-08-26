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
 * This object represents a chat.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Chat(
        /**
         * Unique identifier for this chat.
         */
        @JsonProperty(value = "id", required = true) val id: Long,
        /**
         * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
         */
        @JsonProperty(value = "type", required = true) val type: String,
        /**
         * Title, for supergroups, channels and group chats
         *
         * *Optional*
         */
        @JsonProperty(value = "title", required = false) val title: String? = null,
        /**
         * Username, for private chats, supergroups and channels if available
         *
         * *Optional*
         */
        @JsonProperty(value = "username", required = false) val username: String? = null,
        /**
         * First name of the other party in a private chat
         *
         * *Optional*
         */
        @JsonProperty(value = "first_name", required = false) val firstName: String,
        /**
         * Last name of the other party in a private chat
         *
         * *Optional*
         */
        @JsonProperty(value = "last_name", required = false) val lastName: String? = null,
        /**
         * Chat photo.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "photo", required = false) val photo: ChatPhoto? = null,
        /**
         * Bio of the other party in a private chat.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "bio", required = false) val bio: String? = null,
        /**
         * True, if privacy settings of the other party in the private chat allows
         * to use *tg://user?id=<user_id>* links only in chats with the user.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "has_private_forwards", required = false) val hasPrivateForwards: Boolean? = null,
        /**
         * Description, for groups, supergroups and channel chats.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "description", required = false) val description: String? = null,
        /**
         * Primary invite link, for groups, supergroups and channel chats.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "invite_link", required = false) val inviteLink: String? = null,
        /**
         * The most recent pinned message (by sending date)
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "pinned_message", required = false) val pinnedMessage: Message? = null,
        /**
         * Default chat member permissions, for groups and supergroups
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "permissions", required = false) val permissions: ChatPermissions? = null,
        /**
         * For supergroups, the minimum allowed delay between consecutive messages
         * sent by each unpriviledged user; in seconds.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "show_mode_delay", required = false) val showModeDelaySeconds: Long? = null,
        /**
         * The time after which all messages sent to the chat will be automatically deleted; in seconds.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(
                value = "message_auto_delete_time",
                required = false
        ) val messageAutoDeleteTimeoutSeconds: Long? = null,
        /**
         * True, if messages from the chat can't be forwarded to other chats.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "has_protected_content", required = false) val hasProtectedContent: Boolean? = null,
        /**
         * For supergroups, name of group sticker set.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "sticker_set_name", required = false) val stickerSetName: String? = null,
        /**
         * True, if the bot can change the group sticker set.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "can_set_sticker_set", required = false) val canSetStickerSet: Boolean? = null,
        /**
         * Unique identifier for the linked chat, i.e. the discussion group identifier
         * for a channel and vice versa; for supergroups and channel chats.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "linked_chat_id", required = false) val linkedChatId: Long? = null,
        /**
         * For supergroups, the location to which the supergroup is connected.
         *
         * *Optional*
         *
         * It is returned only for ***getChat*** method
         */
        @JsonProperty(value = "location", required = false) val location: ChatLocation? = null
)