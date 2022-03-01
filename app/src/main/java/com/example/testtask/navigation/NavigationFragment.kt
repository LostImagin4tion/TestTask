package com.example.testtask.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R

class NavigationFragment(
    val isProfileButton: Boolean,
    val isProjectInfo: Boolean,
    val isSearchButton: Boolean,
    val isExitButton: Boolean
): Fragment() {

    private lateinit var viewModel: NavigationViewModel

    private lateinit var profileButton: ImageButton
    private lateinit var projectInfoButton: ImageButton
    private lateinit var searchButton: ImageButton
    private lateinit var exitButton: ImageButton

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(NavigationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu, container, false).apply {
            profileButton = findViewById(R.id.profile_button)
            projectInfoButton = findViewById(R.id.project_button)
            searchButton = findViewById(R.id.search_button)
            exitButton = findViewById(R.id.exit_button)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileButton.isPressed = isProfileButton
        projectInfoButton.isPressed = isProjectInfo
        searchButton.isPressed = isSearchButton
        exitButton.isPressed = isExitButton

        profileButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).goUserProfile()
        }

        searchButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).goSearch()
        }

        projectInfoButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).goProjectInfo()
        }

        exitButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).goExitWarning()
        }
    }

    companion object {
        fun newInstance(
            isProfileButton: Boolean = false,
            isProjectInfo: Boolean = false,
            isSearchButton: Boolean = false,
            isExitButton: Boolean = false
        ): NavigationFragment {
            return NavigationFragment(
                isProfileButton,
                isProjectInfo,
                isSearchButton,
                isExitButton
            )
        }
    }
}