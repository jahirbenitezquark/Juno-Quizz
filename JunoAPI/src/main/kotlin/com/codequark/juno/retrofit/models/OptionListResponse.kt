package com.codequark.juno.retrofit.models

import com.codequark.juno.models.Option
import org.springframework.lang.NonNull

data class OptionListResponse(
    @NonNull
    val options: MutableList<Option>
): Response()