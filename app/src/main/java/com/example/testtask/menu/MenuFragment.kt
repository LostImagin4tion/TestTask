package com.example.testtask.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R
import com.example.testtask.navigation.NavigationFragment

class MenuFragment: Fragment() {

    private lateinit var viewModel: MenuViewModel

    private lateinit var email: EditText
    private lateinit var projectNumber: EditText
    private lateinit var start: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false).apply {
            email = findViewById(R.id.email)
            projectNumber = findViewById(R.id.project_number)
            start = findViewById(R.id.start_button)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.statusBarColor = resources.getColor(R.color.royal_blue)

        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        start.setOnClickListener {
            val email = email.text.toString()
            val projectNumber: String? = projectNumber.text?.toString()

            (requireActivity() as com.example.testtask.MainActivity).goUserProfile(email, projectNumber as String)
        }
    }

    companion object {
        fun newInstance() = MenuFragment()
    }
}