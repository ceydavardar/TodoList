package com.ceydavardar.todoodev.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceydavardar.todoodev.R
import com.ceydavardar.todoodev.databinding.FragmentMainBinding
import com.ceydavardar.todoodev.ui.adapter.TasksAdapter
import com.ceydavardar.todoodev.ui.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_addTaskFragment)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.taskListLiveData.observe(viewLifecycleOwner) {

            val adapter = TasksAdapter(requireContext(), it, viewModel)
            binding.recyclerView.adapter = adapter

        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(kelime: String): Boolean {
                viewModel.search(kelime)
                return true
            }

            override fun onQueryTextSubmit(kelime: String): Boolean {
                viewModel.search(kelime)
                return false
            }
        })

        val bundle: MainFragmentArgs by navArgs()

        if (bundle.task != null) {
            viewModel.loadTaskList()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTaskList()
    }

}

