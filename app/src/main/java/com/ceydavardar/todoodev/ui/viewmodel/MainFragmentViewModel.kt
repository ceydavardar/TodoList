package com.ceydavardar.todoodev.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceydavardar.todoodev.data.entity.Task
import com.ceydavardar.todoodev.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    var repository: TaskRepository
) : ViewModel() {

    val taskListLiveData = MutableLiveData<List<Task>>()

    init {
        loadTaskList()
    }

    fun loadTaskList() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                taskListLiveData.value = repository.loadTaskList()
            } catch (e: Exception) {}
        }
    }

    fun delete(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.delete(task)
            loadTaskList()
        }
    }

    fun search(kelime: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                taskListLiveData.value = repository.search(kelime)
            } catch (e: Exception) {}
        }
    }

}