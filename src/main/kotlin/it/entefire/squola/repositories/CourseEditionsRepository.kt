package it.entefire.squola.repositories

import it.entefire.squola.entities.CourseEdition
import org.springframework.data.repository.CrudRepository

interface CourseEditionsRepository : CrudRepository<CourseEdition, Long> {
}