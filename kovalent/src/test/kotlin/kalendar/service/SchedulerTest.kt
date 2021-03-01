package kalendar.service

import kalendar.database.InMemoryEventDatabase
import kalendar.domain.*
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain same`
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.LocalTime

class SchedulerTest {
    private val database = InMemoryEventDatabase()
    private val scheduler = Scheduler(database)

    @Test
    fun `scheduling a daily event`() {
        val event = Event.default.copy(
            title = "Standup",
            starts = Monday.at(9, 0).am(),
            ends = Monday.at(9, 30).am()
        )

        scheduler.recurringDaily(event, 5)

        with(database.getAll()) {
            size `should be equal to` 5
            map { it.starts } `should contain same` onEachWeekday(event.starts.toLocalTime())
            map { it.ends } `should contain same` onEachWeekday(event.ends.toLocalTime())
        }
    }

    private fun onEachWeekday(time: LocalTime): List<LocalDateTime> {
        return listOf(
            Monday.at(time),
            Tuesday.at(time),
            Wednesday.at(time),
            Thursday.at(time),
            Friday.at(time),
        )
    }
}