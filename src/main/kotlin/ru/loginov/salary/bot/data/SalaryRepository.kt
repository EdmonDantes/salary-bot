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

package ru.loginov.salary.bot.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface SalaryRepository : JpaRepository<SalaryDescription, Int> {

    fun findByUserIdAndIncomeDateYearAndIncomeDateMonth(
        userId: Long,
        year: Int,
        month: Int
    ): Optional<SalaryDescription>

    fun findAllByIncomeDateYearAndIncomeDateMonth(year: Int, month: Int): List<SalaryDescription>

    @Query("SELECT dto.bank, COUNT(dto.id) AS bank_count FROM SalaryDescription dto GROUP BY dto.bank ORDER BY bank_count")
    fun findTopBanksByCount(pageable: Pageable) : Page<Array<Any>>

}