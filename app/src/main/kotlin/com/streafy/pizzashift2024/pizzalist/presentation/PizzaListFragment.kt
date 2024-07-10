package com.streafy.pizzashift2024.pizzalist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.streafy.pizzashift2024.R
import com.streafy.pizzashift2024.databinding.FragmentPizzaListBinding
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PizzaListFragment : Fragment() {

    companion object {
        fun newInstance() = PizzaListFragment()
    }

    private var _binding: FragmentPizzaListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PizzaListViewModel by viewModels()

    private var adapter: PizzaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPizzaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle).collectLatest(::handleState)
        }

        val recycler = binding.rvPizzaList
        adapter = PizzaAdapter(onClick = { })
        recycler.adapter = adapter

        binding.btnRetry.setOnClickListener {
            viewModel.loadData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

    private fun handleState(state: PizzaListUiState) {
        when (state) {
            PizzaListUiState.Initial -> showLoading()
            PizzaListUiState.Loading -> showLoading()
            is PizzaListUiState.Content -> showContent(state.pizzas)
            is PizzaListUiState.Error -> showError(state.message)
        }
    }

    private fun showLoading() {
        binding.rvPizzaList.visibility = View.GONE
        binding.llError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showContent(pizzas: List<Pizza>) {
        adapter?.submitList(pizzas)

        binding.rvPizzaList.visibility = View.VISIBLE
        binding.llError.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun showError(message: String?) {
        binding.tvErrorMessage.text = message ?: getString(R.string.unknown_error)

        binding.rvPizzaList.visibility = View.GONE
        binding.llError.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}