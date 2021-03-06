package it.entefire.squola.controllers

import it.entefire.squola.dtos.CourseDTO
import it.entefire.squola.dtos.CourseEditionDTO
import it.entefire.squola.entities.Course
import it.entefire.squola.extensions.toCourseDTO
import it.entefire.squola.extensions.toCourseEditionDTO
import it.entefire.squola.repositories.CourseEditionsRepository
import it.entefire.squola.repositories.CourseRepository
import it.entefire.squola.services.abstractions.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/course")
class CourseController {
    @Autowired
    lateinit var courseRepo: CourseRepository

    @Autowired
    lateinit var courseService: CourseService

    @Autowired
    lateinit var courseEditionsRepo: CourseEditionsRepository

    @GetMapping("/")
    fun allCourses(): ResponseEntity<MutableIterable<CourseDTO>> {
        //return courseRepo.findAll()
        //return courseRepo.findAll().map { it.toCourseDTO() }.toMutableSet()
        var courseEntities = courseRepo.findAll()
        var courseDTOs = mutableSetOf<CourseDTO>()
        for(c in courseEntities) {
            var courseEditionDTOs = mutableSetOf<CourseEditionDTO>()
            for(ce in c.editions) {
                courseEditionDTOs.add(CourseEditionDTO(ce.id,ce.code,ce.start,ce.end,c.id,c.title))
            }
            courseDTOs.add(CourseDTO(c.id,c.title,c.numHours,c.description,c.cost,courseEditionDTOs))
        }
        return ResponseEntity(courseDTOs,HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable id: Long): ResponseEntity<Void> {
        courseRepo.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/")
    fun addCourse(@RequestBody cDTO: CourseDTO): ResponseEntity<CourseDTO> {
        val result = courseRepo.save(cDTO.toCourse())
        //return result.toCourseDTO()
        return ResponseEntity(result.toCourseDTO(),HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCourse(@RequestBody cDTO: CourseDTO, @PathVariable id: Long): ResponseEntity<CourseDTO> {
        if(id != cDTO.id) {
            throw IllegalArgumentException("ID corso non valido.")
        }
        val result = courseRepo.save(cDTO.toCourse())
        return ResponseEntity(result.toCourseDTO(),HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findCourseById(@PathVariable id: Long): ResponseEntity<CourseDTO> {
        val courseOptional = courseRepo.findById(id)
        if(!courseOptional.isPresent) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val course = courseOptional.get()
        return ResponseEntity(course.toCourseDTO(),HttpStatus.OK)
    }

    @GetMapping("/{id}/editions")
    fun getEditionsByCourseId(@PathVariable id: Long): ResponseEntity<MutableSet<CourseEditionDTO>> {
        val courseOptional = courseRepo.findById(id)
        if(!courseOptional.isPresent) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val course = courseOptional.get()
        val editions = course.editions
        return ResponseEntity(editions.map { it.toCourseEditionDTO() }
            .toMutableSet(),HttpStatus.OK)
    }

    @PostMapping("/{id}/editions")
    fun addCourseEdition(@RequestBody ceDTO: CourseEditionDTO, @PathVariable id: Long): ResponseEntity<CourseEditionDTO> {
        val courseOptional = courseRepo.findById(id)
        if(!courseOptional.isPresent) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val course = courseOptional.get()
        val edition = ceDTO.toEdition()
        edition.course = course
        val saved = courseService.saveCourseEdition(edition)
        return ResponseEntity(saved.toCourseEditionDTO(),HttpStatus.CREATED)
    }
}