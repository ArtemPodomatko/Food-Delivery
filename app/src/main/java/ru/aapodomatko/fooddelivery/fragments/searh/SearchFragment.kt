package ru.aapodomatko.fooddelivery.fragments.searh

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.aapodomatko.fooddelivery.adapters.homeFragmentAdapter.HomeFragmentAdapter
import ru.aapodomatko.fooddelivery.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var mViewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(
            this,
            SearchFragmentViewModel.SearchFragmentViewModelFactory(requireActivity().application)
        )[SearchFragmentViewModel::class.java]

        val menuAdapter = HomeFragmentAdapter()

        binding.menuRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuAdapter
        }

        mViewModel.foodItemsLiveData.observe(viewLifecycleOwner) {
            menuAdapter.differ.submitList(it)
        }

        binding.edSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.searchItems(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        mViewModel.searchResults.observe(viewLifecycleOwner) {
            menuAdapter.differ.submitList(it)
        }
    }

}