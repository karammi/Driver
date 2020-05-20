package ir.brn.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.brn.cache.dao.*
import ir.brn.cache.model.*
import ir.brn.cache.model.load.CachedTrip
import ir.brn.cache.model.load.CachedTripAddress

@Database(
        entities = [CachedUser::class,
            Config::class
        ],
        version = 2,
        exportSchema = false)
abstract class DriverDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun configDao(): ConfigDao



    companion object : SingletonHolder<Context, DriverDatabase>({
        Room.databaseBuilder(it.applicationContext,
                DriverDatabase::class.java,
                "test.db").build()
    })

}