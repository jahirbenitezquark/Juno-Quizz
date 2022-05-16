package com.codequark.juno.commons

import org.springframework.data.repository.CrudRepository
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable
import org.springframework.stereotype.Service
import java.io.Serializable
import java.util.function.Consumer

@Suppress("NULLABLE_TYPE_PARAMETER_AGAINST_NOT_NULL_TYPE_PARAMETER")
@Service
abstract class GenericServiceImpl<Model, ID: Serializable>: GenericServiceAPI<Model, ID> {
    abstract fun getDao(): CrudRepository<Model, ID>

    @NonNull
    override fun getAll(): MutableList<Model> {
        val returnList: MutableList<Model> = ArrayList()
        val dao = getDao()

        dao.findAll().forEach(Consumer {
                obj: Model -> returnList.add(obj)
        })

        return returnList
    }

    @Nullable
    override fun getModel(id: ID): Model? {
        val dao = getDao()
        val obj = dao.findById(id)

        return if(obj.isPresent) {
            obj.get()
        } else null
    }

    @NonNull
    override fun insert(@NonNull entity: Model): Model {
        val dao = getDao()
        return dao.save(entity)
    }

    override fun insert(list: MutableList<Model>) {
        val dao = getDao()
        dao.saveAll(list)
    }

    @NonNull
    override fun replace(entity: Model): Model {
        val dao = getDao()
        return dao.save(entity)
    }

    override fun replace(list: MutableList<Model>) {
        val dao = getDao()
        dao.saveAll(list)
    }

    @NonNull
    override fun update(entity: Model): Model {
        val dao = getDao()
        return dao.save(entity)
    }

    override fun update(list: MutableList<Model>) {
        val dao = getDao()
        dao.saveAll(list)
    }

    override fun delete(id: ID) {
        val dao = getDao()
        dao.deleteById(id)
    }

    override fun deleteAll() {
        val dao = getDao()
        dao.deleteAll()
    }

    override fun exists(id: ID): Boolean {
        val dao = getDao()
        return dao.existsById(id)
    }
}