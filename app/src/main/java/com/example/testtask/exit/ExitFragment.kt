package com.example.testtask.exit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R

class ExitFragment: Fragment() {

    private lateinit var viewModel: ExitViewModel

    private lateinit var yesButton: Button
    private lateinit var noButton: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(ExitViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.exit_warning, container, false).apply {
            yesButton = findViewById(R.id.exit_warning_yes_button)
            noButton = findViewById(R.id.exit_warning_no_button)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yesButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).goMenu()
        }
        noButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).onBackPressed()
        }
    }

    companion object {
        fun newInstance(): ExitFragment {
            return ExitFragment()
        }
    }
}