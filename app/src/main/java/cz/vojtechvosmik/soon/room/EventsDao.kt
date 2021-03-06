package cz.vojtechvosmik.soon.room

import android.arch.persistence.room.*
import cz.vojtechvosmik.soon.models.Event

@Dao
interface EventsDao {

    @Query("SELECT * FROM events")
    fun getEvents(): List<Event>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getEventsWithId(id: Int): List<Event>

    @Insert
    fun insertEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)
}