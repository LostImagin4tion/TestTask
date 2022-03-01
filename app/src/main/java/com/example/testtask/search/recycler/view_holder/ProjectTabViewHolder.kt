package com.example.testtask.search.recycler.view_holder

import android.content.res.Resources
import android.content.res.TypedArray
import android.provider.Settings
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.buildSpannedString
import com.example.testtask.R
import com.example.testtask.protocol.getThumbnailURL
import com.example.testtask.search.recycler.model.ProjectTab
import com.squareup.picasso.Picasso
import kotlin.random.Random

class ProjectTabViewHolder(parent: ViewGroup): TabViewHolder(parent, R.layout.project_tile) {

    private val projectNumber = itemView.findViewById<TextView>(R.id.project_number)
    private val projectStatus = itemView.findViewById<TextView>(R.id.project_status)
    private val projectVacancy = itemView.findViewById<TextView>(R.id.vacancy)
    private val projectHeader = itemView.findViewById<TextView>(R.id.project_header)
    private val projectHead = itemView.findViewById<TextView>(R.id.project_head)
    private val projectType = itemView.findViewById<TextView>(R.id.project_type)
    private val projectThumbnail = itemView.findViewById<ImageView>(R.id.project_thumbnail)

    fun bind(item: ProjectTab) {

        if(item.number.isEmpty()) {
            projectNumber.text = "-"
        }
        else {
            projectNumber.text = item.number
        }

        projectStatus.text = item.status
        if(item.status == "Рабочий") {
            projectStatus.setBackgroundResource(R.drawable.project_tile_status_rectangle_green)
        }
        else {
            projectStatus.setBackgroundResource(R.drawable.project_tile_status_rectangle_purple)
        }

        projectVacancy.text = "${item.vacancy} вакансия(й)"

        projectHeader.text = item.header

        val headName = item.head.split(" ")

        val firstName = headName[1][0]
        val secondName = headName[0]

        val patronymic: String = if(headName.size > 2) {
            headName[2][0].toString() } else { "" }

        projectHead.text = "Рук.: $secondName $firstName. $patronymic."

        projectType.text = "Тип: ${item.type}"

        if (item.thumbnail == "null" || item.thumbnail.isEmpty()) {

            val examples = listOf(
                R.drawable.example,
                R.drawable.example1,
                R.drawable.example2,
                R.drawable.example3
            )

            val randomIndex = Random.nextInt(examples.size)
            projectThumbnail.setImageResource(examples[randomIndex])
        }
        else {
            val imageURL = getThumbnailURL(item.projectId)

            Picasso.get()
                .load(imageURL)
                .into(projectThumbnail)
        }
    }
}