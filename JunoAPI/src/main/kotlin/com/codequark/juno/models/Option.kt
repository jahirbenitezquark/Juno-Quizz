package com.codequark.juno.models

import com.codequark.juno.utils.ModelConstants
import org.hibernate.Hibernate
import org.springframework.lang.NonNull
import javax.persistence.*

@Entity(name = ModelConstants.tableOptions)
data class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = ModelConstants.columnOptionId)
    val optionId: Int,

    @NonNull
    @Column(name = ModelConstants.columnOption)
    val option: String,

    @NonNull
    @Column(name = ModelConstants.columnQuestionId)
    val questionId: Int,

    @NonNull
    @Column(name = ModelConstants.columnActive)
    val active: Boolean
) {
    constructor(
        @NonNull
        option: String,

        @NonNull
        questionId: Int,

        @NonNull
        active: Boolean
    ): this(0, option, questionId, active)

    override fun equals(other: Any?): Boolean {
        if(this === other) {
            return true
        }

        if(other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }

        other as Option

        return optionId == other.optionId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + ": (optionId = $optionId, option = $option, questionId = $questionId, active = $active)"
    }
}