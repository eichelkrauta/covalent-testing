package kovalent.database

import kovalent.domain.TodoItem

class InMemoryTodoDatabase: TodoDatabase() {

    private val todos = mutableListOf<TodoItem>()

    override fun createTodo(todo: TodoItem) {
        todos.add(todo)
    }

    override fun getAll(): List<TodoItem> {
        return todos
    }

    fun clearAll() {
        todos.clear()
    }
}
