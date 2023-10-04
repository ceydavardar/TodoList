package com.ceydavardar.todoodev.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ceydavardar.todoodev.data.entity.Task
import com.ceydavardar.todoodev.databinding.TaskCardDesignBinding
import com.ceydavardar.todoodev.ui.fragment.MainFragmentDirections
import com.ceydavardar.todoodev.ui.viewmodel.MainFragmentViewModel

class TasksAdapter(
    var context: Context,
    var taskList: List<Task>,
    var viewModel: MainFragmentViewModel
): RecyclerView.Adapter<TasksAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var binding: TaskCardDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        var binding = TaskCardDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {

        val task = taskList.get(position)
        val binding = holder.binding

        binding.taskNameTextView.text = task.name
        binding.taskDescriptionTextView.text = task.description

        binding.deleteButton.setOnClickListener {
            viewModel.delete(task)
            viewModel.loadTaskList()
        }

        binding.editButton.setOnClickListener {
            val gecis = MainFragmentDirections.actionMainFragmentToEditTaskFragment(task)
            Navigation.findNavController(it).navigate(gecis)
        }

    }

}