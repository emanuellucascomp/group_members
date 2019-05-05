package com.raywenderlich.android.octomembers.repository

import com.raywenderlich.android.octomembers.model.Member
import retrofit2.Callback

interface Repository {
  fun retrieveTeamMembers(teamName: String, callback: Callback<List<Member>>)
  fun retrieveMember(login: String, callback: Callback<Member>)
}