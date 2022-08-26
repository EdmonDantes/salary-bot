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

package ru.loginov.salary.bot.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.loginov.http.HttpClient
import ru.loginov.telegram.api.DefaultTelegramAPI
import ru.loginov.telegram.api.TelegramAPI

@Configuration
@ConditionalOnProperty(name = ["bot.telegram.server.url", "bot.telegram.token"])
class TelegramAPIConfiguration {

    @Value("\${bot.telegram.server.url}")
    private lateinit var telegramServerUrl: String

    @Value("\${bot.telegram.token}")
    private lateinit var telegramToken: String

    @Bean
    fun telegramApi(): TelegramAPI = DefaultTelegramAPI(
        HttpClient(),
        telegramServerUrl + telegramToken
    )
}