package com.example.employeeapp.ui.employees

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeeapp.R
import com.example.employeeapp.databinding.EmployeeFragmentBinding
import com.example.employeeapp.utils.Resource
import com.example.employeeapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeFragment : Fragment(), EmployeeAdapter.EmployeeItemListener  {

    private var binding: EmployeeFragmentBinding by autoCleared()
    private val viewModel: EmployeeViewModel by viewModels()
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmployeeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()

        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                searchDatabase(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })
    }

    private fun setupRecyclerView() {
        adapter = EmployeeAdapter(this)
        binding.employeeRv.layoutManager = LinearLayoutManager(requireContext())
        binding.employeeRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.employees.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(employeeId: Int) {
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to employeeId)
        )
    }

    // We have just created this function for searching our database
    private fun searchDatabase(query: String) {
        // %" "% because our custom sql query will require that
        val searchQuery = "%$query%"

        viewModel.searchEmployee(searchQuery).observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.setItems(ArrayList(it))
            else {
                Toast.makeText(requireContext(), "Employee not found!!", Toast.LENGTH_SHORT).show()
                adapter.setItems(ArrayList())
            }

        })
    }
}
