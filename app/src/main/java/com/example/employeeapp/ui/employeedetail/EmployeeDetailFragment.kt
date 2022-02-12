package com.example.employeeapp.ui.employeedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.employeeapp.data.entities.Employee
import com.example.employeeapp.databinding.EmployeeDetailFragmentBinding
import com.example.employeeapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeDetailFragment : Fragment() {

    private var binding: EmployeeDetailFragmentBinding by autoCleared()
    private val viewModel: EmployeeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmployeeDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.employee.observe(viewLifecycleOwner, Observer {
            bindEmployee(it!!)

        })
    }

    private fun bindEmployee(employee: Employee) {
        binding.name.text = employee.name
        binding.username.text = employee.username
        binding.email.text = employee.email
        binding.address.text = employee.address.city

        binding.phone.text = employee.phone
        binding.website.text = employee.website
        binding.company.text = formatText(employee.company?.name) +"\n"+ formatText(employee.company?.catchPhrase) + "\n"+ formatText(employee.company?.bs)
        Glide.with(binding.root)
            .load(employee.profile_image)
            .transform(CircleCrop())
            .into(binding.image)
    }

    private fun formatText(text : String?) : String {
        return if (text === null) "" else text
    }

}
