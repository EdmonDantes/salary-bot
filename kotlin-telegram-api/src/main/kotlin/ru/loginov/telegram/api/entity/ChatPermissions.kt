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
 * Describes actions that a non-administrator user is allowed to take in a chat.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ChatPermissions(
        /**
         * True, if the user is allowed to send text messages, contacts, locations and venues
         *
         * *Optional*
         */
        @JsonProperty(value = "can_send_messages", required = false) val canSendMessage: Boolean?,
        /**
         * True, if the user is allowed to send audios, documents, photos, videos,
         * video notes and voice notes, implies [canSendMessages]
         *
         * *Optional*
         */
        @JsonProperty(value = "can_send_media_messages", required = false) val canSendMediaMessages: Boolean?,
        /**
         * True, if the user is allowed to send polls, implies [canSendMessages]
         *
         * *Optional*
         */
        @JsonProperty(value = "can_send_polls", required = false) val canSendPolls: Boolean?,
        /**
         * True, if the user is allowed to send animations, games, stickers and use inline bots,
         * implies [canSendMediaMessages]
         *
         * *Optional*
         */
        @JsonProperty(value = "can_send_other_messages", required = false) val canSendOtherMessages: Boolean?,
        /**
         * True, if the user is allowed to add web page previews to their messages, implies [canSendMediaMessages]
         *
         * *Optional*
         */
        @JsonProperty(value = "can_add_web_page_previews", required = false) val canAddWebPagePreviews: Boolean?,
        /**
         * True, if the user is allowed to change the chat title, photo and other settings.
         * Ignored in public supergroups
         *
         * *Optional*
         */
        @JsonProperty(value = "can_change_info", required = false) val canChangeInfo: Boolean?,
        /**
         * True, if the user is allowed to invite new users to the chat
         *
         * *Optional*
         */
        @JsonProperty(value = "can_invite_users", required = false) val canInviteUsers: Boolean?,
        /**
         * True, if the user is allowed to pin messages. Ignored in public supergroups
         *
         * *Optional*
         */
        @JsonProperty(value = "can_pin_messages", required = false) val canPinMessages: Boolean?,
)
