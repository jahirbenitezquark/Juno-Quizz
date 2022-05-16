package com.codequark.juno.services.impl

import com.codequark.juno.commons.GenericServiceImpl
import com.codequark.juno.models.Question
import com.codequark.juno.repositories.QuestionRepository
import com.codequark.juno.services.api.QuestionServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.lang.NonNull
import org.springframework.stereotype.Service

@Service
class QuestionServiceImpl: GenericServiceImpl<Question, Int>(), QuestionServiceAPI {
    @Autowired
    private lateinit var repository: QuestionRepository

    @NonNull
    override fun getDao(): CrudRepository<Question, Int> {
        return repository
    }
}