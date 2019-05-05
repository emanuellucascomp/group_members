
package com.raywenderlich.android.octomembers.repository.remote

import com.raywenderlich.android.octomembers.model.Member
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubApi {
  @GET("orgs/{org}/members")
  fun retrieveTeamMembers(@Path("org") teamName: String): Call<List<Member>>

  @GET("users/{login}")
  fun retrieveMember(@Path("login") login: String): Call<Member>
}