package kovalent.database

import kovalent.domain.TodoItem
import kovalent.domain.default
import org.amshove.kluent.`should contain same`
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

abstract class TodoDatabaseTest {

    abstract val database: TodoDatabase

    @Disabled
    @Test
    fun `a todo can be persisted in the database`() {
        val newTodo = TodoItem.default
        val expectedId = newTodo.id

        database.createTodo(newTodo)

        val todos = database.getAll()
        val todoIds = todos.map { it.id }
        todoIds `should contain same` listOf(expectedId)
    }
}

class PostgresTodoDatabaseTest : TodoDatabaseTest() {
    override val database: TodoDatabase
        get() = PostgresTodoDatabase()
}

class InMemoryTodoDatabaseTest : TodoDatabaseTest() {
    override val database: TodoDatabase
        get() = InMemoryTodoDatabase()
}
