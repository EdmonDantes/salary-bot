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
import ru.loginov.telegram.api.entity.KeyboardButtonPollType
import ru.loginov.telegram.api.entity.PollType

class KeyboardButtonBuilder : AbstractKeyboardButtonBuilder<KeyboardButton>() {
    var text: String? = null
    var requestContact: Boolean? = null
    var requestLocation: Boolean? = null
    var requestPoll: PollType? = null

    fun text(text: CharSequence) {
        this.text = text.toString()
    }

    fun text(block: () -> CharSequence) = text(block())

    fun requestContact() {
        checkRequestOrThrow()
        requestContact = true
    }

    fun requestLocation() {
        checkRequestOrThrow()
        requestLocation = true
    }

    fun requestPoll(type: PollType?) {
        checkRequestOrThrow()
        this.requestPoll = type
    }


    private fun checkRequestOrThrow() {
        if (requestLocation == true) {
            error("Only one request can be in button. Button already has location request")
        }
        if (requestContact == true) {
            error("Only one request can be in button. Button already has contact request")
        }
        if (requestPoll != null) {
            error("Only one request can be in button. Button already has poll request")
        }
    }

    override fun build(): KeyboardButton = KeyboardButton(
            text ?: error("Builder hasn't text. Can not create keyboard button without text"),
            requestContact,
            requestLocation,
            requestPoll?.let { KeyboardButtonPollType(it) }
    )
}