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

package ru.loginov.salary.bot.salary

import kotlinx.coroutines.runBlocking
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.loginov.salary.bot.data.SalaryDescription
import ru.loginov.salary.bot.data.SalaryRepository
import ru.loginov.salary.bot.data.UserState
import ru.loginov.salary.bot.data.UserStateRepository
import ru.loginov.salary.bot.telegram.TelegramHandler
import ru.loginov.telegram.api.TelegramAPI
import ru.loginov.telegram.api.entity.Update
import ru.loginov.telegram.api.request.SendMessageRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.TreeMap
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

@Service
class CommandHandler(
    private val salaryRepository: SalaryRepository,
    private val userStateRepo: UserStateRepository,
    private val telegram: TelegramAPI
) : TelegramHandler {

    private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4)

    override fun handle(update: Update) {
        val sender = update.message?.from ?: return
        if (sender.isBot) {
            return
        }

        val chat = update.message?.chat ?: return

        if (chat.type != "private") {
            return
        }

        val userId = sender.id
        val text = update.message?.text ?: return

        var state: UserState? = userStateRepo.findById(userId).orElse(null)


        state = when (text.lowercase()) {
            "/source", "/github" -> {
                CompletableFuture.runAsync({
                    runBlocking {
                        telegram.sendMessage {
                            this.chatId = chat.id
                            markdown2 {
                                url("https://github.com/EdmonDantes/salary-bot")
                            }
                            mainMenu()
                        }
                    }
                }, executor)
                state ?: UserState(userId, null)
            }
            "/start" -> {
                CompletableFuture.runAsync({
                    runBlocking {
                        telegram.sendMessage {
                            this.chatId = chat.id
                            markdown2 {
                                append("?????????? ????????????????????")
                            }
                            mainMenu()
                        }
                    }
                }, executor)
                UserState(userId, null)
            }
            "????????????????", "/add", "add" -> {
                UserState(userId, "add")
            }
            "????????????????????", "/stat", "stat" -> {
                UserState(userId, "stat")
            }
            else -> state?.let {
                UserState(it.userId, it.state?.let { "$it$text" })
            }
        }

        if (state == null) {
            CompletableFuture.runAsync({
                runBlocking {
                    telegram.sendMessage {
                        chatId = chat.id
                        markdown2 {
                            bold {
                                append("???????????????? ??????????????")
                            }
                        }
                        mainMenu()
                    }
                }
            }, executor)

            return
        }

        val states = state.state?.split("|") ?: return
        if (states.isEmpty()) {
            return
        }

        val newState = when (states[0]) {
            "add" -> {
                handleAdd(state, chat.id, states)
            }
            "stat" -> {
                handleStat(state, chat.id, states)
            }
            else -> null
        }

        CompletableFuture.runAsync({
            userStateRepo.save(UserState(state.userId, newState))
        }, executor)
    }

    private fun handleStat(state: UserState, chatId: Long, states: List<String>): String? = if (states.size < 2) {
        CompletableFuture.runAsync({
            runBlocking {
                telegram.sendMessage {
                    this.chatId = chatId
                    markdown2 {
                        append("?????????????? ???????? ?? ?????????????? ")
                        italic {
                            append("<??????????>")
                            bold {
                                append('.')
                            }
                            append("<??????>")
                        }
                    }
                    replyKeyboard {
                        line {
                            add {
                                text = "???????????????????? ??????????"
                            }
                        }
                        line {
                            add {
                                text = "?????????????? ??????????"
                            }
                        }
                        once()
                        placeholder("???????? ?????? ???????????????? ????????????????????")
                    }
                }
            }
        }, executor)
        state.state?.let { "$it|" }
    } else {

        val date = when (states[1].lowercase()) {
            "???????????????????? ??????????", "??????????????", "????????????????????", "prev", "previous" -> {
                MONTH_AND_YEAR_DATE_FORMAT.format(LocalDate.now().minusMonths(1))
            }
            "?????????????? ??????????", "??????????????", "curr", "current" -> {
                MONTH_AND_YEAR_DATE_FORMAT.format(LocalDate.now())
            }
            else -> states[1]
        }.split('.')


        val month = date.getOrNull(0)?.toIntOrNull()
        val year = date.getOrNull(1)?.toIntOrNull()

        if (date.size == 2 && month != null && month > 0 && month < 13 && year != null && year > 2021 && year <= 2025) {
            calculateStat(chatId, month, year)

            null
        } else {
            CompletableFuture.runAsync({
                runBlocking {
                    telegram.sendMessage {
                        this.chatId = chatId
                        markdown2 {
                            bold {
                                append("???????????????????????? ???????????? ????????.\n")
                            }
                            append("?????????????? ???????? ?? ?????????????? ")
                            italic {
                                append("<??????????>")
                                bold {
                                    append('.')
                                }
                                append("<??????>")
                            }
                        }
                        replyKeyboard {
                            line {
                                add {
                                    text = "???????????????????? ??????????"
                                }
                            }
                            line {
                                add {
                                    text = "?????????????? ??????????"
                                }
                            }
                            once()
                            placeholder("???????? ?????? ???????????????? ????????????????????")
                        }
                    }
                }
            }, executor)

            "stat|"
        }
    }

    private fun handleAdd(state: UserState, chatId: Long, states: List<String>): String? = when (states.size) {
        1 -> {
            CompletableFuture.runAsync({
                runBlocking {
                    telegram.sendMessage {
                        this.chatId = chatId
                        markdown2 {
                            append("?????????????? ???????? ???????????????????? ???????????????? ?? ?????????????? ")
                            italic {
                                append("<????????>")
                                bold {
                                    append('.')
                                }
                                append("<??????????>")
                                bold {
                                    append('.')
                                }
                                append("<??????>")
                            }
                        }
                        replyKeyboard {
                            line {
                                add {
                                    text = "??????????????"
                                }
                            }
                            line {
                                add {
                                    text = "??????????"
                                }
                            }
                            once()
                            placeholder("???????? ???????????????????? ????????????????")
                        }
                    }
                }
            }, executor)
            state.state?.let { "$it|" }
        }
        2 -> {
            try {
                val date = when (states[1].lowercase()) {
                    "??????????????", "today" -> LocalDate.now()
                    "??????????", "yesterday" -> LocalDate.now().minusDays(1)
                    else -> LocalDate.parse(states[1], DATE_FORMAT)
                }

                if (date.isAfter(LocalDate.now())
                    || date.isBefore(LocalDate.of(2022, 3, 1))
                ) {
                    throw DateTimeParseException("Specified date is not range", date.format(DATE_FORMAT), 0)
                }

                CompletableFuture.runAsync({
                    val banks = salaryRepository.findTopBanksByCount(Pageable.ofSize(10))
                    runBlocking {
                        telegram.sendMessage {
                            this.chatId = chatId
                            markdown2 {
                                append("?????????????? ?????? ????????")
                            }
                            replyKeyboard {
                                banks.mapNotNull { if (it[0] is String) it[0] as String else null }.distinct()
                                    .chunked(2) {
                                        line {
                                            it.forEach { bankName ->
                                                add {
                                                    text = bankName
                                                }
                                            }
                                        }
                                    }
                                once()
                                placeholder("?????? ????????")
                            }
                        }

                    }
                }, executor)

                states.subList(0, states.size - 1).plus(DATE_FORMAT.format(date)).joinToString("|").plus("|")
            } catch (e: DateTimeParseException) {
                CompletableFuture.runAsync({
                    runBlocking {
                        telegram.sendMessage {
                            this.chatId = chatId
                            markdown2 {
                                bold {
                                    append("???????????????????????? ???????????? ????????.\n")
                                }
                                append("?????????????? ???????? ???????????????????? ???????????????? ?? ?????????????? ")
                                italic {
                                    append("<????????>")
                                    bold {
                                        append('.')
                                    }
                                    append("<??????????>")
                                    bold {
                                        append('.')
                                    }
                                    append("<??????>")
                                }
                            }
                            replyKeyboard {
                                line {
                                    add {
                                        text = "??????????????"
                                    }
                                }
                                line {
                                    add {
                                        text = "??????????"
                                    }
                                }
                                once()
                                placeholder("???????? ???????????????????? ????????????????")
                            }
                        }
                    }
                }, executor)
                states.subList(0, states.size - 1).joinToString("|").plus("|")
            }
        }
        3 -> {
            CompletableFuture.runAsync({
                runBlocking {
                    telegram.sendMessage {
                        this.chatId = chatId
                        markdown2 {
                            append("???????????? ???? ?????????????? ???????????? ?????????? ?????????????????")
                        }
                        replyKeyboard {
                            line {
                                add {
                                    text = "????"
                                }
                                add {
                                    text = "??????"
                                }
                            }
                            once()
                        }
                    }
                }
            }, executor)

            state.state.let { "$it|" }
        }
        else -> {
            val shouldGetSalary = YES_ARRAY.contains(states.getOrNull(3)?.lowercase())

            if (shouldGetSalary && states.size < 5) {
                CompletableFuture.runAsync({
                    runBlocking {
                        telegram.sendMessage {
                            this.chatId = chatId
                            markdown2 {
                                append("?????????????? ???????? ???????????????? ?? ??????????????")
                            }
                        }
                    }
                }, executor)
                state.state.let { "$it|" }
            } else {

                val date = LocalDate.parse(states[1], DATE_FORMAT)
                val bankName = states[2]
                val salary = if (shouldGetSalary) states.getOrNull(4)?.toDoubleOrNull() else null

                if (salary != null && salary < 0.0) {
                    CompletableFuture.runAsync({
                        runBlocking {
                            telegram.sendMessage {
                                this.chatId = chatId
                                markdown2 {
                                    append("???????????????? ???????????? ???????? ???????????? 0")
                                }
                            }
                        }
                    }, executor)
                    states.subList(0, 4).joinToString("|").plus("|")
                } else {

                    val shouldRewrite = YES_ARRAY.contains(states.getOrNull(if (shouldGetSalary) 5 else 4)?.lowercase())

                    if (states.size > (if (shouldGetSalary) 5 else 4) && !shouldRewrite) {
                        CompletableFuture.runAsync({
                            runBlocking {
                                telegram.sendMessage {
                                    this.chatId = chatId
                                    markdown2 {
                                        append("???????? ???????????? ???? ???????? ????????????????")
                                    }
                                    mainMenu()
                                }
                            }
                        }, executor)
                        null
                    } else {
                        val record = salaryRepository.findByUserIdAndIncomeDateYearAndIncomeDateMonth(
                            state.userId!!,
                            date.year,
                            date.monthValue
                        ).orElse(null)

                        if (record == null || shouldRewrite) {
                            try {
                                salaryRepository.save(
                                    SalaryDescription(
                                        record?.id,
                                        state.userId,
                                        bankName,
                                        salary,
                                        date.year,
                                        date.monthValue,
                                        date.dayOfMonth
                                    )
                                )

                                CompletableFuture.runAsync({
                                    runBlocking {
                                        telegram.sendMessage {
                                            this.chatId = chatId
                                            markdown2 {
                                                append("?????? ?????????? ??????????????")
                                            }
                                            mainMenu()
                                        }
                                    }
                                }, executor)
                            } catch (e: Exception) {
                                CompletableFuture.runAsync({
                                    runBlocking {
                                        telegram.sendMessage {
                                            this.chatId = chatId
                                            markdown2 {
                                                append("?????????????????? ???????????? ?????? ????????????????????, ???????????????????? ?????? ??????")
                                            }
                                            mainMenu()
                                        }
                                    }
                                }, executor)
                                e.printStackTrace()
                            }
                            null
                        } else {
                            CompletableFuture.runAsync({
                                runBlocking {
                                    telegram.sendMessage {
                                        this.chatId = chatId
                                        markdown2 {
                                            append("???? ?????? ?????????????? ???????????? ???? ???????? ??????????. ?????????????? ?????? ???????????? ???????????????????????? ?????")
                                        }
                                        replyKeyboard {
                                            line {
                                                add {
                                                    text = "????"
                                                }
                                                add {
                                                    text = "??????"
                                                }
                                            }
                                            once()
                                            placeholder("?????????????????????????")
                                        }
                                    }
                                }
                            }, executor)

                            state.state?.let { "$it|" }
                        }

                    }
                }
            }
        }
    }

    private fun calculateStat(chatId: Long, month: Int, year: Int) {

        val stats = TreeMap<Int, MutableMap<String, MutableList<SalaryDescription>>>()
        var salaryAvrg: Double? = null
        var salaryValueCount = 0

        salaryRepository.findAllByIncomeDateYearAndIncomeDateMonth(year, month).forEach {
            stats.computeIfAbsent(it.incomeDateDay!!) { TreeMap() }.computeIfAbsent(it.bank!!) { ArrayList() }.add(it)
            if (it.salaryValue != null) {
                if (salaryAvrg == null) {
                    salaryAvrg = it.salaryValue
                    salaryValueCount++
                } else {
                    salaryAvrg = salaryAvrg!! + it.salaryValue!!
                    salaryValueCount++
                }
            }
        }

        CompletableFuture.runAsync({
            runBlocking {
                telegram.sendMessageWithoutLimit {
                    this.chatId = chatId
                    markdown2 {
                        append(
                            "???????????????????? ???? '${
                                LocalDate.of(year, month, 1).format(MONTH_AND_YEAR_DATE_FORMAT)
                            }' ???? '${LocalDate.now().format(DATE_FORMAT)}'"
                        )

                        if (salaryAvrg != null && salaryValueCount > 0) {
                            append('\n')
                            append("?????????????? ???????????????? ???? ??????????: ")
                            append(String.format("%.2f", salaryAvrg!! / salaryValueCount))
                        }

                        stats.forEach { (day, records) ->
                            append("\n\n$day:")

                            records.forEach { (bankName, count) ->
                                append("\n$bankName: ${count.size}")
                            }
                        }
                    }
                    mainMenu()
                }
            }
        }, executor)
    }

    private fun SendMessageRequest.mainMenu() {
        replyKeyboard {
            line {
                add {
                    text = "????????????????"
                }
            }
            line {
                add {
                    text = "????????????????????"
                }
            }
        }

    }

    companion object {
        private val MONTH_AND_YEAR_DATE_FORMAT = DateTimeFormatter.ofPattern("MM.yyyy")
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        private val YES_ARRAY = listOf("yes", "y", "????", "??", "true", "t", "1")
    }

}