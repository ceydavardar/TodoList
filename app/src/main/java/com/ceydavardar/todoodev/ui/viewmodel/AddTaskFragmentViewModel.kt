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
class AddTaskFragmentViewModel @Inject constructor(
    var repository: TaskRepository
): ViewModel() {

    fun save(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.save(task)
        }
    }

}