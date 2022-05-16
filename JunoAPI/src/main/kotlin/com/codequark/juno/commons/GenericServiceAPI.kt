package com.codequark.juno.commons

import java.io.Serializable

interface GenericServiceAPI<T, ID: Serializable> {
    fun getAll(): MutableList<T>?
    fun getModel(id: ID): T?
    fun insert(entity: T): T
    fun insert(list: MutableList<T>)
    fun replace(entity: T): T
    fun replace(list: MutableList<T>)
    fun update(entity: T): T
    fun update(list: MutableList<T>)
    fun delete(id: ID)
    fun deleteAll()
    fun exists(id: ID): Boolean
}