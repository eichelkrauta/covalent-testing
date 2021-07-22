package kalendar.database

import kalendar.domain.*
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain same`
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class EventDatabaseTest {

    abstract val database: EventDatabase

    @Test
    fun `an event can be persisted in the database`() {
        val newEvent = Event.default
        val expectedTitle = newEvent.title

        database.create(newEvent)

        val events = database.getAll()
        events.map { it.title } `should contain same` listOf(expectedTitle)
    }

    @Test
    fun `a list of events can be queried by the day`() {
        database.create(Standup)
        database.create(TechHuddle)
        database.create(WorkingSession)

        with(database.getEventsOnDay(Tuesday)) {
            map { it.title } `should contain same` listOf(TechHuddle.title)
        }
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
