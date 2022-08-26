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
 * This object represents a chat photo.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ChatPhoto(
        /**
         * File identifier of small (160x160) chat photo.
         * This file_id can be used only for photo download
         * and only for as long as the photo is not changed.
         */
        @JsonProperty(value = "small_file_id", required = true) val smallFileId: Long,
        /**
         * Unique file identifier of small (160x160) chat photo,
         * which is supposed to be the same over time and for different bots.
         * Can't be used to download or reuse the file.
         */
        @JsonProperty(value = "small_file_unique_id", required = true) val smallFileUniqueId: Long,
        /**
         * File identifier of big (640x640) chat photo.
         * This file_id can be used only for photo download
         * and only for as long as the photo is not changed.
         */
        @JsonProperty(value = "big_file_id", required = true) val bigFileId: Long,
        /**
         * Unique file identifier of big (640x640) chat photo, which is supposed
         * to be the same over time and for different bots.
         * Can't be used to download or reuse the file.
         */
        @JsonProperty(value = "big_file_unique_id", required = true) val bigFileUniqueId: Long,
)