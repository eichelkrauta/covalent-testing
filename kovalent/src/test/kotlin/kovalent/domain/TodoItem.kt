package kovalent.domain

import java.time.LocalDate
import java.util.*

val TodoItem.Companion.default get() = TodoItem(Random().nextInt(), "Some Todo Item", "Some Description", LocalDate.of(2020, 1, 1))
