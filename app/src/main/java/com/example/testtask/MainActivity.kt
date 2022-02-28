package com.example.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testtask.menu.MenuFragment
import com.example.testtask.protocol.*
import com.example.testtask.user_profile.UserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity() : AppCompatActivity() {

    private val baseURL = "https://devcabinet.miem.vmnet.top/"

    private val service = API(baseURL).connect()

    private var userID: String = ""
    private var userName: String = ""
    private var userStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MenuFragment.newInstance())
                .commit()
        }
    }

    fun goUserProfile(email: String, projectNumber: String?) {

        service.getUserInfo(email).enqueue(object: Callback<UserResponse> {

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("TAG_", "getUserInfo error! ${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d("TAG_", response.body().toString())

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
                        projectNumber= projectNumber,
                    ))
                    .addToBackStack("UserProfile")
                    .commit()
            }
        })
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}