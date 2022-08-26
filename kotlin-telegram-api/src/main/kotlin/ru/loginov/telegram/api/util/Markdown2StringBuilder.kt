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

package ru.loginov.telegram.api.util

import ru.loginov.telegram.api.entity.User
import java.net.URL

interface Markdown2StringBuilder {

    fun append(builder: Markdown2StringBuilder): Markdown2StringBuilder

    fun append(char: Char, needToEscape: Boolean = true): Markdown2StringBuilder
    fun append(str: String, needToEscape: Boolean = true): Markdown2StringBuilder
    fun append(obj: Any, needToEscape: Boolean = true): Markdown2StringBuilder

    fun bold(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder
    fun italic(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder
    fun underline(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder
    fun strikethrough(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder
    fun spoiler(block: Markdown2StringBuilder.() -> Unit): Markdown2StringBuilder

    fun url(url: String): Markdown2StringBuilder
    fun url(url: URL): Markdown2StringBuilder
    fun link(name: String, url: String): Markdown2StringBuilder
    fun link(name: String, url: URL): Markdown2StringBuilder
    fun mention(userName: String): Markdown2StringBuilder
    fun mention(user: User): Markdown2StringBuilder
    fun mention(name: String, userId: Long): Markdown2StringBuilder
    fun mention(name: String, user: User): Markdown2StringBuilder
    fun code(block: StringBuilder.() -> Unit): Markdown2StringBuilder
    fun codeBlock(codeLanguage: String? = null, block: StringBuilder.() -> Unit): Markdown2StringBuilder

}