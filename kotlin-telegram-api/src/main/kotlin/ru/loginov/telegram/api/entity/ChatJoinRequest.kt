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
 * Represents a join request sent to a chat.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ChatJoinRequest(
        /**
         * Chat to which the request was sent
         */
        @JsonProperty(value = "chat", required = true) val chat: Chat,
        /**
         * User that sent the join request
         */
        @JsonProperty(value = "from", required = true) val from: User,
        /**
         * Date the request was sent in Unix time
         */
        @JsonProperty(value = "date", required = true) val date: Long,
        /**
         * Bio of the user.
         *
         * *Optional*
         */
        @JsonProperty(value = "bio", required = false) val bio: String?,
        /**
         * Chat invite link that was used by the user to send the join request
         *
         * *Optional*
         */
        @JsonProperty(value = "invite_link", required = false) val inviteLink: ChatInviteLink?,
)