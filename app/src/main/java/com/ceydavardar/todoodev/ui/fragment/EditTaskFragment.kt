package com.ceydavardar.todoodev.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.ceydavardar.todoodev.R
import com.ceydavardar.todoodev.data.entity.Task
import com.ceydavardar.todoodev.databinding.FragmentEditTaskBinding
import com.ceydavardar.todoodev.ui.viewmodel.EditTaskFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private lateinit var binding: FragmentEditTaskBinding
    private lateinit var viewModel: EditTaskFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditTaskBinding.inflate(inflater, container, false)

        val bundle: EditTaskFragmentArgs by navArgs()

        var task = bundle.task

        binding.button2.setOnClickListener {

            val name = binding.taskNameET.text.toString()
            val description = binding.taskDescriptionEditText.text.toString()

            task.description = description
            task.name = name

            viewModel.update(task)

            val gecis = EditTaskFragmentDirections.actionEditTaskFragmentToMainFragment(task)

            Navigation.findNavController(it).navigate(gecis)

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: EditTaskFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}