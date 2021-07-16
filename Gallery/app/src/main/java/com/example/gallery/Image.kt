package com.example.gallery

import android.os.Parcel
import android.os.Parcelable

class Image() : Parcelable {

    var name: String?=null
    var stars: Float = 0.0F
    var description: String?=null
    var cos: Int = 0

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        stars = parcel.readFloat()
        description = parcel.readString()
        cos = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeFloat(stars)
        parcel.writeString(description)
        parcel.writeInt(cos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}