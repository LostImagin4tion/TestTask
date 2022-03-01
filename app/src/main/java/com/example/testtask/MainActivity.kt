package com.example.testtask

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testtask.exit.ExitFragment
import com.example.testtask.menu.MenuFragment
import com.example.testtask.project_info.ProjectInfoFragment
import com.example.testtask.protocol.*
import com.example.testtask.search.SearchFragment
import com.example.testtask.user_profile.UserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity() : AppCompatActivity() {

    private val baseURL = "https://devcabinet.miem.vmnet.top/"

    private val userService = API(baseURL).connectUserService()
    private val projectService = API(baseURL).connectProjectService()

    private var userID: String = ""
    private var userName: String = ""
    private var userStatus: Boolean = false
    private lateinit var userEmail: String
    private lateinit var userProject: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MenuFragment.newInstance(), "MenuFragment")
                .commit()
        }
    }

    fun goMenu() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun goUserProfile(email: String = userEmail, projectNumber: String = userProject) {

        userEmail = email
        userProject = projectNumber

        userService.getUserInfo(email).enqueue(object: Callback<UserResponse> {

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("TAG_", "getUserInfo error! ${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d("TAG_", "getUserInfo successful call! ${response.body()}")

                val userData = response.body()?.data

                if (userData != null) {
                    userID = userData.userId.toString()
                    userName = userData.fullName.toString()
                    userStatus = userData.isTeacher == true
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UserProfile.newInstance(
                        userID = userID,
                        userName = userName,
                        status = userStatus,
                        projectNumber = projectNumber,
                    ))
                    .addToBackStack("UserProfile")
                    .commit()
            }
        })
    }

    fun goProjectInfo(projectNumber:String = userProject) {

        projectService.getProjectInfo(projectNumber).enqueue(object: Callback<ProjectInfoResponse> {

            override fun onFailure(call: Call<ProjectInfoResponse>, t: Throwable) {
                Log.d("TAG_", "getProjectInfo error! ${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ProjectInfoResponse>,
                response: Response<ProjectInfoResponse>
            ) {
                Log.d("TAG_", "get ProjectInfo successful call! ${response.body()}")

                val projectInfo = response.body()?.data

                val projectId = projectInfo?.id ?: ""
                val thumbnail = projectInfo?.thumbnail ?: ""
                val email = projectInfo?.googleGroup ?: ""
                val wiki = projectInfo?.wiki ?: ""
                val header = projectInfo?.nameRus ?: ""
                val head = projectInfo?.mainLeader?.get(0)?.fio ?: ""
                val headMail = projectInfo?.mainLeader?.get(0)?.email ?: ""
                val headPhone = projectInfo?.mainLeader?.get(0)?.telnum ?: ""

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ProjectInfoFragment.newInstance(
                        projectNumber = projectNumber,
                        projectId = projectId,
                        thumbnail = thumbnail,
                        email = email,
                        wiki = wiki,
                        header = header,
                        head = head,
                        headMail = headMail,
                        headPhone = headPhone)
                    )
                    .addToBackStack("ProjectInfo")
                    .commit()
            }
        })
    }

    fun goSearch() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment.newInstance())
            .addToBackStack("Search")
            .commit()
    }

    fun goExitWarning() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, ExitFragment.newInstance())
            .addToBackStack("Exit")
            .commit()
    }

    fun isBackStackSize() = supportFragmentManager.backStackEntryCount > 1

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}