package kalendar.service

import kalendar.database.InMemoryEventDatabase
import kalendar.domain.*
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain same`
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.LocalTime

class SchedulerTest {
    private val database = InMemoryEventDatabase()
    private val scheduler = Scheduler(database)

    @Test
    fun `scheduling a daily event`() {
        val event = Standup

        scheduler.recurringDaily(event, 5)

        with(database.getAll()) {
            size `should be equal to` 5
            map { it.starts } `should contain same` onEachWeekday(event.starts.toLocalTime())
            map { it.ends } `should contain same` onEachWeekday(event.ends.toLocalTime())
        }
    }

    @Test
    fun `schedule a weekly event`() {
        val expectedEvent = StakeholderSync

        scheduler.recurringWeekly(expectedEvent, 2)

        with(database.getAll()) {
            size `should be equal to` 2
            map { it.starts } `should contain same` onThisWeekAndNext(expectedEvent.starts)
            map { it.ends } `should contain same` onThisWeekAndNext(expectedEvent.ends)
        }
    }

    @Test
    fun `shows whats happening on next Monday`() {
        scheduler.recurringDaily(Standup, 14)
        scheduler.recurringWeekly(StakeholderSync, 2)

        val schedule = scheduler.happeningOn(Monday.plusWeeks(1))

        with(schedule) {
            map { it.title } `should contain same` listOf(Standup.title, StakeholderSync.title)
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

    private fun onThisWeekAndNext(dateTime: LocalDateTime): List<LocalDateTime> {
        return listOf(
            dateTime,
            dateTime.plusWeeks(1)
        )
    }
}
