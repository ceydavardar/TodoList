package com.ceydavardar.todoodev.di

import android.content.Context
import androidx.room.Room
import com.ceydavardar.todoodev.data.datasource.TaskDataSource
import com.ceydavardar.todoodev.data.repo.TaskRepository
import com.ceydavardar.todoodev.room.Database
import com.ceydavardar.todoodev.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTaskDao(@ApplicationContext context: Context): TaskDao {
        val database = Room.databaseBuilder(context, Database::class.java, "tasks.sqlite")
            .createFromAsset("tasks.sqlite").build()
        return database.getTaskDao()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(dataSource: TaskDataSource): TaskRepository {
        return TaskRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideTaskDataSource(taskDao: TaskDao): TaskDataSource {
        return TaskDataSource(taskDao)
    }

}