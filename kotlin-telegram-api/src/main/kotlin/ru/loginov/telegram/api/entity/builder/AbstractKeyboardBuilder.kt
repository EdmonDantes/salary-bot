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

/**
 * Abstract class for create custom keyboard builders
 *
 * @param K finally keyboard type
 * @param B buttons class
 * @param LB line builder class
 * @param BB button builder class
 */
abstract class AbstractKeyboardBuilder<K, B, LB : AbstractKeyboardLineBuilder<B, BB>, BB : AbstractKeyboardButtonBuilder<B>> {

    private var keyboardLines = ArrayList<LB>()
    private var height: Int? = null

    val size: Int
        get() = keyboardLines.size

    fun height(height: Number?) {
        this.height = height?.toInt()
    }

    fun height(block: () -> Number?) {
        this.height = block()?.toInt()
    }

    fun line(block: LB.() -> Unit) {
        if (height == null || keyboardLines.size < height!!) {
            val builder = createLineBuilder()
            block(builder)
            if (builder.size > 0) {
                keyboardLines.add(builder)
            }
        } else {
            error("Limit lines reached = '$height'")
        }
    }

    fun build(): K = internalBuild(keyboardLines.map { it.build() })

    protected abstract fun createLineBuilder(): LB

    protected abstract fun internalBuild(keyboard: List<List<B>>): K

}