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

import ru.loginov.telegram.api.entity.KeyboardButton
import ru.loginov.telegram.api.entity.ReplyKeyboardMarkup

class ReplyKeyboardMarkupBuilder : AbstractKeyboardBuilder<ReplyKeyboardMarkup, KeyboardButton, ReplyKeyboardMarkupLineBuilder, KeyboardButtonBuilder>() {

    private var resizeOnClient: Boolean? = null
    private var oneTime: Boolean? = null
    private var placeholder: String? = null
    private var selective: Boolean? = null

    fun shouldResizeOnClient(): ReplyKeyboardMarkupBuilder = apply {
        resizeOnClient = true
    }

    fun once(): ReplyKeyboardMarkupBuilder = apply {
        oneTime = true
    }

    fun placeholder(placeholder: String): ReplyKeyboardMarkupBuilder = apply {
        this.placeholder = placeholder
    }

    fun selective(): ReplyKeyboardMarkupBuilder = apply {
        this.selective = selective
    }

    override fun createLineBuilder(): ReplyKeyboardMarkupLineBuilder = ReplyKeyboardMarkupLineBuilder()

    override fun internalBuild(keyboard: List<List<KeyboardButton>>): ReplyKeyboardMarkup =
            ReplyKeyboardMarkup(keyboard, resizeOnClient, oneTime, placeholder, selective)
}