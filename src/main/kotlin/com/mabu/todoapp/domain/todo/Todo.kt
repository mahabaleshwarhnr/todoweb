package com.mabu.todoapp.domain.todo

import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Entity
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,
    @Column(nullable = false)
    @NotBlank(message = "name field cannot be blank")
    @Min(message = "Name field must have mimumum 3 characters long", value = 3)
    val name: String,
    val description: String?,

    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @Column(nullable = false)
    val dueDate: LocalDateTime,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    val priority: Priority,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Todo

        return id == other.id
    }

    override fun hashCode(): Int = 177241809

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , description = $description , createdAt = $createdAt , dueDate = $dueDate , priority = $priority )"
    }
}
