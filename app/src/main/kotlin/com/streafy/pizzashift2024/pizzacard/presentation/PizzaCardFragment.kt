package com.streafy.pizzashift2024.pizzacard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.streafy.pizzashift2024.R
import com.streafy.pizzashift2024.databinding.FragmentPizzaCardBinding
import com.streafy.pizzashift2024.shared.parcelable
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PizzaCardFragment : Fragment() {

    companion object {
        private const val ARG_PIZZA = "arg_pizza"

        fun newInstance(pizza: Pizza): PizzaCardFragment =
            PizzaCardFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PIZZA, pizza)
                }
            }
    }

    private var _binding: FragmentPizzaCardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PizzaCardViewModel by viewModels()

    private var adapter: ToppingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = requireArguments()
        val pizza = args.parcelable<Pizza>(ARG_PIZZA)
            ?: error("No argument passed to PizzaCardFragment")

        viewModel.setPizzaData(pizza)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPizzaCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle).collectLatest(::handleState)
        }

        val recycler = binding.rvToppings
        adapter = ToppingAdapter(onClick = { viewModel.onToppingSelected(it.id) })
        recycler.adapter = adapter

        val firstButtonId = R.id.tg_btn_first
        binding.tgSizes.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                viewModel.onSizeSelected(checkedId % firstButtonId) //Выглядит костыльно...
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

    private fun handleState(state: PizzaCardUiState) {

        adapter?.selectedIndexes = state.selectedToppingIndexes
        adapter?.toppings = state.pizza?.toppings ?: emptyList()

        with(binding) {
            val pizza = state.pizza ?: return
            ivPizza.load(pizza.imageUri) {
                placeholder(R.drawable.placeholder_pizza)
                crossfade(true)
            }
            tvName.text = pizza.name
            tvSizeAndDough.text =
                getString(R.string.size_and_dough, pizza.sizes[state.selectedSizeIndex].name)
            tvDescription.text = pizza.description

            tgBtnFirst.text = pizza.sizes[0].name
            tgBtnSecond.text = pizza.sizes[1].name
            tgBtnThird.text = pizza.sizes[2].name
        }
    }
}