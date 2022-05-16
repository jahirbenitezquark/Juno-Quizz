package com.codequark.juno.retrofit.models

import com.codequark.juno.models.Question
import org.springframework.lang.NonNull

data class QuestionResponse(
    @NonNull
    val question: Question
): Response()