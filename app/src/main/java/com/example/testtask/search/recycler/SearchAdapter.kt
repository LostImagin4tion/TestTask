package com.example.testtask.search.recycler

import android.view.ViewGroup
import com.example.testtask.search.recycler.model.ProjectTab
import com.example.testtask.search.recycler.view_holder.ProjectTabViewHolder
import androidx.recyclerview.widget.ListAdapter

class SearchAdapter: ListAdapter<ProjectTab, ProjectTabViewHolder>(SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectTabViewHolder {
        return ProjectTabViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ProjectTabViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item as ProjectTab)
    }
}