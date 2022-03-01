package com.example.testtask.search.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.testtask.search.recycler.model.ProjectTab

class SearchDiffCallback: DiffUtil.ItemCallback<ProjectTab>() {

    override fun areItemsTheSame(oldItem: ProjectTab, newItem: ProjectTab): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProjectTab, newItem: ProjectTab): Boolean {
        return oldItem == newItem
    }
}