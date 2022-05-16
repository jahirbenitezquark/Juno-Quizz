package com.codequark.juno.utils

class Constants {
    @Retention(AnnotationRetention.SOURCE)
    @StringDef(
        // Response
        JsonConstants.code,
        JsonConstants.message,
        JsonConstants.response,

        // General
        JsonConstants.action,

        // Models
        JsonConstants.encuesta,

        JsonConstants.pregunta,
        JsonConstants.preguntas,

        JsonConstants.seccion,

        JsonConstants.tipoPregunta,

        // Encuesta
        JsonConstants.encuestaId,

        // Opcion
        JsonConstants.habilitado,

        // Pregunta
        JsonConstants.preguntaId,
        JsonConstants.numeroPregunta,
        JsonConstants.orden,
        JsonConstants.descripcion,
        JsonConstants.obligatorio,

        // Seccion
        JsonConstants.seccionId,
        JsonConstants.numeroSeccion,

        // TipoPregunta
        JsonConstants.tipoPreguntaId
    )
    annotation class JsonConstants {
        companion object {
            // Response
            const val code = "code"
            const val message = "message"
            const val response = "response"

            // General
            const val action = "action"

            // Models
            const val encuesta = "encuesta"

            const val pregunta = "pregunta"
            const val preguntas = "preguntas"

            const val seccion = "seccion"

            const val tipoPregunta = "tipoPregunta"

            // Encuesta
            const val encuestaId = "encuestaId"

            // Opción
            const val habilitado = "habilitado"

            // Pregunta
            const val preguntaId = "preguntaId"
            const val numeroPregunta = "numeroPregunta"
            const val orden = "orden"
            const val descripcion = "descripcion"
            const val obligatorio = "obligatorio"

            // Seccion
            const val seccionId = "seccionId"
            const val numeroSeccion = "numeroSeccion"

            // TipoPregunta
            const val tipoPreguntaId = "tipoPreguntaId"
        }
    }
}