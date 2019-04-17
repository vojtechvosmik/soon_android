package cz.vojtechvosmik.soon.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap
import java.util.*

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var title: String,
    var date: Date,
    var photo: Bitmap? = null
)