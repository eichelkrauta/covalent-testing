package kalendar.database

import kalendar.domain.Event
import java.time.LocalDate

abstract class EventDatabase {
    abstract fun create(event: Event)
    abstract fun getAll(): List<Event>
    abstract fun getEventsOnDay(date: LocalDate): List<Event>
}
