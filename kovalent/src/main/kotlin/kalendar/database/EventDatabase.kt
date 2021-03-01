package kalendar.database

import kalendar.domain.Event

abstract class EventDatabase {
    abstract fun create(todo: Event)
    abstract fun getAll(): List<Event>
}
