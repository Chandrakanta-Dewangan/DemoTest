package com.freeagent.testapp.db.dao

import androidx.room.*
import com.freeagent.testapp.db.model.Currency

@Dao
interface CurrencyDAO {

    @Query("SELECT * FROM Currency")
    fun getAll(): MutableList<Currency>

    @Query("SELECT * FROM Currency WHERE id = :id")
    fun findById(id: Int): Currency

    @Query("SELECT COUNT(id) FROM Currency")
    fun currencyCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrencies(currencies: Currency)

    @Update
    fun updateCurrency(currency: Currency)
}