package it.entefire.squola.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Course(@Id var id: Long, var title: String, var numHours: Int, var description: String,
             var cost: Double, @OneToMany(mappedBy = "course") var editions: MutableSet<CourseEdition>) {
}