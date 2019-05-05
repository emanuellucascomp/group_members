package com.raywenderlich.android.octomembers.ui.member

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.raywenderlich.android.octomembers.R
import com.raywenderlich.android.octomembers.model.Member
import com.raywenderlich.android.octomembers.repository.remote.RemoteRepository
import kotlinx.android.synthetic.main.activity_member.*

class MemberActivity : AppCompatActivity(), MemberContract.View {

  lateinit var presenter: MemberContract.Presenter

  companion object {
    const val EXTRA_MEMBER_LOGIN = "EXTRA_MEMBER_LOGIN"

    fun newIntent(context: Context, memberLogin: String): Intent {
      val intent = Intent(context, MemberActivity::class.java)
      intent.putExtra(EXTRA_MEMBER_LOGIN, memberLogin)
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_member)

    setupPresenter()
    setupActionBar()

    presenter.retrieveMember(memberLoginFromIntent())
  }

  private fun setupPresenter() {
    presenter = MemberPresenter(RemoteRepository(), this)
  }

  private fun setupActionBar() {
    title = memberLoginFromIntent()
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun memberLoginFromIntent() = intent.getStringExtra(EXTRA_MEMBER_LOGIN)

  override fun showMember(member: Member) {
    memberName.text = member.name
  }

  override fun showErrorRetrievingMember() {
    Toast.makeText(this, getString(R.string.error_retrieving_member), Toast.LENGTH_SHORT).show()
  }
}
