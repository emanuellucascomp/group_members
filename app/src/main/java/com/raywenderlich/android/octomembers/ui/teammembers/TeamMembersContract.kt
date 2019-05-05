package com.raywenderlich.android.octomembers.ui.teammembers

import com.raywenderlich.android.octomembers.model.Member


interface TeamMembersContract {

  interface View {
    fun showMembers(members: List<Member>)
    fun showErrorRetrievingMembers()
    fun clearMembers()
  }

  interface Presenter {
    fun retrieveAllMembers(teamName: String)
  }
}