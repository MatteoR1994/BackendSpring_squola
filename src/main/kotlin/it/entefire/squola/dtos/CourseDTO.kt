package it.entefire.squola.dtos

import it.entefire.squola.entities.Course

class CourseDTO(var id: Long, var title: String, var numHours: Int, var description: String,
                var cost: Double, var editions: MutableSet<CourseEditionDTO>) {

    fun toCourse(): Course {
        return Course(id,title,numHours,description,cost, mutableSetOf())
    }
}