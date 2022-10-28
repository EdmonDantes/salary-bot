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

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "income_date_month", "income_date_year"])],
    indexes = [Index(columnList = "income_date_year desc, income_date_month desc, user_id")]
)
class SalaryDescription {
    constructor()

    constructor(id: Int?, userId: Long?, bank: String?, salaryValue: Double?, incomeDateYear: Int?, incomeDateMonth: Int?, incomeDateDay: Int?) {
        this.id = id
        this.userId = userId
        this.bank = bank
        this.salaryValue = salaryValue
        this.incomeDateYear = incomeDateYear
        this.incomeDateMonth = incomeDateMonth
        this.incomeDateDay = incomeDateDay
    }

    @Id
    @GeneratedValue
    var id: Int? = null

    @Column(name = "user_id", nullable = false)
    var userId: Long? = null

    @Column(nullable = false)
    var bank: String? = null

    @Column
    var salaryValue: Double? = null

    @Column(name = "income_date_year", nullable = false)
    var incomeDateYear: Int? = null

    @Column(name = "income_date_month", nullable = false)
    var incomeDateMonth: Int? = null

    @Column(nullable = false)
    var incomeDateDay: Int? = null
}