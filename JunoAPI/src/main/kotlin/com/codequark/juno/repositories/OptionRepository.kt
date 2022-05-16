package com.codequark.juno.repositories

import com.codequark.juno.models.Option
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OptionRepository: CrudRepository<Option, Int>