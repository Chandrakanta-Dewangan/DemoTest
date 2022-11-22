package com.freeagent.testapp.db.dao

import androidx.room.*
import com.freeagent.testapp.db.model.Rate

@Dao
interface RateDAO {

    @Query("SELECT * FROM Rate")
    fun getAll(): MutableList<Rate>

    @Query("SELECT * FROM Rate WHERE rateId = :id")
    fun findById(id: Int): Rate

    @Query("SELECT COUNT(rateId) FROM Rate")
    fun rateCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRates(rates: List<Rate>)
}