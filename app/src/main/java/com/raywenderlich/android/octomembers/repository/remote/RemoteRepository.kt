package com.raywenderlich.android.octomembers.repository.remote

import com.raywenderlich.android.octomembers.model.Member
import com.raywenderlich.android.octomembers.repository.Repository
import retrofit2.Callback
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RemoteRepository : Repository {

  val api: GitHubApi

  init {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.github.com")
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    api = retrofit.create<GitHubApi>(GitHubApi::class.java)
  }

  override fun retrieveTeamMembers(teamName: String, callback: Callback<List<Member>>) {
    api.retrieveTeamMembers(teamName).enqueue(callback)
  }

  override fun retrieveMember(login: String, callback: Callback<Member>) {
    api.retrieveMember(login).enqueue(callback)
  }
}