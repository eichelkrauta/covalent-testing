package kalendar.database

import kalendar.domain.Event
import java.time.LocalDate

class InMemoryEventDatabase: EventDatabase() {

    private val entries = mutableListOf<Event>()

    override fun create(event: Event) {
        entries.add(event)
    }

    override fun getAll(): List<Event> {
        return entries
    }

    override fun getEventsOnDay(date: LocalDate): List<Event> {
        return entries.filter {
            it.starts.isAfter(date.atStartOfDay()) && it.starts.isBefore(date.plusDays(1).atStartOfDay())
        }.toList()
    }

    fun clearAll() {
        entries.clear()
    }
}
