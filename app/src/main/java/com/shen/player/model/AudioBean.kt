package com.shen.player.model

import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import java.util.ArrayList

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/20 11:17
 * 描述:该类主要作用
 */
data class AudioBean(
    var data: String?,
    var size:Long,
    var display_name: String?,
    var artst: String?
):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeLong(size)
        parcel.writeString(display_name)
        parcel.writeString(artst)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioBean> {
        override fun createFromParcel(parcel: Parcel): AudioBean {
            return AudioBean(parcel)
        }

        override fun newArray(size: Int): Array<AudioBean?> {
            return arrayOfNulls(size)
        }

        fun getAduioBean(cursor: Cursor?):AudioBean{
            var audioBean = AudioBean("",0,"","")
            cursor?.let {
                audioBean.data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                audioBean.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audioBean.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                audioBean.display_name = audioBean?.display_name?.substring(0,audioBean!!.display_name!!.lastIndexOf("."))
                audioBean.artst = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            }
            return audioBean
        }

        fun getAudioBeans(item: Cursor?): ArrayList<AudioBean> {
            var list:ArrayList<AudioBean> = ArrayList()
            item?.let {
                it.moveToPosition(-1)
                while (it.moveToNext()){
                    list.add(getAduioBean(item))
                }
            }
            return list
        }
    }
}