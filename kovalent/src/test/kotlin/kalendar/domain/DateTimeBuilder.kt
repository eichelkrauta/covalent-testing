package kalendar.domain

import java.time.*

private fun dateBuilder(month: Month): (Int, Int) -> LocalDate = { day: Int, year: Int ->
    LocalDate.of(year, month, day)
}

val Jan = dateBuilder(Month.JANUARY)
val Feb = dateBuilder(Month.FEBRUARY)
val Mar = dateBuilder(Month.MARCH)
val Apr = dateBuilder(Month.APRIL)
val May = dateBuilder(Month.MAY)
val Jun = dateBuilder(Month.JUNE)
val Jul = dateBuilder(Month.JULY)
val Aug = dateBuilder(Month.AUGUST)
val Sep = dateBuilder(Month.SEPTEMBER)
val Oct = dateBuilder(Month.OCTOBER)
val Nov = dateBuilder(Month.NOVEMBER)
val Dec = dateBuilder(Month.DECEMBER)

fun LocalDate.at(hour: Int, minute: Int = 0, second: Int = 0) = LocalDateTime.of(this, LocalTime.of(hour, minute, second))
fun LocalDate.at(time: LocalTime) = LocalDateTime.of(this, time)
fun LocalDateTime.am() = this
fun LocalDateTime.pm() = this.plusHours(12)

val Monday = Feb(1, 2021)
val Tuesday = Monday + Period.ofDays(1)
val Wednesday = Monday + Period.ofDays(2)
val Thursday = Monday + Period.ofDays(3)
val Friday = Monday + Period.ofDays(4)
