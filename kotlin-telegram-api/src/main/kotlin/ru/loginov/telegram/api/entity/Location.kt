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
 * This object represents a point on the map.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Location(
        /**
         * Longitude as defined by sender
         */
        @JsonProperty(value = "longitude", required = true) val longitude: Double,
        /**
         * Latitude as defined by sender
         */
        @JsonProperty(value = "latitude", required = true) val latitude: Double,
        /**
         * The radius of uncertainty for the location, measured in meters; 0-1500
         *
         * *Optional*
         */
        @JsonProperty(value = "horizontal_accuracy", required = false) val horizontalAccuracy: Double,
        /**
         * Time relative to the message sending date, during which the location can be updated; in seconds.
         * For active live locations only.
         *
         * *Optional*
         */
        @JsonProperty(value = "live_period", required = false) val livePeriod: Long,
        /**
         * The direction in which user is moving, in degrees; 1-360.
         * For active live locations only.
         *
         * *Optional*
         */
        @JsonProperty(value = "heading", required = false) val heading: Long,
        /**
         * Maximum distance for proximity alerts about approaching another chat member, in meters.
         * For sent live locations only.
         *
         * *Optional*
         */
        @JsonProperty(value = "proximity_alert_radius", required = false) val proximityAlertRadius: Long,
)