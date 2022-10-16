package com.udacity.asteroidradar.shared.date

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


object AppDate {
    const val DAYS = 7L

    enum class Format(val value: String) {
        YYYY_MM_DD("yyyy-MM-dd")
    }

    fun format(
        date: LocalDate,
        format: Format = Format.YYYY_MM_DD
    ): String {
        return date.format(DateTimeFormatter.ofPattern(format.value))
    }

    fun format(
        date: LocalDate,
        format: DateTimeFormatter
    ): String {
        return date.format(format)
    }

    fun nextWeekDates(): ArrayList<String> {
        val formattedDateList = ArrayList<String>()

        val date = LocalDate.now().minusDays(1)
        for (i in 0..DAYS) {
            val d = format(date.plusDays(i + 1))
            formattedDateList.add(d)
        }

        return formattedDateList
    }
}