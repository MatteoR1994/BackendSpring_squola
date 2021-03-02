package it.entefire.squola.dtos

import java.time.LocalDate

class CourseEditionDTO(var id: Long, var code: String, var start: LocalDate, var end: LocalDate,
                       var courseId: Long, var courseTitle: String) {
}