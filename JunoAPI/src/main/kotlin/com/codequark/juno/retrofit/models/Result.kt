package com.codequark.juno.retrofit.models

import com.codequark.juno.retrofit.RetrofitConstants.ResultCodeDef
import com.codequark.juno.retrofit.RetrofitConstants.ResultDescriptionDef
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable

data class Result(
    @ResultCodeDef
    val code: Int = 0,

    @NonNull
    @ResultDescriptionDef
    val message: String,

    @Nullable
    val response: Response?
)