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

val Standup =
        Event.default.copy(
                title = "Standup",
                starts = Monday.at(9, 0).am(),
                ends = Monday.at(9, 30).am()
        )

val StakeholderSync =
        Event.default.copy(
                title = "Stakeholder Sync",
                starts = Monday.at(10, 0).am(),
                ends = Monday.at(11, 0).am()
        )

val TechHuddle =
        Event.default.copy(
                title = "Tuesday Tech Huddle",
                starts = Tuesday.at(10, 0).am(),
                ends = Tuesday.at(11, 0).am()
        )

val WorkingSession =
        Event.default.copy(
                title = "Wednesday Working Session",
                starts = Wednesday.at(10, 0).am(),
                ends = Wednesday.at(11, 0).am()
        )

