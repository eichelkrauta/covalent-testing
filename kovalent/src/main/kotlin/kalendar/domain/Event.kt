package kalendar.domain

import java.time.LocalDateTime

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val starts: LocalDateTime,
    val ends: LocalDateTime
) {
    companion object
}
