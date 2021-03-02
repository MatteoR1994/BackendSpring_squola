package it.entefire.squola.entities

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class CourseEdition(@Id var id: Long, var code: String, var start: LocalDate, var end: LocalDate,
                    @ManyToOne var course: Course?) {
}