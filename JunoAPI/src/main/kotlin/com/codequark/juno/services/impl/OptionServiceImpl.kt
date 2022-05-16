package com.codequark.juno.services.impl

import com.codequark.juno.commons.GenericServiceImpl
import com.codequark.juno.models.Option
import com.codequark.juno.repositories.OptionRepository
import com.codequark.juno.services.api.OptionServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.lang.NonNull
import org.springframework.stereotype.Service

@Service
class OptionServiceImpl: GenericServiceImpl<Option, Int>(), OptionServiceAPI {
    @Autowired
    private lateinit var repository: OptionRepository

    @NonNull
    override fun getDao(): CrudRepository<Option, Int> {
        return repository
    }
}