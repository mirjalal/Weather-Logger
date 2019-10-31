package com.talmir.weatherlogger.data.source.local.room.utils

import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE

interface BaseDao<E> {
    @Insert(onConflict = IGNORE)
    suspend fun insert(vararg data: E): LongArray
}
