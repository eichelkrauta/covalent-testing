package kalendar.database

import kalendar.domain.Event
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

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

    override fun create(todo: Event) {
        transaction {
            EventDao.new {
                title = todo.title
                description = todo.description
                starts = todo.starts
                ends = todo.ends
            }
        }
    }

    override fun getAll(): List<Event>
        = transaction {
            EventDao.all().map {
                Event(it.id.value, it.title, it.description, it.starts, it.ends)
            }
        }

}
