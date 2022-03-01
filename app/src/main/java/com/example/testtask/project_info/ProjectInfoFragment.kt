package com.example.testtask.project_info

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R
import com.example.testtask.navigation.NavigationFragment
import com.example.testtask.protocol.getThumbnailURL
import com.squareup.picasso.Picasso
import java.lang.reflect.Type
import kotlin.random.Random

class ProjectInfoFragment(val projectNumber: String): Fragment() {

    private lateinit var viewModel: ProjectInfoViewModel

    private lateinit var backButton: ImageButton

    private lateinit var mailButton: Button
    private lateinit var wikiButton: Button

    private lateinit var thumbnail: ImageView
    private lateinit var badgeCardView: FrameLayout
    private lateinit var header: TextView
    private lateinit var head: TextView
    private lateinit var headMail: TextView
    private lateinit var headPhoneNumber: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(ProjectInfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_info, container, false).apply {
            backButton = findViewById(R.id.project_info_back)
            mailButton = findViewById(R.id.mail_button)
            wikiButton = findViewById(R.id.wiki_button)
            thumbnail = findViewById(R.id.badge)
            badgeCardView = findViewById(R.id.badge_card_view)
            header = findViewById(R.id.header)
            head = findViewById(R.id.head_name)
            headMail = findViewById(R.id.head_mail)
            headPhoneNumber = findViewById(R.id.head_phone_number)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.container, NavigationFragment.newInstance(isProjectInfo = true), "NavigationFragment")
            ?.commit()

        backButton.setOnClickListener {
            (requireActivity() as com.example.testtask.MainActivity).onBackPressed()
        }

        val mail = arguments?.get(EMAIL)
        val wiki = arguments?.get(WIKI)

        mailButton.setOnClickListener {
            copyToClipboard(mail as String)
        }
        wikiButton.setOnClickListener {
            copyToClipboard(wiki as String)
        }

        header.text = arguments?.get(HEADER) as CharSequence?
        head.text = arguments?.get(HEAD) as CharSequence?
        headMail.text = arguments?.get(HEAD_MAIL) as CharSequence?
        headPhoneNumber.text = arguments?.get(HEAD_PHONE) as CharSequence?

        val thumbnailURL = arguments?.get(THUMBNAIL) as String

        if (thumbnailURL.isEmpty()) {
            val images = resources.obtainTypedArray(R.array.thumbnails)
            val randomIndex = Random.nextInt(images.length())
            val resId = images.getResourceId(randomIndex, 0)

            thumbnail.setImageResource(resId)
            images.recycle()
        }
        else {
            val imageURL = getThumbnailURL(arguments?.get(PROJECT_ID) as String)

            Picasso.get()
                .load(imageURL)
                .into(thumbnail)
        }
    }

    private fun copyToClipboard(text: CharSequence){
        val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
    }

    companion object {

        private const val PROJECT_ID = "project_id"
        private const val THUMBNAIL = "thumbnail"
        private const val EMAIL = "email"
        private const val WIKI = "wiki"
        private const val HEADER = "header"
        private const val HEAD = "head"
        private const val HEAD_MAIL = "head_mail"
        private const val HEAD_PHONE = "head_phone"

        fun newInstance(
            projectNumber: String,
            projectId: String,
            thumbnail: String,
            email: String,
            wiki: String,
            header: String,
            head: String,
            headMail: String,
            headPhone: String
        ): ProjectInfoFragment {
            return ProjectInfoFragment(projectNumber).apply {
                arguments = bundleOf(
                    PROJECT_ID to projectId,
                    THUMBNAIL to thumbnail,
                    EMAIL to email,
                    WIKI to wiki,
                    HEADER to header,
                    HEAD to head,
                    HEAD_MAIL to headMail,
                    HEAD_PHONE to headPhone
                )
            }
        }
    }
}