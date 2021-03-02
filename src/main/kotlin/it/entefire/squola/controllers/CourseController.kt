package it.entefire.squola.controllers

import it.entefire.squola.dtos.CourseDTO
import it.entefire.squola.dtos.CourseEditionDTO
import it.entefire.squola.entities.Course
import it.entefire.squola.extensions.toCourseDTO
import it.entefire.squola.repositories.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/course")
class CourseController {
    @Autowired
    lateinit var courseRepo: CourseRepository

    @GetMapping("/")
    fun allCourses(): MutableIterable<CourseDTO> {
        //return courseRepo.findAll()
        //return courseRepo.findAll().map { it.toCourseDTO() }.toMutableSet()
        var courseEntities = courseRepo.findAll()
        var courseDTOs = mutableSetOf<CourseDTO>()
        for(c in courseEntities) {
            var courseEditionDTOs = mutableSetOf<CourseEditionDTO>()
            for(ce in c.editions) {
                courseEditionDTOs.add(CourseEditionDTO(ce.id,ce.code,ce.start,ce.end,c.id,c.title))
            }
            courseDTOs.add(CourseDTO(c.id,c.title,c.numHours,c.cost,courseEditionDTOs))
        }
        return courseDTOs
    }
}