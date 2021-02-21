package kovalent.database

import kovalent.domain.TodoItem

class InMemoryTodoDatabase: TodoDatabase() {
    override fun createTodo(todo: TodoItem) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<TodoItem> {
        TODO("Not yet implemented")
    }
}
