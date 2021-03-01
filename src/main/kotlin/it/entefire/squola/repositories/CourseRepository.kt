package it.entefire.squola.repositories

import it.entefire.squola.entities.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course,Long> {
}