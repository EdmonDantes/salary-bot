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
 * This object represents an audio file to be treated as music by the Telegram clients.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Audio(
        /**
         * Identifier for this file, which can be used to download or reuse the file
         */
        @JsonProperty(value = "file_id", required = true) val file_id: String,
        /**
         * Unique identifier for this file, which is supposed
         * to be the same over time and for different bots.
         * Can't be used to download or reuse the file.
         */
        @JsonProperty(value = "file_unique_id", required = true) val file_unique_id: String,
        /**
         * Duration of the audio in seconds as defined by sender
         */
        @JsonProperty(value = "duration", required = true) val durationSec: Long,
        /**
         * Performer of the audio as defined by sender or by audio tags
         *
         * *Optional*
         */
        @JsonProperty(value = "performer", required = false) val performer: String?,
        /**
         * Title of the audio as defined by sender or by audio tags
         *
         * *Optional*
         */
        @JsonProperty(value = "title", required = false) val title: String?,
        /**
         * Original filename as defined by sender
         *
         * *Optional*
         */
        @JsonProperty(value = "file_name", required = false) val fileName: String?,
        /**
         * MIME type of the file as defined by sender
         *
         * *Optional*
         */
        @JsonProperty(value = "mime_type", required = false) val mimeType: String?,
        /**
         * File size in bytes
         *
         * *Optional*
         */
        @JsonProperty(value = "file_size", required = false) val sizeBytes: Long?,
        /**
         * Thumbnail of the album cover to which the music file belongs
         *
         * *Optional*
         */
        @JsonProperty(value = "thumb", required = false) val thumb: PhotoSize?,
)