package it.entefire.squola.services.abstractions

import it.entefire.squola.entities.CourseEdition

interface CourseService {
    fun saveCourseEdition (ce: CourseEdition): CourseEdition
}