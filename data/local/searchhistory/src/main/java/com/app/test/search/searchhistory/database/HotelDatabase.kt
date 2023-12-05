package com.app.test.search.searchhistory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.test.search.searchhistory.entity.HotelDao
import com.app.test.search.searchhistory.entity.HotelEntity

@Database(
    entities = [HotelEntity::class],
    version = 1,
    exportSchema = false
)

abstract class HotelDatabase : RoomDatabase() {
    abstract fun getHotelDao(): HotelDao

    /*  companion object {
          @Volatile
          private var INSTANCE: HotelDatabase? = null

          fun getDatabase(context: Context): HotelDatabase {

              val tempInstance = INSTANCE

              if (tempInstance != null) {
                  return tempInstance
              }

              synchronized(this) {
                  val instance = Room.databaseBuilder(
                      context.applicationContext,
                      HotelDatabase::class.java,
                      "hotel_database"
                  ).build()

                  INSTANCE = instance
                  return instance
              }
          }
      }*/
}

object HotelDatabaseFactory {
    fun getHotelDataBase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        HotelDatabase::class.java,
        name = "hotel_database",
    ).build()
}
