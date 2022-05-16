package com.codequark.juno.models

import com.codequark.juno.retrofit.RetrofitConstants.ResultCodeDef
import com.codequark.juno.retrofit.RetrofitConstants.ResultDescriptionDef
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable

data class Response<Model>(
    @NonNull
    val code: Int,

    @Nullable
    val message: String?,

    @Nullable
    val response: Model?
) {
    constructor(
        @NonNull
        code: Int,
        @NonNull
        message: String,
    ): this(
        code,
        message,
        null
    )

    constructor(
        @NonNull
        error: String,
    ): this(
        ResultCodeDef.ERROR_CODE,
        error,
        null
    )

    constructor(
        @NonNull
        response: Model
    ): this(
        ResultCodeDef.SUCCESS_CODE,
        ResultDescriptionDef.SUCCESS_CODE,
        response
    )
}