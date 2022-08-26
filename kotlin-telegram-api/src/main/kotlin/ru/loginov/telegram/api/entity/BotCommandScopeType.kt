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
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Scope type
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
enum class BotCommandScopeType(@JsonValue val type: String) {
    /**
     * Represents the default scope of bot commands.
     * Default commands are used if no commands with a narrower scope are specified for the user.
     */
    DEFAULT("default"),

    /**
     * Represents the scope of bot commands, covering all private chats.
     */
    ALL_PRIVATE_CHAT("all_private_chats"),

    /**
     * Represents the scope of bot commands, covering all group and supergroup chats.
     */
    ALL_GROUP_CHATS("all_group_chats"),

    /**
     * Represents the scope of bot commands, covering all group and supergroup chat administrators.
     */
    ALL_CHAT_ADMINISTRATORS("all_chat_administrators"),

    /**
     * Represents the scope of bot commands, covering a specific chat.
     */
    CHAT("chat"),

    /**
     * Represents the scope of bot commands, covering all administrators of a specific group or supergroup chat.
     */
    CHAT_ADMINISTRATORS("chat_administrators"),

    /**
     * Represents the scope of bot commands, covering a specific member of a group or supergroup chat.
     */
    CHAT_MEMBER("chat_member")
}