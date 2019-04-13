package cz.vojtechvosmik.soon.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import cz.vojtechvosmik.soon.models.Event

@Database(entities = [Event::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (appDatabase == null) {
                synchronized(AppDatabase::class) {
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "eventsDb"
                    ).allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }

        fun destroyDatabase() {
            appDatabase = null
        }
    }

    abstract fun eventsDao(): EventsDao
}