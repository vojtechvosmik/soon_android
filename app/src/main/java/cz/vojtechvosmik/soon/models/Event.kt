package cz.vojtechvosmik.soon.models

import android.arch.persistence.room.Entity
import java.util.*

@Entity
data class Event(
    val title: String,
    val date: Date
)