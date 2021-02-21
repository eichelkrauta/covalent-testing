package kovalent.database

import kovalent.domain.TodoItem

abstract class TodoDatabase {

    abstract fun createTodo(todo: TodoItem)
    abstract fun getAll(): List<TodoItem>
}
