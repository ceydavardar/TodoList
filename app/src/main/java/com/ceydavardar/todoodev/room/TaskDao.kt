package com.ceydavardar.todoodev.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ceydavardar.todoodev.data.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun loadTaskList(): List<Task>

    @Insert
    suspend fun save(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks WHERE name like '%' || :kelime || '%'")
    suspend fun search(kelime: String): List<Task>

}