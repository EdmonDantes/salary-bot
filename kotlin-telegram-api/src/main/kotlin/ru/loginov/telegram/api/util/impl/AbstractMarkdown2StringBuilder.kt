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

package ru.loginov.telegram.api.util.impl

import ru.loginov.telegram.api.entity.User
import ru.loginov.telegram.api.util.Markdown2StringBuilder
import java.net.URL

abstract class AbstractMarkdown2StringBuilder : Markdown2StringBuilder {

    protected val markdown2 = StringBuilder()

    protected abstract val codeBlockEscapedSymbols: CharArray

    override fun append(builder: Markdown2StringBuilder): Markdown2StringBuilder = this.apply {
        append(builder.toString(), false)
    }

    override fun bold(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder = this.apply {
        processBlock("*", block)
    }

    override fun italic(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder = this.apply {
        processBlock("_", block)
    }

    override fun underline(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder = this.apply {
        processBlock("__", block)
    }

    override fun strikethrough(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder = this.apply {
        processBlock("~", block)
    }

    override fun spoiler(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder = this.apply {
        processBlock("||", block)
    }

    override fun url(url: String): Markdown2StringBuilder = this.apply {
        if (URL_REGEX_CHECKER.find(url)?.value != url) {
            throw IllegalArgumentException("Wrong url format. Url should be https://google.com or www.google.com")
        }

        append(url)
    }

    override fun url(url: URL): Markdown2StringBuilder = this.apply {
        append(url.toString())
    }

    override fun link(name: String, url: String): Markdown2StringBuilder = this.apply {
        if (URL_REGEX_CHECKER.find(url)?.value != url) {
            throw IllegalArgumentException("Wrong url format. Url should be https://google.com or www.google.com")
        }
        append('[', false).append(name).append(']', false)
                .append('(', false).append(url).append(')', false)
    }

    override fun link(name: String, url: URL): Markdown2StringBuilder = this.apply {
        append('[', false).append(name).append(']', false)
                .append('(', false).append(url.toString()).append(')', false)
    }

    override fun mention(userName: String): Markdown2StringBuilder = this.apply {
        if (userName.isEmpty()) {
            throw IllegalArgumentException("Users name can not be empty")
        }

        append('@', false).append(userName)
    }

    override fun mention(user: User): Markdown2StringBuilder = this.apply {
        if (user.username.isNullOrEmpty()) {
            throw IllegalArgumentException("Users name can not be empty")
        }
        append('@', false).append(user.username)
    }

    override fun mention(name: String, userId: Long): Markdown2StringBuilder = this.apply {
        if (name.isEmpty()) {
            throw IllegalArgumentException("Mentions name can not be empty")
        }
        append('[', false).append(name).append("](", false)
                .append("tg://user?id=").append(userId).append(')', false)
    }

    override fun mention(name: String, user: User): Markdown2StringBuilder = this.apply {
        if (name.isEmpty()) {
            throw IllegalArgumentException("Mentions name can not be empty")
        }

        append('[', false).append(name).append("](", false)
                .append("tg://user?id=").append(user.id).append(')', false)
    }

    override fun code(block: StringBuilder.() -> Unit): Markdown2StringBuilder = this.apply {
        append('`', false)
        val codeBlockBuilder = StringBuilder()
        block(codeBlockBuilder)
        codeBlockBuilder.replace(codeBlockEscapedSymbols) { ch -> "\\$ch" }

        append(codeBlockBuilder.toString(), false)
        append('`', false)
    }

    override fun codeBlock(
            codeLanguage: String?,
            block: StringBuilder.() -> Unit
    ): Markdown2StringBuilder = this.apply {
        if (markdown2.last() != '\n') {
            markdown2.append('\n')
        }
        markdown2.append("```")
        if (codeLanguage != null) {
            markdown2.append(codeLanguage)
        }
        markdown2.append('\n')

        val codeBlockBuilder = StringBuilder()
        block(codeBlockBuilder)
        codeBlockBuilder.replace(codeBlockEscapedSymbols) { ch -> "\\$ch" }

        append(codeBlockBuilder.toString(), false)
        if (markdown2.last() != '\n') {
            markdown2.append('\n')
        }
        markdown2.append("```")
    }

    protected fun processBlock(borderChars: String, block: Markdown2StringBuilder.() -> Unit) {
        append(borderChars, false)
        block(this)
        append(borderChars, false)
    }

    protected fun StringBuilder.replace(chars: CharArray, block: (Char) -> String): StringBuilder {
        var index = 0
        while (index < length) {
            val ch = get(index)
            if (chars.contains(ch)) {
                val forReplace = block(ch)
                replace(index, index + 1, forReplace)
                index += forReplace.length
            } else {
                index++
            }
        }

        return this
    }


    override fun toString(): String = markdown2.toString()

    override fun equals(other: Any?): Boolean =
            this === other
                    || javaClass == other?.javaClass
                    && markdown2 == (other as AbstractMarkdown2StringBuilder).markdown2

    override fun hashCode(): Int = markdown2.hashCode()

    companion object {
        private val URL_REGEX_CHECKER = Regex("(https?://(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?://(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})\n")
    }
}