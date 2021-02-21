package kovalent.domain

import java.time.LocalDate
import java.util.*

data class TodoItem(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: LocalDate
) {
    companion object
}
