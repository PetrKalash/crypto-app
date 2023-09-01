package ru.petrkalash.testkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.petrkalash.testkotlin.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(Any()) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, "coin_info.db").build()
                db = instance
                return instance
            }
        }
    }
    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}