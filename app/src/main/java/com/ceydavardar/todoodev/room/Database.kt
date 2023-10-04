package com.ceydavardar.todoodev.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceydavardar.todoodev.data.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

}