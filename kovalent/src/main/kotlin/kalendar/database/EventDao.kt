package kalendar.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object EventTable: IntIdTable() {
    val title = varchar("title", 250)
    val description = varchar("description", 1000)
    val starts = datetime("starts")
    val ends = datetime("ends")
}

class EventDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<EventDao>(EventTable)

    var title by EventTable.title
    var description by EventTable.description
    var starts by EventTable.starts
    var ends by EventTable.ends
}
