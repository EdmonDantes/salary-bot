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

package ru.loginov.telegram.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This object represents request for method [ru.loginov.telegram.api.TelegramAPI.getUpdates]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class GetUpdatesRequest {
        /**
         * Identifier of the first update to be returned.
         * Must be greater by one than the highest among the identifiers of previously received updates.
         * By default, updates starting with the earliest unconfirmed update are returned.
         * An update is considered confirmed as soon as [ru.loginov.telegram.api.TelegramAPI.getUpdates]
         * is called with an [offset] higher than its [ru.loginov.telegram.api.entity.Update.id].
         * The negative [offset] can be specified to retrieve updates starting from *-offset* update from
         * the end of the updates queue. All previous updates will forgotten.
         */
        @JsonProperty(value = "offset", required = false)
        var offset: Long? = null

        /**
         * Limits the number of updates to be retrieved.
         * Values between 1-100 are accepted. Defaults to 100.
         */
        @JsonProperty(value = "limit", required = false)
        var limit: Long? = null

        /**
         * Timeout in seconds for long polling.
         * Defaults to 0, i.e. usual short polling.
         * Should be positive, short polling should be used for testing purposes only.
         */
        @JsonProperty(value = "timeout", required = false)
        var timeoutSec: Long? = null

        /**
         * A JSON-serialized list of the update types you want your bot to receive.
         * For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these types.
         * See Update for a complete list of available update types.
         * Specify an empty list to receive all update types except chat_member (default).
         * If not specified, the previous setting will be used.
         *
         * Please note that this parameter doesn't affect updates created before the call to the getUpdates,
         * so unwanted updates may be received for a short period of time.
         */
        @JsonProperty(value = "allowed_updates", required = false)
        var allowedUpdates: List<String>? = null
}