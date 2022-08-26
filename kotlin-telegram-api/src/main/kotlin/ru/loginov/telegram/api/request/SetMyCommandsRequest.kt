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
import ru.loginov.telegram.api.entity.BotCommand
import ru.loginov.telegram.api.entity.BotCommandScope

/**
 * This object represents request for method [ru.loginov.telegram.api.TelegramAPI.setMyCommands]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class SetMyCommandsRequest {

    /**
     * A JSON-serialized list of bot commands to be set as the list of the bot's commands.
     * At most 100 commands can be specified.
     */
    @JsonProperty(value = "commands", required = true)
    var commands: List<BotCommand>? = null

    /**
     * A JSON-serialized object, describing scope of users for which the commands are relevant.
     * Defaults to [ru.loginov.telegram.api.entity.BotCommandScopeType.DEFAULT].
     */
    @JsonProperty(value = "scope", required = false)
    var scope: BotCommandScope? = null

    /**
     * A two-letter ISO 639-1 language code.
     * If empty, commands will be applied to all users from the given scope, for whose language there are no dedicated commands
     */
    @JsonProperty(value = "language_code", required = false)
    var language: String? = null

}