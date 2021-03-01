package it.entefire.squola.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class CourseEdition(@Id var id: Long, var code: String, var start: LocalDate, var end: LocalDate,
                    @JsonIgnore @ManyToOne var course: Course) {
}