package kovalent.database

import kovalent.domain.TodoItem
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class PostgresTodoDatabase: TodoDatabase() {

    companion object {
        private val postgresHost = System.getenv("POSTGRES_HOSTNAME") ?: "localhost"
        private val db by lazy {
            Database.connect("jdbc:postgresql://${postgresHost}:5432/", driver = "org.postgresql.Driver", user = "root", password = "password")

            transaction {
                addLogger(StdOutSqlLogger)

                SchemaUtils.create(TodoItemTable)
            }
        }
    }

    init {
        db
    }

    override fun createTodo(todo: TodoItem) {
        transaction {
            TodoItemDAO.new {
                title = todo.title
                description = todo.description
                dueDate = todo.dueDate.toString()
            }
        }
    }

    override fun getAll(): List<TodoItem>
        = transaction {
            TodoItemDAO.all().map {
                TodoItem(it.id.value, it.title, it.description, LocalDate.parse(it.dueDate))
            }
        }

}
