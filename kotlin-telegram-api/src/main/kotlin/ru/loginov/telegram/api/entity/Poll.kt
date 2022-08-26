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
 * This object contains information about a poll.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Poll(
        /**
         * Unique poll identifier
         */
        @JsonProperty(value = "id", required = true) val id: String,
        /**
         * Poll question, 1-300 characters
         */
        @JsonProperty(value = "question", required = true) val question: String,
        /**
         * List of poll options
         */
        @JsonProperty(value = "options", required = true) val options: List<PollOption>,
        /**
         * Total number of users that voted in the poll
         */
        @JsonProperty(value = "total_voter_count", required = true) val totalVoterCount: Long,
        /**
         * True, if the poll is closed
         */
        @JsonProperty(value = "is_closed", required = true) val isClosed: Boolean,
        /**
         * True, if the poll is anonymous
         */
        @JsonProperty(value = "is_anonymous", required = true) val isAnonymous: Boolean,
        /**
         * Poll type
         */
        @JsonProperty(value = "type", required = true) val type: PollType,
        /**
         * True, if the poll allows multiple answers
         */
        @JsonProperty(value = "allows_multiple_answers", required = true) val isAllowsMultipleAnswers: Boolean,
        /**
         * 0-based identifier of the correct answer option.
         * Available only for polls in the quiz mode, which are closed,
         * or was sent (not forwarded) by the bot or to the private chat with the bot.
         *
         * *Optional*
         */
        @JsonProperty(value = "correct_option_id", required = false) val correctOptionId: Long?,
        /**
         * Text that is shown when a user chooses an incorrect answer or taps
         * on the lamp icon in a quiz-style poll, 0-200 characters
         *
         * *Optional*
         */
        @JsonProperty(value = "explanation", required = false) val explanation: String?,
        /**
         * Special entities like usernames, URLs, bot commands, etc. that appear in the explanation
         *
         * *Optional*
         */
        @JsonProperty(value = "explanation_entities", required = false) val explanationEntities: List<MessageEntity>?,
        /**
         * Amount of time in seconds the poll will be active after creation
         *
         * *Optional*
         */
        @JsonProperty(value = "open_period", required = false) val openPeriodSec: Long?,
        /**
         * Point in time (Unix timestamp) when the poll will be automatically closed
         *
         * *Optional*
         */
        @JsonProperty(value = "close_date", required = false) val closeDateTime: Long?,
)