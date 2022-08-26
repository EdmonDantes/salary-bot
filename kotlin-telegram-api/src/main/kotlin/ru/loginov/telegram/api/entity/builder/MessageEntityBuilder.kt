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

package ru.loginov.telegram.api.entity.builder

import ru.loginov.telegram.api.entity.MessageEntity
import ru.loginov.telegram.api.entity.MessageEntityType
import ru.loginov.telegram.api.entity.User
import java.net.URL

class MessageEntityBuilder {

    private var type: MessageEntityType? = null
    private var offset: Long? = null
    private var length: Long? = null
    private var url: String? = null
    private var user: User? = null
    private var language: String? = null

    fun mention(): MessageEntityBuilder = apply {
        type = MessageEntityType.MENTION
    }

    fun hashtag(): MessageEntityBuilder = apply {
        type = MessageEntityType.HASHTAG
    }

    fun cashtag(): MessageEntityBuilder = apply {
        type = MessageEntityType.CASHTAG
    }

    fun botCommand(): MessageEntityBuilder = apply {
        type = MessageEntityType.BOT_COMMAND
    }

    fun url(): MessageEntityBuilder = apply {
        type = MessageEntityType.URL
    }

    fun email(): MessageEntityBuilder = apply {
        type = MessageEntityType.EMAIL
    }

    fun phoneNumber(): MessageEntityBuilder = apply {
        type = MessageEntityType.PHONE_NUMBER
    }

    fun bold(): MessageEntityBuilder = apply {
        type = MessageEntityType.BOLD
    }

    fun italic(): MessageEntityBuilder = apply {
        type = MessageEntityType.ITALIC
    }

    fun underline(): MessageEntityBuilder = apply {
        type = MessageEntityType.UNDERLINE
    }

    fun strikethrough(): MessageEntityBuilder = apply {
        type = MessageEntityType.STRIKETHROUGH
    }

    fun spoiler(): MessageEntityBuilder = apply {
        type = MessageEntityType.SPOILER
    }

    fun code(): MessageEntityBuilder = apply {
        type = MessageEntityType.CODE
    }

    fun codeBlock(language: String? = null): MessageEntityBuilder = apply {
        type = MessageEntityType.PRE
        this.language = language
    }

    fun link(url: URL): MessageEntityBuilder = apply {
        this.type = MessageEntityType.TEXT_LINK
        this.url = url.toString()
    }

    fun textMention(user: User): MessageEntityBuilder = apply {
        this.type = MessageEntityType.TEXT_MENTIONS
        this.user = user
    }

    fun setStartPosition(startPosition: Number): MessageEntityBuilder = apply {
        offset = startPosition.toLong()
    }

    fun setEndPosition(endPosition: Number): MessageEntityBuilder = apply {
        length = endPosition.toLong() - (offset ?: error("Not yet set start position"))
    }

    fun setLength(length: Number): MessageEntityBuilder = apply {
        this.length = length.toLong()
    }

    fun build(): MessageEntity = MessageEntity(
            type ?: error("Builder isn't init. Doesn't set 'type' property"),
            offset ?: error("Builder isn't init. Doesn't set 'offset' property"),
            length ?: error("Builder isn't init. Doesn't set 'length' property"),
            url,
            user,
            language
    )
}

fun messageEntity(block: MessageEntityBuilder.() -> Unit) : MessageEntity {
    val builder = MessageEntityBuilder()
    block(builder)
    return builder.build()
}