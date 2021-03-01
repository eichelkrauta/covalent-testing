package kalendar.database

import kalendar.domain.Event
import kalendar.domain.default
import org.amshove.kluent.`should contain same`
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class EventDatabaseTest {

    abstract val database: EventDatabase

    @Test
    fun `a todo can be persisted in the database`() {
        val newEvent = Event.default
        val expectedTitle = newEvent.title

        database.create(newEvent)

        val events = database.getAll()
        events.map { it.title } `should contain same` listOf(expectedTitle)
    }
}

class PostgresEventDatabaseTest : EventDatabaseTest() {

    override val database = PostgresEventDatabase()

    @BeforeEach
    fun setup() {
        transaction {
            EventTable.deleteAll()
        }
    }
}

class InMemoryEventDatabaseTest : EventDatabaseTest() {

    override val database = InMemoryEventDatabase()

    @BeforeEach
    fun setup() {
        database.clearAll()
    }
}
