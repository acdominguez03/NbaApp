package com.example.nbaapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaapp.R
import com.example.nbaapp.databinding.FragmentHomeBinding
import com.example.nbaapp.domain.model.TeamStandingModel
import com.example.nbaapp.presentation.home.adapter.TeamStandingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewmodel: HomeViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var teamStandingAdapter: TeamStandingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        recyclerView = binding.rvTeams

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

                if (state.isWesternConference) {
                    teamStandingAdapter = TeamStandingAdapter(state.westernTeamsList) { teamId ->
                        onItemSelected(teamId)
                    }
                    binding.tvConference.text = getString(R.string.western_conference)
                } else {
                    teamStandingAdapter = TeamStandingAdapter(state.easternTeamsList) { teamId ->
                        onItemSelected(teamId)
                    }
                    binding.tvConference.text = getString(R.string.eastern_conference)
                }

                recyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = teamStandingAdapter
                }
            }
        }

        binding.btnChangeConference.setOnClickListener {
            viewmodel.changeTeamListConference()
        }

        return binding.root
    }

    private fun onItemSelected(teamId: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(teamId))
    }
}