package com.codequark.juno.repositories

import com.codequark.juno.models.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository: CrudRepository<Question, Int>