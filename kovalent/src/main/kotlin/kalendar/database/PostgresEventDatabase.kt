package kalendar.database

import kalendar.domain.Event
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greater
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class PostgresEventDatabase: EventDatabase() {

    companion object {
        private val postgresHost = System.getenv("POSTGRES_HOSTNAME") ?: "localhost"
        private val db by lazy {
            Database.connect("jdbc:postgresql://${postgresHost}:5432/", driver = "org.postgresql.Driver", user = "root", password = "password")

            transaction {
                addLogger(StdOutSqlLogger)

                SchemaUtils.create(EventTable)
            }
        }
    }

    init {
        db
    }

    override fun create(event: Event) {
        transaction {
            EventDao.new {
                title = event.title
                description = event.description
                starts = event.starts
                ends = event.ends
            }
        }
    }

    override fun getAll(): List<Event>
        = transaction {
            EventDao.all().map {
                Event(it.id.value, it.title, it.description, it.starts, it.ends)
            }
        }

    override fun getEventsOnDay(date: LocalDate): List<Event>
        = transaction {
            EventTable.select {
                EventTable.starts greater LocalDateTime.of(date, LocalTime.MIDNIGHT) and
                    (EventTable.starts less LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT))
            }.map {
                Event(
                        it[EventTable.id].value,
                        it[EventTable.title],
                        it[EventTable.description],
                        it[EventTable.starts],
                        it[EventTable.ends]
                )
            }
        }

}
