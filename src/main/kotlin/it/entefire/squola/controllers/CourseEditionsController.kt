package it.entefire.squola.controllers

import it.entefire.squola.repositories.CourseEditionsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/edition")
class CourseEditionsController {
    @Autowired
    lateinit var courseEditionsRepo: CourseEditionsRepository

    // Cancellare edizione da id
    @DeleteMapping("/{id}")
    fun deleteCourseEdition(@PathVariable id: Long): ResponseEntity<Void> {
        courseEditionsRepo.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}