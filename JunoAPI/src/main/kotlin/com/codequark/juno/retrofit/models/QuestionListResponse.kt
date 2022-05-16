package com.codequark.juno.retrofit.models

import com.codequark.juno.models.Question
import org.springframework.lang.NonNull

data class QuestionListResponse(
    @NonNull
    val questions: MutableList<Question>
): Response()