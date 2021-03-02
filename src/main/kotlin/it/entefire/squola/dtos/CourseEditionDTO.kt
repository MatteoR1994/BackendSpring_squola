package it.entefire.squola.dtos

import it.entefire.squola.entities.Course
import it.entefire.squola.entities.CourseEdition
import java.time.LocalDate

class CourseEditionDTO(var id: Long, var code: String, var start: LocalDate, var end: LocalDate,
                       var courseId: Long, var courseTitle: String) {

    fun toEdition(): CourseEdition {
        return CourseEdition(id,code,start,end,null)
    }
}