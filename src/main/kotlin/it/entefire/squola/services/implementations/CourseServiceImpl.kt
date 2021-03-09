package it.entefire.squola.services.implementations

import it.entefire.squola.entities.CourseEdition
import it.entefire.squola.repositories.CourseEditionsRepository
import it.entefire.squola.services.abstractions.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CourseServiceImpl : CourseService {
    @Autowired
    lateinit var cer: CourseEditionsRepository

    override fun saveCourseEdition(ce: CourseEdition): CourseEdition {
        val saved = cer.save(ce)
        return saved
    }
}