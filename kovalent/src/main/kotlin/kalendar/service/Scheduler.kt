package kalendar.service

import kalendar.database.EventDatabase
import kalendar.domain.Event
import java.time.Period

class Scheduler(private val database: EventDatabase) {
    fun recurringDaily(event: Event, days: Int) {
        (0 until days).forEach {
            database.create(event.copy(
                starts = event.starts + Period.ofDays(it),
                ends = event.ends + Period.ofDays(it)
            ))
        }
    }
}
