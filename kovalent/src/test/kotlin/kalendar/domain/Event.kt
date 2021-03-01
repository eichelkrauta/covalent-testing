package kalendar.domain

import java.time.LocalDateTime
import java.util.*

val Event.Companion.default get() = Event(
    Random().nextInt(),
    "Some Todo Item",
    "Some Description",
    LocalDateTime.of(2020, 1, 1, 13, 0),
    LocalDateTime.of(2020, 1, 1, 14, 0)
)
