package com.shen.player.model

import android.os.Parcel
import android.os.Parcelable

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/6 11:23
 * 描述:该类主要作用
 */
data class VideoPlayerBean(var id:String?, var title: String?, var videoUrl: String?,var imgUrl:String?):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(videoUrl)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoPlayerBean> {
        override fun createFromParcel(parcel: Parcel): VideoPlayerBean {
            return VideoPlayerBean(parcel)
        }

        override fun newArray(size: Int): Array<VideoPlayerBean?> {
            return arrayOfNulls(size)
        }
    }

}