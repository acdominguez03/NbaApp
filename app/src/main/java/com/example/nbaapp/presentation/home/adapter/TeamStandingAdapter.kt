package com.example.nbaapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaapp.R
import com.example.nbaapp.domain.model.TeamStandingModel

class TeamStandingAdapter(
    private var teamStandingList: List<TeamStandingModel>,
    private val onClickListener: (Int) -> Unit
): RecyclerView.Adapter<TeamStandingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamStandingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamStandingViewHolder(layoutInflater.inflate(R.layout.team_standing_row_item, parent, false))
    }

    override fun getItemCount(): Int = teamStandingList.size

    override fun onBindViewHolder(holder: TeamStandingViewHolder, position: Int) {
        holder.render(
            teamStanding = teamStandingList[position],
            onClickListener
        )
    }

    fun setData(teamStandingList: List<TeamStandingModel>) {
        this.teamStandingList = teamStandingList
    }
}