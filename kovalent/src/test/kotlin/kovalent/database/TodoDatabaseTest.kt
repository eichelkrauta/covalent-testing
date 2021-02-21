package kovalent.database

import kovalent.domain.TodoItem
import kovalent.domain.default
import org.amshove.kluent.`should contain same`
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class TodoDatabaseTest {

    abstract val database: TodoDatabase

    @Test
    fun `a todo can be persisted in the database`() {
        val newTodo = TodoItem.default
        val expectedTitle = newTodo.title

        database.createTodo(newTodo)

        val todos = database.getAll()
        val todoTitles = todos.map { it.title }
        todoTitles `should contain same` listOf(expectedTitle)
    }
}

class PostgresTodoDatabaseTest : TodoDatabaseTest() {

    private val postgresDatabase = PostgresTodoDatabase()
    override val database: TodoDatabase = postgresDatabase

    @BeforeEach
    fun setup() {
        transaction {
            TodoItemTable.deleteAll()
        }
    }
}

class InMemoryTodoDatabaseTest : TodoDatabaseTest() {

    private val inMemoryDatabase = InMemoryTodoDatabase()
    override val database: TodoDatabase = inMemoryDatabase

    @BeforeEach
    fun setup() {
        inMemoryDatabase.clearAll()
    }
}
