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
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This object describes the position on faces where a mask should be placed by default.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MaskPosition(
        /**
         * The part of the face relative to which the mask should be placed.
         * One of “forehead”, “eyes”, “mouth”, or “chin”.
         */
        @JsonProperty(value = "point", required = true) val point: String,
        /**
         * Shift by X-axis measured in widths of the mask scaled to the face size,
         * from left to right. For example, choosing -1.0 will place mask just
         * to the left of the default mask position.
         */
        @JsonProperty(value = "x_shift", required = true) val xShift: Double,
        /**
         * Shift by Y-axis measured in heights of the mask scaled to the face size,
         * from top to bottom. For example, 1.0 will place the mask just below
         * the default mask position.
         */
        @JsonProperty(value = "y_shift", required = true) val yShift: Double,
        /**
         * Mask scaling coefficient. For example, 2.0 means double size.
         */
        @JsonProperty(value = "scale", required = true) val scale: Double,
)