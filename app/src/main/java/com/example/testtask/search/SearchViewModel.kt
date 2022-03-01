package com.example.testtask.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.protocol.API
import com.example.testtask.protocol.ProjectResponse
import com.example.testtask.search.recycler.model.ProjectTab
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    private val baseURL = "https://devcabinet.miem.vmnet.top/"

    private val convertedProjects = mutableListOf<ProjectTab>()
    private val _projects = MutableLiveData<List<ProjectTab>>()
    val projects: LiveData<List<ProjectTab>> = _projects

    private val service = API(baseURL).connectProjectService()

    fun start() {

        service.getProjectCatalog().enqueue(object: Callback<ProjectResponse> {

            override fun onFailure(call: Call<ProjectResponse>, t: Throwable) {
                Log.d("TAG_", "getProjectCatalog error! ${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ProjectResponse>,
                response: Response<ProjectResponse>
            ) {
                Log.d("TAG_", "getProjectCatalog successful call!")

                val projectCatalog = response.body()?.data

                if (projectCatalog != null) {

                    for (i in projectCatalog.indices) {

                        val currentProject = projectCatalog[i]

                        val projectTab = ProjectTab(
                            projectId = currentProject.id ?: "",
                            number = currentProject.number ?: "-",
                            header = currentProject.nameRus ?: "undefined",
                            head = currentProject.head ?: "undefined",
                            status = currentProject.statusDesc ?: "undefined",
                            type = currentProject.typeDesc ?: "undefined",
                            vacancy = currentProject.vacancies ?: "",
                            thumbnail = currentProject.thumbnail ?: ""
                        )

                        println(projectTab.toString())

                        convertedProjects.add(projectTab)
                        _projects.postValue(convertedProjects)
                    }
                }
            }
        })
    }
}