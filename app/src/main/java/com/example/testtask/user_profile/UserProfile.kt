package com.example.testtask.user_profile

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R
import com.example.testtask.protocol.API
import com.example.testtask.protocol.UserService
import com.example.testtask.protocol.getUserAvatarURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_profile.*

class UserProfile: Fragment() {

    private lateinit var viewModel: UserProfileViewModel

    private lateinit var avatar: ImageView
    private lateinit var userName: TextView
    private lateinit var status: TextView

    private val baseURL = "https://devcabinet.miem.vmnet.top/"

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_profile, container, false).apply {
            avatar = findViewById(R.id.avatar)
            userName = findViewById(R.id.user_name)
            status = findViewById(R.id.status)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.statusBarColor = resources.getColor(R.color.blue_grey)

        val name = (arguments?.get(USERNAME) as CharSequence?)?.split(" ")
        val firstName = name?.get(1) ?: "User"
        val secondName = name?.get(0) ?: "Name"

        userName.text = buildString {
            append(firstName)
            append("\n")
            append(secondName)
        }

        if (arguments?.get(STATUS) as Boolean) {
            status.text = "Сотрудник"
        } else {
            status.text = "Студент"
        }

        val userID = arguments?.get(USER_ID) as String
        val avatarURL = getUserAvatarURL(baseURL, userID)

        val avatarWidth = (avatar.layoutParams.width/2.75).toInt()
        val avatarHeight = (avatar.layoutParams.height/2.75).toInt()

        Picasso.get()
            .load(avatarURL)
            .resize(avatarWidth, avatarHeight)
            .centerInside()
            .into(avatar)
    }

    companion object {

        private const val USER_ID = "userID"
        private const val USERNAME = "userName"
        private const val STATUS = "status"
        private const val PROJECT_NUMBER = "projectNumber"

        fun newInstance(
            userID: String,
            userName: String,
            status: Boolean,
            projectNumber: String?,
        ): UserProfile {
            return UserProfile().apply {
                arguments = bundleOf(
                    USER_ID to userID,
                    USERNAME to userName,
                    STATUS to status,
                    PROJECT_NUMBER to projectNumber
                )
            }
        }
    }
}