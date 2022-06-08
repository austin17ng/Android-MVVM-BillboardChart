package com.example.mybillboard.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseChartItem::class, DatabaseSong::class], version = 1)
abstract class BillboardDatabase : RoomDatabase() {
//    abstract val chartItemDao: ChartItemDao
    abstract fun chartItem(): ChartItemDao

    abstract fun song(): SongDao

    companion object {
        @Volatile
        private var INSTANCE: BillboardDatabase? = null

        fun getInstance(context: Context): BillboardDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BillboardDatabase::class.java,
                        "billboard_database"
                    )
//                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}

//private lateinit var INSTANCE: BillboardDatabase
//
//fun getDatabase(context: Context) : BillboardDatabase {
//    synchronized(BillboardDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(context.applicationContext,
//                BillboardDatabase::class.java,
//                "billboard").build()
//        }
//    }
//    return INSTANCE
//}