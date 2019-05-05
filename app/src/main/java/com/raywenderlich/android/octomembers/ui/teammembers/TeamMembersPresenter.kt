package com.raywenderlich.android.octomembers.ui.teammembers

import com.raywenderlich.android.octomembers.model.Member
import com.raywenderlich.android.octomembers.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TeamMembersPresenter(val repository: Repository, val view: TeamMembersContract.View) : TeamMembersContract.Presenter {

  override fun retrieveAllMembers(teamName: String) {
    repository.retrieveTeamMembers(teamName, object : Callback<List<Member>> {
      override fun onResponse(call: Call<List<Member>>?, response: Response<List<Member>>?) {
        val members = response?.body()
        if (members != null) {
          view.showMembers(members)
        } else {
          clearViewMembersAndShowError()
        }
      }

      override fun onFailure(call: Call<List<Member>>?, t: Throwable?) {
        clearViewMembersAndShowError()
      }
    })
  }

  private fun clearViewMembersAndShowError() {
    view.clearMembers()
    view.showErrorRetrievingMembers()
  }
}