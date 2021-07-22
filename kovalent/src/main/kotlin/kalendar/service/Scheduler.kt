package kalendar.service

import kalendar.database.EventDatabase
import kalendar.domain.Event
import java.time.LocalDate
import java.time.Period

class Scheduler(private val database: EventDatabase) {

    fun recurringDaily(event: Event, days: Int) = recurring(event, Period.ofDays(1), days)

    fun recurringWeekly(event: Event, weeks: Int) = recurring(event, Period.ofWeeks(1), weeks)

    fun happeningOn(date: LocalDate): List<Event> {
        return database.getEventsOnDay(date)
    }

    private fun recurring(event: Event, period: Period, times: Int) {
        (0 until times).forEach {
            database.create(event.copy(
                starts = event.starts + period.multipliedBy(it),
                ends = event.ends + period.multipliedBy(it)
            ))
        }
    }

}
