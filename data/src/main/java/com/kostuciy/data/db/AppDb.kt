package com.kostuciy.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kostuciy.data.db.dao.BinDao
import com.kostuciy.data.db.entities.CardInfoEntity

@Database(
    entities = [CardInfoEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class AppDb : RoomDatabase() {
    abstract fun dao(): BinDao
}
