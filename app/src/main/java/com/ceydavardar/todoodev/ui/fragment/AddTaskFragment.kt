package com.ceydavardar.todoodev.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ceydavardar.todoodev.R
import com.ceydavardar.todoodev.data.entity.Task
import com.ceydavardar.todoodev.databinding.FragmentAddTaskBinding
import com.ceydavardar.todoodev.ui.viewmodel.AddTaskFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var viewModel: AddTaskFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)

        binding.button.setOnClickListener {

            var name = binding.taskNameEditText.text.toString()
            var description = binding.taskDescriptionEditText.text.toString()

            var task = Task(0, name, description)

            viewModel.save(task)

            val gecis = AddTaskFragmentDirections.actionAddTaskFragmentToMainFragment(task)

            Navigation.findNavController(it).navigate(gecis)

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AddTaskFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}