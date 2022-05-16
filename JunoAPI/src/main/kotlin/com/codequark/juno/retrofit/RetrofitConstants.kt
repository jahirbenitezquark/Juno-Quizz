package com.codequark.juno.retrofit

import com.codequark.juno.utils.IntDef
import com.codequark.juno.utils.StringDef

class RetrofitConstants {
    // Success or error codes for API
    @Retention(AnnotationRetention.SOURCE)
    @IntDef( // General codes for API
        ResultCodeDef.DEFAULT_RESULT_CODE,  // Success or error codes for API
        ResultCodeDef.SUCCESS_CODE,
        ResultCodeDef.ERROR_CODE,
        ResultCodeDef.ERROR_CODE_MISSING_PARAMS,
        ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE,
        ResultCodeDef.ERROR_CODE_NOT_AUTHENTICATED,
        ResultCodeDef.ERROR_CODE_UNKNOWN_ACTION,
        ResultCodeDef.ERROR_CODE_DUPLICATE_ENTRY,  // General for login errors
        ResultCodeDef.ERROR_CODE_PASSWORD,  // Error codes for model
        ResultCodeDef.ERROR_CODE_EXISTS,
        ResultCodeDef.ERROR_CODE_NOT_EXISTS,
        ResultCodeDef.ERROR_CODE_ENABLED,
        ResultCodeDef.ERROR_CODE_DISABLED
    )
    annotation class ResultCodeDef {
        companion object {
            // General codes for API
            const val DEFAULT_RESULT_CODE = -999

            // Success or error codes for API
            const val SUCCESS_CODE = 0
            const val ERROR_CODE = -1
            const val ERROR_CODE_MISSING_PARAMS = -2
            const val ERROR_CODE_CONNECTION_DATABASE = -3
            const val ERROR_CODE_NOT_AUTHENTICATED = -4
            const val ERROR_CODE_UNKNOWN_ACTION = -5
            const val ERROR_CODE_DUPLICATE_ENTRY = -6

            // General for login errors
            const val ERROR_CODE_PASSWORD = -32

            // Error codes for model
            const val ERROR_CODE_EXISTS = -50
            const val ERROR_CODE_NOT_EXISTS = -51
            const val ERROR_CODE_ENABLED = -52
            const val ERROR_CODE_DISABLED = -53
        }
    }

    // Success or error codes for API
    @Retention(AnnotationRetention.SOURCE)
    @StringDef( // General codes for API
        ResultDescriptionDef.DEFAULT_RESULT_CODE,  // Success or error codes for API
        ResultDescriptionDef.SUCCESS_CODE,
        ResultDescriptionDef.ERROR_CODE,
        ResultDescriptionDef.ERROR_CODE_MISSING_PARAMS,
        ResultDescriptionDef.ERROR_CODE_CONNECTION_DATABASE,
        ResultDescriptionDef.ERROR_CODE_NOT_AUTHENTICATED,
        ResultDescriptionDef.ERROR_CODE_UNKNOWN_ACTION,
        ResultDescriptionDef.ERROR_CODE_DUPLICATE_ENTRY,  // General for login errors
        ResultDescriptionDef.ERROR_CODE_PASSWORD,  // Error codes for model
        ResultDescriptionDef.ERROR_CODE_EXISTS,
        ResultDescriptionDef.ERROR_CODE_NOT_EXISTS,
        ResultDescriptionDef.ERROR_CODE_ENABLED,
        ResultDescriptionDef.ERROR_CODE_DISABLED
    )
    annotation class ResultDescriptionDef {
        companion object {
            // General codes for API
            const val DEFAULT_RESULT_CODE = "resultMessageDefault"

            // Success or error codes for API
            const val SUCCESS_CODE = "success"
            const val ERROR_CODE = "error"
            const val ERROR_CODE_MISSING_PARAMS = "error_missing_params"
            const val ERROR_CODE_CONNECTION_DATABASE = "error_connection_database"
            const val ERROR_CODE_NOT_AUTHENTICATED = "error_not_authenticated"
            const val ERROR_CODE_UNKNOWN_ACTION = "error_unknown_action"
            const val ERROR_CODE_DUPLICATE_ENTRY = "duplicate_entry"

            // General for login errors
            const val ERROR_CODE_PASSWORD = "error_password"

            // Error codes for model
            const val ERROR_CODE_EXISTS = "error_exists"
            const val ERROR_CODE_NOT_EXISTS = "error_not_exists"
            const val ERROR_CODE_ENABLED = "error_enabled"
            const val ERROR_CODE_DISABLED = "error_disabled"
        }
    }
}