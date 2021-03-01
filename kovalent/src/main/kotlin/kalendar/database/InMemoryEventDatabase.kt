package kalendar.database

import kalendar.domain.Event

class InMemoryEventDatabase: EventDatabase() {

    private val entries = mutableListOf<Event>()

    override fun create(todo: Event) {
        entries.add(todo)
    }

    override fun getAll(): List<Event> {
        return entries
    }

    fun clearAll() {
        entries.clear()
    }
}
