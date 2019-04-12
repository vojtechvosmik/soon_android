package cz.vojtechvosmik.soon.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "events")
data class Event(
    val title: String,
    val date: Date
)