package com.ceydavardar.todoodev.data.datasource

import com.ceydavardar.todoodev.data.entity.Task
import com.ceydavardar.todoodev.room.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDataSource(var taskDao: TaskDao) {

    suspend fun save(task: Task) {
        taskDao.save(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun loadTaskList(): List<Task> = withContext(Dispatchers.IO) {
        return@withContext taskDao.loadTaskList()
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun search(kelime: String): List<Task> = withContext(Dispatchers.IO) {
        return@withContext taskDao.search(kelime)
    }

}