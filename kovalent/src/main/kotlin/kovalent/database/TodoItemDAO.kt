package kovalent.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TodoItemTable: IntIdTable() {
    val title = varchar("title", 250)
    val description = varchar("description", 1000)
    val dueDate = varchar("dueDate", 100)
}

class TodoItemDAO(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TodoItemDAO>(TodoItemTable)

    var title by TodoItemTable.title
    var description by TodoItemTable.description
    var dueDate by TodoItemTable.dueDate
}
