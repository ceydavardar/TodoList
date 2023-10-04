package com.ceydavardar.todoodev.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ceydavardar.todoodev.data.entity.Task
import com.ceydavardar.todoodev.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskFragmentViewModel @Inject constructor(
    var repository: TaskRepository
) : ViewModel() {

    fun update(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.delete(task)
            repository.save(Task(task.id, task.name, task.description))
        }
    }

}