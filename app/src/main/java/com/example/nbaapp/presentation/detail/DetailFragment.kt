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
import com.example.nbaapp.databinding.FragmentDetailBinding
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
                state.team?.let { team ->
                    Log.d("TAG", team.logo)
                    binding.tvName.text = team.name
                    Glide.with(this@DetailFragment).load(team.logo)
                        .into(binding.ivLogo)
                    binding.tvTotalGamesStats.text = "${team.totalWins} W - ${team.totalLoses} L"
                    binding.tvTotalAveragePoints.text = "Media de ${team.totalAveragePointFor} a favor y ${team.totalAveragePointAgainst} en contra"
                    binding.tvWinsAtHome.text = "Victorias: ${team.gamesWinAtHome}"
                    binding.tvWinsAway.text = "Victorias: ${team.gamesWinAway}"
                    binding.tvLosesAtHome.text = "Derrotas: ${team.gamesLoseAtHome}"
                    binding.tvLosesAway.text = "Derrotas: ${team.gamesLoseAway}"
                    binding.tvPointForAtHome.text = "Puntos a favor: ${team.pointForAtHome}"
                    binding.tvPointForAway.text = "Puntos a favor: ${team.pointForAway}"
                    binding.tvAveragePointForAtHome.text = "Media de puntos: ${team.averagePointForAtHome}"
                    binding.tvAveragePointForAway.text = "Media de puntos: ${team.averagePointForAway}"
                    binding.tvPointAgainstAtHome.text = "Puntos en contra: ${team.pointAgainstAtHome}"
                    binding.tvPointAgainstAway.text = "Puntos en contras: ${team.pointAgainstAway}"
                    binding.tvAveragePointAgainstAtHome.text = "Media de puntos: ${team.averagePointAgainstAtHome}"
                    binding.tvAveragePointAgainstAway.text = "Media de puntos: ${team.averagePointAgainstAway}"

                }
            }
        }

        Log.d("TAG", viewmodel.uiState.value.team?.logo.toString())

        return binding.root
    }
}