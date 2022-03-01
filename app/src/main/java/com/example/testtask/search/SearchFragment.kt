package com.example.testtask.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.navigation.NavigationFragment
import com.example.testtask.search.recycler.SearchAdapter

class SearchFragment: Fragment() {

    private lateinit var viewModel: SearchViewModel

    private lateinit var backButton: ImageButton

    private lateinit var projectTabs: RecyclerView
    private lateinit var adapter: SearchAdapter

    private lateinit var searchBar: EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search, container, false).apply {
            backButton = findViewById(R.id.search_back)
            projectTabs = findViewById(R.id.project_tiles)
            searchBar = findViewById(R.id.search_bar)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, NavigationFragment.newInstance(isSearchButton = true), "NavigationFragment")
            ?.commit()

        backButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).onBackPressed()
        }

        adapter = SearchAdapter()
        projectTabs.adapter = adapter
        projectTabs.isNestedScrollingEnabled = false

        viewModel.projects.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        viewModel.start()
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}