package com.freeagent.testapp.db.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.freeagent.testapp.models.RatesData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Currency(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var rates: List<RatesData>?,
    val base: String,
    val date: String
): Parcelable {
    @Ignore constructor() : this(1, null, "", "")
}

class RatesTypeConverter{
    @TypeConverter
    fun fromOrderApplication(orderApplication: List<RatesData>): String{
        val type = object : TypeToken<List<RatesData>>() {}.type
        return Gson().toJson(orderApplication, type)
    }

    @TypeConverter
    fun toOrderApplication(json: String): List<RatesData> {
        val type = object : TypeToken<List<RatesData>>() {}.type
        return Gson().fromJson(json, type)
    }
}