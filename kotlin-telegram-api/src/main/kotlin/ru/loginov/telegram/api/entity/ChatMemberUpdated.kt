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
 * This object represents changes in the status of a chat member.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ChatMemberUpdated(
        /**
         * Chat the user belongs to
         */
        @JsonProperty(value = "chat", required = true) val chat: Chat,
        /**
         * Performer of the action, which resulted in the change
         */
        @JsonProperty(value = "from", required = true) val from: User,
        /**
         * Date the change was done in Unix time
         */
        @JsonProperty(value = "date", required = true) val dateTime: Long,
        /**
         * Previous information about the chat member
         */
        @JsonProperty(value = "old_chat_member", required = true) val oldChatMember: ChatMember,
        /**
         * New information about the chat member
         */
        @JsonProperty(value = "new_chat_member", required = true) val newChatMember: ChatMember,
        /**
         * Chat invite link, which was used by the user to join the chat; for joining by invite link events only.
         *
         * *Optional*
         */
        @JsonProperty(value = "invite_link", required = false) val inviteLink: ChatInviteLink?,
)