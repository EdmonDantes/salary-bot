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
 * Abstract class for creating custom keyboard lines builders
 *
 * @param T buttons class
 * @param B button builder class
 */
abstract class AbstractKeyboardLineBuilder<T, B : AbstractKeyboardButtonBuilder<T>> {
    private var line: MutableList<B> = ArrayList()
    private var width: Long? = null

    val size: Int
        get() = line.size

    fun add(block: B.() -> Unit) {
        if (width == null || line.size < width!!) {
            val builder = createKeyboardBuilder()
            block(builder)
            line.add(builder)
        } else {
            error("Limit buttons reached = '$width'")
        }
    }

    fun maxWidth(width: Number?) {
        this.width = width?.toLong()
    }

    fun maxWidth(block: () -> Number?) {
        this.width = block()?.toLong()
    }

    fun build(): List<T> = line.map { it.build() }

    protected abstract fun createKeyboardBuilder(): B
}