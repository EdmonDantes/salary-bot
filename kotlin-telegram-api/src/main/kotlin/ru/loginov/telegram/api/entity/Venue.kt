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
 * This object represents a venue.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Venue(
        /**
         * Venue location. Can't be a live location
         */
        @JsonProperty(value = "location", required = true) val location: Location,
        /**
         * Name of the venue
         */
        @JsonProperty(value = "title", required = true) val title: String,
        /**
         * Address of the venue
         */
        @JsonProperty(value = "address", required = true) val address: String,
        /**
         * Foursquare identifier of the venue
         *
         * *Optional*
         */
        @JsonProperty(value = "foursquare_id", required = false) val foursquareId: String?,
        /**
         * Foursquare type of the venue.
         * (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
         *
         * *Optional*
         */
        @JsonProperty(value = "foursquare_type", required = false) val foursquareType: String?,
        /**
         * Google Places identifier of the venue
         *
         * *Optional*
         */
        @JsonProperty(value = "google_place_id", required = false) val googlePlaceId: String?,
        /**
         * Google Places type of the venue.
         *
         * *Optional*
         */
        @JsonProperty(value = "google_place_type", required = false) val googlePlaceType: String?
)