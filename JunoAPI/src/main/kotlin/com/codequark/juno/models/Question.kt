package com.codequark.juno.models

import com.codequark.juno.utils.ModelConstants
import org.hibernate.Hibernate
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable
import javax.persistence.*

@Entity(name = ModelConstants.tableQuestions)
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = ModelConstants.columnQuestionId)
    val questionId: Int,

    @NonNull
    @Column(name = ModelConstants.columnQuestion)
    val question: String,

    @NonNull
    @Column(name = ModelConstants.columnAnswerId)
    val answerId: Int,

    @Nullable
    @OneToMany
    @JoinColumn(name = ModelConstants.columnQuestionId, insertable = false, updatable = false)
    val options: List<Option>?,

    @NonNull
    @Column(name = ModelConstants.columnActive)
    val active: Boolean
) {
    constructor(
        @NonNull
        questionId: Int,

        @NonNull
        question: String,

        @NonNull
        answerId: Int,

        @NonNull
        active: Boolean
    ): this(questionId, question, answerId, null, active)

    constructor(
        @NonNull
        question: String,

        @NonNull
        answerId: Int,

        @NonNull
        active: Boolean
    ): this(0, question, answerId, null, active)

    override fun equals(other: Any?): Boolean {
        if(this === other) {
            return true
        }

        if(other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }

        other as Question

        return questionId == other.questionId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + ": (questionId = $questionId, question = $question, answerId = $answerId, options = $options, active = $active)"
    }
}