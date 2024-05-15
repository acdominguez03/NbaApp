package com.example.nbaapp.presentation.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaapp.databinding.TeamStandingRowItemBinding
import com.example.nbaapp.domain.model.TeamStandingModel

class TeamStandingViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = TeamStandingRowItemBinding.bind(view)

    fun render(teamStanding: TeamStandingModel, onClickListener: (Int) -> Unit) {
        binding.tvTeamName.text = teamStanding.teamName
        binding.tvWins.text = teamStanding.wins.toString()
        binding.tvLoses.text = teamStanding.loses.toString()
        binding.tvPosition.text = teamStanding.position.toString()

        itemView.setOnClickListener {
            onClickListener(teamStanding.teamId)
        }
    }
}