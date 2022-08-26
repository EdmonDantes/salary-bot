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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
enum class MessageEntityType(@JsonValue private val tag: String) {
    /**
     * Example: @username
     */
    MENTION("mention"),

    /**
     * Example: #hashtag
     */
    HASHTAG("hashtag"),

    /**
     * Example: $USD
     */
    CASHTAG("cashtag"),

    /**
     * Example: /start@jobs_bot
     */
    BOT_COMMAND("bot_command"),

    /**
     * Example: `https://telegram.org`
     */
    URL("url"),

    /**
     * Example: do-not-reply@telegram.org
     */
    EMAIL("email"),

    /**
     * Example: +1-212-555-0123
     */
    PHONE_NUMBER("phone_number"),

    /**
     * Bold text
     */
    BOLD("bold"),

    /**
     * Italic text
     */
    ITALIC("italic"),

    /**
     * Underlined text
     */
    UNDERLINE("underline"),

    /**
     * Strikethrough text
     */
    STRIKETHROUGH("strikethrough"),

    /**
     * Spoiler message
     */
    SPOILER("spoiler"),

    /**
     * Monowidth string
     */
    CODE("code"),

    /**
     * Monowidth block
     */
    PRE("pre"),

    /**
     * For clickable text URLs
     */
    TEXT_LINK("text_link"),

    /**
     * For users without usernames
     */
    TEXT_MENTIONS("text_mention");
}