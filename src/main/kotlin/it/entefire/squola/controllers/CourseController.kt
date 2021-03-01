package it.entefire.squola.controllers

import it.entefire.squola.entities.Course
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
    fun allCourses(): MutableIterable<Course> {
        return courseRepo.findAll()
    }
}