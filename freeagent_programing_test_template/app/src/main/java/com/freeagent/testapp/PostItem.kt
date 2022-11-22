package com.freeagent.testapp

import android.os.Parcel
import android.os.Parcelable

class PostItem(val name: String?, val image: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "PostItem(name=$name, image=$image)"
    }

    companion object CREATOR : Parcelable.Creator<PostItem> {
        override fun createFromParcel(parcel: Parcel): PostItem {
            return PostItem(parcel)
        }

        override fun newArray(size: Int): Array<PostItem?> {
            return arrayOfNulls(size)
        }
    }

}

