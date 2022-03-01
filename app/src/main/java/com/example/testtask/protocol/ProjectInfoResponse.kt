package com.example.testtask.protocol

import com.google.gson.annotations.SerializedName

data class ProjectInfoResponse (
    @SerializedName("message") var message: String? = null,
    @SerializedName("code") var code: Int? = null,
    @SerializedName("data") var data: Info? = Info()
)

data class MainLeader (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("fio") var fio: String?  = null,
    @SerializedName("email") var email: String?  = null,
    @SerializedName("telnum") var telnum: String?  = null,
    @SerializedName("trelloId") var trelloId: String?  = null,
    @SerializedName("role") var role: String?  = null,
    @SerializedName("status") var status: String?  = null,
    @SerializedName("ownerPrivilege") var ownerPrivilege : Boolean? = null
)

data class Info (
    @SerializedName("id") var id: String? = null,
    @SerializedName("nameRus") var nameRus: String? = null,
    @SerializedName("nameEng") var nameEng: String? = null,
    @SerializedName("number") var number: String? = null,
    @SerializedName("numberOrig") var numberOrig: Int? = null,
    @SerializedName("trello") var trello: String? = null,
    @SerializedName("googleGroup") var googleGroup: String? = null,
    @SerializedName("chat") var chat: String? = null,
    @SerializedName("wiki") var wiki: String? = null,
    @SerializedName("typeValue") var typeValue: Int? = null,
    @SerializedName("typeLabel") var typeLabel: String? = null,
    @SerializedName("sourceValue") var sourceValue: Int? = null,
    @SerializedName("sourceLabel") var sourceLabel: String? = null,
    @SerializedName("teamValue") var teamValue: Int? = null,
    @SerializedName("teamLabel") var teamLabel: String? = null,
    @SerializedName("statusValue") var statusValue: Int? = null,
    @SerializedName("statusLabel") var statusLabel: String? = null,
    @SerializedName("projectOffice") var projectOffice: Boolean? = null,
    @SerializedName("exportControl") var exportControl: Boolean? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("projectYearInfo") var projectYearInfo: Int? = null,
    @SerializedName("wrongYear") var wrongYear: Boolean? = null,
    @SerializedName("showAchievements") var showAchievements: Boolean? = null,
    @SerializedName("mainLeader") var mainLeader: ArrayList<MainLeader> = arrayListOf(),
    @SerializedName("inProject") var inProject: Boolean? = null,
    @SerializedName("projectOwner") var projectOwner: Boolean? = null,
    @SerializedName("initiator") var initiator: Boolean? = null,
    @SerializedName("passportComplete") var passportComplete: Boolean? = null,
    @SerializedName("onRevise") var onRevise: Boolean? = null,
)