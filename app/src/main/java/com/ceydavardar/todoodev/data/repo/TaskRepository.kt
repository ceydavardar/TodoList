package com.ceydavardar.todoodev.data.repo

import com.ceydavardar.todoodev.data.datasource.TaskDataSource
import com.ceydavardar.todoodev.data.entity.Task

class TaskRepository(var dataSource: TaskDataSource) {

    suspend fun save(task: Task) {
        dataSource.save(task)
    }

    suspend fun update(task: Task) {
        dataSource.update(task)
    }

    suspend fun delete(task: Task) {
        dataSource.delete(task)
    }

    suspend fun loadTaskList(): List<Task> {
        return dataSource.loadTaskList()
    }

    suspend fun search(kelime: String): List<Task> {
        return dataSource.search(kelime)
    }

}