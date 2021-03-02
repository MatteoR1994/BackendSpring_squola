package it.entefire.squola.extensions

import it.entefire.squola.dtos.CourseDTO
import it.entefire.squola.dtos.CourseEditionDTO
import it.entefire.squola.entities.Course
import it.entefire.squola.entities.CourseEdition

fun Course.toCourseDTO(): CourseDTO {
    return CourseDTO(this.id,this.title,this.numHours,this.cost,
        this.editions.map { it.toCourseEditionDTO() }.toMutableSet())
}

fun CourseEdition.toCourseEditionDTO(): CourseEditionDTO {
    return CourseEditionDTO(this.id,this.code,this.start,this.end,this.course.id,this.course.title)
}