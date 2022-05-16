package com.codequark.juno.controllers

import com.codequark.juno.models.Question
import com.codequark.juno.models.Response
import com.codequark.juno.repositories.QuestionRepository
import com.codequark.juno.retrofit.RetrofitConstants.ResultCodeDef
import com.codequark.juno.retrofit.RetrofitConstants.ResultDescriptionDef
import com.codequark.juno.retrofit.models.QuestionListResponse
import com.codequark.juno.retrofit.models.QuestionResponse
import com.codequark.juno.services.api.QuestionServiceAPI
import com.codequark.juno.utils.Endpoints
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Endpoints.questionUrl)
@CrossOrigin("*")
class QuestionController {
    @Autowired
    private lateinit var service: QuestionServiceAPI

    @Autowired
    private lateinit var repository: QuestionRepository

    @NonNull
    private val logger: Logger = LoggerFactory.getLogger(QuestionController::class.java)

    @GetMapping("/all")
    fun getAll(@RequestHeader headers: Map<String, String>): ResponseEntity<Response<QuestionListResponse>> {
        return try {
            printHeaders(headers)

            val result: MutableList<Question>? = service.getAll()

            val response: Response<QuestionListResponse> = if(result != null) {
                val content = QuestionListResponse(result)
                Response(content)
            } else {
                handleError()
            }

            ResponseEntity(response, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @GetMapping("/{id}")
    fun getModel(@RequestHeader headers: Map<String, String>, @PathVariable id: Int): ResponseEntity<Response<QuestionResponse>> {
        return try {
            val result = service.getModel(id)

            if(result != null) {
                handleResult(result)
            } else {
                handleNotExists()
            }
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @PostMapping("/")
    fun insert(@RequestHeader headers: Map<String, String>, @RequestBody question: Question): ResponseEntity<Response<QuestionResponse>> {
        return try {
            printHeaders(headers)

            val exists = repository.existsById(question.questionId)

            if(exists) {
                return handleExists()
            }

            val timeModel = Question(question.question, question.answerId, question.active)
            val result = service.insert(timeModel)

            handleResult(result)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @PostMapping("/replace")
    fun replace(@RequestHeader headers: Map<String, String>, @RequestBody question: Question): ResponseEntity<Response<QuestionResponse>> {
        return try {
            printHeaders(headers)

            val timeModel = Question(question.questionId, question.question, question.answerId, question.active)
            val result = service.replace(timeModel)

            handleResult(result)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @PutMapping("/")
    fun update(@RequestHeader headers: Map<String, String>, @RequestBody question: Question): ResponseEntity<Response<QuestionResponse>> {
        return try {
            printHeaders(headers)

            val model = service.getModel(question.questionId)

            if(model != null) {
                val timeModel = Question(question.questionId, question.question, question.answerId, question.active)
                val result = service.update(timeModel)
                handleResult(result)
            } else {
                handleNotExists()
            }
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@RequestHeader headers: Map<String, String>, @PathVariable id: Int): ResponseEntity<Response<QuestionResponse>> {
        return try {
            printHeaders(headers)

            val model = service.getModel(id)

            if(model != null) {
                service.delete(id)
            } else {
                return handleNotExists()
            }

            val response: Response<QuestionResponse> = Response(ResultCodeDef.SUCCESS_CODE, ResultDescriptionDef.SUCCESS_CODE)
            ResponseEntity<Response<QuestionResponse>>(response, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @DeleteMapping("/all")
    fun delete(@RequestHeader headers: Map<String, String>): ResponseEntity<Response<QuestionResponse>> {
        return try {
            // this.service.deleteAll()

            val response: Response<QuestionResponse> = Response(ResultCodeDef.SUCCESS_CODE, ResultDescriptionDef.SUCCESS_CODE)
            ResponseEntity<Response<QuestionResponse>>(response, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @GetMapping("/exists/{id}")
    fun exists(@RequestHeader headers: Map<String, String>, @PathVariable id: Int): ResponseEntity<Response<Boolean>> {
        return try {
            printHeaders(headers)

            val result = service.exists(id)
            val response: Response<Boolean> = Response(result)

            ResponseEntity(response, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    private fun handleNotExists(): ResponseEntity<Response<QuestionResponse>> {
        val response: Response<QuestionResponse> = Response(ResultCodeDef.ERROR_CODE_NOT_EXISTS, ResultDescriptionDef.ERROR_CODE_NOT_EXISTS)
        return ResponseEntity(response, HttpStatus.OK)
    }

    private fun handleExists(): ResponseEntity<Response<QuestionResponse>> {
        val response = Response<QuestionResponse>(ResultCodeDef.ERROR_CODE_EXISTS, ResultDescriptionDef.ERROR_CODE_EXISTS)
        return ResponseEntity(response, HttpStatus.OK)
    }

    private fun handleError(): Response<QuestionListResponse> {
        return Response(ResultCodeDef.ERROR_CODE, ResultDescriptionDef.ERROR_CODE)
    }

    private fun handleResult(@NonNull model: Question): ResponseEntity<Response<QuestionResponse>> {
        val content = QuestionResponse(model)
        val response: Response<QuestionResponse> = Response(content)
        return ResponseEntity(response, HttpStatus.OK)
    }

    private fun printHeaders(@RequestHeader headers: Map<String, String>) {
        headers.forEach { header ->
            val key = header.key
            val value = header.value
            logger.info("Header $key: $value")
        }
    }
}