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

import ru.loginov.telegram.api.entity.InlineKeyboardButton
import ru.loginov.telegram.api.entity.InlineKeyboardMarkup

class InlineKeyboardMarkupBuilder : AbstractKeyboardBuilder<InlineKeyboardMarkup, InlineKeyboardButton, InlineKeyboardMarkupLineBuilder, InlineKeyboardMarkupButtonBuilder>() {
    override fun createLineBuilder(): InlineKeyboardMarkupLineBuilder = InlineKeyboardMarkupLineBuilder()

    override fun internalBuild(keyboard: List<List<InlineKeyboardButton>>): InlineKeyboardMarkup =
            InlineKeyboardMarkup(keyboard)

}