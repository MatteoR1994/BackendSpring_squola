package it.entefire.squola.dtos

class CourseDTO(var id: Long, var title: String, var numHours: Int,
                var cost: Double, var editions: MutableSet<CourseEditionDTO>) {
}