package com.codequark.juno.controllers

import com.codequark.juno.models.Option
import com.codequark.juno.models.Response
import com.codequark.juno.repositories.OptionRepository
import com.codequark.juno.retrofit.RetrofitConstants.ResultCodeDef
import com.codequark.juno.retrofit.RetrofitConstants.ResultDescriptionDef
import com.codequark.juno.retrofit.models.OptionListResponse
import com.codequark.juno.retrofit.models.OptionResponse
import com.codequark.juno.services.api.OptionServiceAPI
import com.codequark.juno.utils.Endpoints
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.NonNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Endpoints.optionUrl)
@CrossOrigin("*")
class OptionController {
    @Autowired
    private lateinit var service: OptionServiceAPI

    @Autowired
    private lateinit var repository: OptionRepository

    @NonNull
    private val logger: Logger = LoggerFactory.getLogger(OptionController::class.java)

    @GetMapping("/all")
    fun getAll(@RequestHeader headers: Map<String, String>): ResponseEntity<Response<OptionListResponse>> {
        return try {
            printHeaders(headers)

            val result: MutableList<Option>? = service.getAll()

            val response: Response<OptionListResponse> = if(result != null) {
                val content = OptionListResponse(result)
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
    fun getModel(@RequestHeader headers: Map<String, String>, @PathVariable id: Int): ResponseEntity<Response<OptionResponse>> {
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
    fun insert(@RequestHeader headers: Map<String, String>, @RequestBody option: Option): ResponseEntity<Response<OptionResponse>> {
        return try {
            printHeaders(headers)

            val exists = repository.existsById(option.optionId)

            if(exists) {
                return handleExists()
            }

            val timeModel = Option(option.option, option.questionId, option.active)
            val result = service.insert(timeModel)

            handleResult(result)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @PostMapping("/replace")
    fun replace(@RequestHeader headers: Map<String, String>, @RequestBody option: Option): ResponseEntity<Response<OptionResponse>> {
        return try {
            printHeaders(headers)

            val timeModel = Option(option.optionId, option.option, option.questionId, option.active)
            val result = service.replace(timeModel)

            handleResult(result)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @PutMapping("/")
    fun update(@RequestHeader headers: Map<String, String>, @RequestBody option: Option): ResponseEntity<Response<OptionResponse>> {
        return try {
            printHeaders(headers)

            val model = service.getModel(option.optionId)

            if(model != null) {
                val timeModel = Option(option.optionId, option.option, option.questionId, option.active)
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
    fun delete(@RequestHeader headers: Map<String, String>, @PathVariable id: Int): ResponseEntity<Response<OptionResponse>> {
        return try {
            printHeaders(headers)

            val model = service.getModel(id)

            if(model != null) {
                service.delete(id)
            } else {
                return handleNotExists()
            }

            val response: Response<OptionResponse> = Response(ResultCodeDef.SUCCESS_CODE, ResultDescriptionDef.SUCCESS_CODE)
            ResponseEntity<Response<OptionResponse>>(response, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(Response(ResultCodeDef.ERROR_CODE_CONNECTION_DATABASE, ex.toString()), HttpStatus.OK)
        }
    }

    @DeleteMapping("/all")
    fun delete(@RequestHeader headers: Map<String, String>): ResponseEntity<Response<OptionResponse>> {
        return try {
            // this.service.deleteAll()

            val response: Response<OptionResponse> = Response(ResultCodeDef.SUCCESS_CODE, ResultDescriptionDef.SUCCESS_CODE)
            ResponseEntity<Response<OptionResponse>>(response, HttpStatus.OK)
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

    private fun handleNotExists(): ResponseEntity<Response<OptionResponse>> {
        val response: Response<OptionResponse> = Response(ResultCodeDef.ERROR_CODE_NOT_EXISTS, ResultDescriptionDef.ERROR_CODE_NOT_EXISTS)
        return ResponseEntity(response, HttpStatus.OK)
    }

    private fun handleExists(): ResponseEntity<Response<OptionResponse>> {
        val response = Response<OptionResponse>(ResultCodeDef.ERROR_CODE_EXISTS, ResultDescriptionDef.ERROR_CODE_EXISTS)
        return ResponseEntity(response, HttpStatus.OK)
    }

    private fun handleError(): Response<OptionListResponse> {
        return Response(ResultCodeDef.ERROR_CODE, ResultDescriptionDef.ERROR_CODE)
    }

    private fun handleResult(@NonNull model: Option): ResponseEntity<Response<OptionResponse>> {
        val content = OptionResponse(model)
        val response: Response<OptionResponse> = Response(content)
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