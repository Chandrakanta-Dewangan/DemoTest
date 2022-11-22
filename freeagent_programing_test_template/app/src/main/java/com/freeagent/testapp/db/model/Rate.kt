package com.freeagent.testapp.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Rate(
    @PrimaryKey(autoGenerate = true)
    val rateId: Int? = 0,
    var rateName: String?,
    val rateVal: Double?
) : Parcelable {
    @Ignore constructor(): this(1,"",0.0)
}