package com.raywenderlich.android.octomembers.ui.teammembers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.raywenderlich.android.octomembers.R
import com.raywenderlich.android.octomembers.ui.extensions.hideKeyboard
import com.raywenderlich.android.octomembers.model.Member
import com.raywenderlich.android.octomembers.repository.remote.RemoteRepository
import kotlinx.android.synthetic.main.activity_team_members.*


class TeamMembersActivity : AppCompatActivity(), TeamMembersContract.View {

  lateinit var presenter: TeamMembersContract.Presenter
  lateinit var adapter: TeamMemberAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_team_members)

    setupPresenter()
    setupEditText()
    setupShowMembersButton()
    setupRecyclerView()
  }

  private fun setupPresenter() {
    presenter = TeamMembersPresenter(RemoteRepository(), this)
  }

  private fun setupEditText() {
    teamName.setSelection(teamName.text.length)
    teamName.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        showMembers.performClick()
        return@OnEditorActionListener true
      }
      false
    })
  }

  private fun setupShowMembersButton() {
    showMembers.setOnClickListener {
      val teamNameValue = teamName.text.toString()
      if (teamNameValue.isNotEmpty()) {
        presenter.retrieveAllMembers(teamNameValue)
      } else {
        showTeamNameEmptyError()
      }
    }
  }

  private fun setupRecyclerView() {
    teamMembersList.layoutManager = LinearLayoutManager(this)
    adapter = TeamMemberAdapter(listOf())
    teamMembersList.adapter = adapter
  }

  private fun showTeamNameEmptyError() {
    Toast.makeText(this, getString(R.string.error_team_name_empty), Toast.LENGTH_SHORT).show()
  }

  override fun showMembers(members: List<Member>) {
    teamMembersList.hideKeyboard()
    adapter.members = members
    adapter.notifyDataSetChanged()
  }

  override fun showErrorRetrievingMembers() {
    Toast.makeText(this, getString(R.string.error_retrieving_team), Toast.LENGTH_SHORT).show()
  }

  override fun clearMembers() {
    adapter.members = listOf()
    adapter.notifyDataSetChanged()
  }
}
