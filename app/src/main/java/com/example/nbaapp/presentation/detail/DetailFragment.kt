package com.example.nbaapp.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.nbaapp.R
import com.example.nbaapp.databinding.FragmentDetailBinding
import com.example.nbaapp.domain.model.TeamDetailModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewmodel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        Log.d("TAG", viewmodel.uiState.value.team?.logo.toString())

        lifecycleScope.launch {
            viewmodel.uiState.collect { state ->

                if (state.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }

                if (state.errorMessage.isNotEmpty()) {
                    binding.tvError.text = state.errorMessage
                }

                state.team?.let { team ->
                    showTeamData(team)
                }
            }
        }
        return binding.root
    }

    private fun showTeamData(team: TeamDetailModel) {
        binding.tvName.text = team.name
        Glide.with(this@DetailFragment).load(team.logo).into(binding.ivLogo)
        binding.tvTotalGamesStats.text = getString(R.string.total_games_stats, team.totalWins.toString(), team.totalLoses.toString())
        binding.tvTotalAveragePoints.text = getString(R.string.total_average_points, team.totalAveragePointFor, team.totalAveragePointAgainst)
        binding.tvLocal.text = getString(R.string.local)
        binding.tvAway.text = getString(R.string.away)

        showLocalData(team)
        showAwayData(team)
    }

    private fun showLocalData(team: TeamDetailModel) {
        binding.tvWinsAtHome.text = getString(R.string.wins_at_home, team.gamesWinAtHome.toString())
        binding.tvLosesAtHome.text = getString(R.string.loses_at_home, team.gamesLoseAtHome.toString())
        binding.tvPointForAtHome.text = getString(R.string.points_for_at_home, team.pointForAtHome.toString())
        binding.tvAveragePointForAtHome.text = getString(R.string.average_points_for_at_home, team.averagePointForAtHome)
        binding.tvPointAgainstAtHome.text = getString(R.string.points_against_at_home, team.pointAgainstAtHome.toString())
        binding.tvAveragePointAgainstAtHome.text = getString(R.string.average_points_against_at_home, team.averagePointAgainstAtHome)
    }

    private fun showAwayData(team: TeamDetailModel) {
        binding.tvWinsAway.text = getString(R.string.wins_away, team.gamesWinAway.toString())
        binding.tvLosesAway.text = getString(R.string.loses_away, team.gamesLoseAway.toString())
        binding.tvPointForAway.text = getString(R.string.points_for_away, team.pointForAway.toString())
        binding.tvAveragePointForAway.text = getString(R.string.average_point_for_away, team.averagePointForAway)
        binding.tvPointAgainstAway.text = getString(R.string.points_against_away, team.pointAgainstAway.toString())
        binding.tvAveragePointAgainstAway.text = getString(R.string.average_points_against_away, team.averagePointAgainstAway)
    }
}