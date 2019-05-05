package com.raywenderlich.android.octomembers.ui.member

import com.raywenderlich.android.octomembers.model.Member


interface MemberContract {
  interface View {
    fun showMember(member: Member)
    fun showErrorRetrievingMember()
  }

  interface Presenter {
    fun retrieveMember(login: String)
  }
}