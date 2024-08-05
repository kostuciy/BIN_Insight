package com.kostuciy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kostuciy.data.db.entities.CardInfoEntity

@Dao
interface BinDao {
    @Insert
    suspend fun saveCard(cardInfo: CardInfoEntity)

    @Query("SELECT * FROM cardinfoentity order by date desc")
    suspend fun getAllCards(): List<CardInfoEntity>

    @Query("DELETE FROM cardinfoentity WHERE date = :date")
    suspend fun deleteCard(date: Long)
}
