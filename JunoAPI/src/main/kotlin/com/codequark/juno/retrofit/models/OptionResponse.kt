package com.codequark.juno.retrofit.models

import com.codequark.juno.models.Option
import org.springframework.lang.NonNull

data class OptionResponse(
    @NonNull
    val option: Option
): Response()