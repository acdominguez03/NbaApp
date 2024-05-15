package com.example.nbaapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaapp.data.Result
import com.example.nbaapp.domain.model.TeamStandingModel
import com.example.nbaapp.domain.use_case.GetAllTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetAllTeamsUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<HomeState> by lazy { MutableStateFlow(HomeState()) }
    val uiState: StateFlow<HomeState> get() = _uiState

    init {
        viewModelScope.launch {
            useCase.invoke().collect { result ->
                when(result) {
                    is Result.Error -> TODO()
                    is Result.Loading -> {
                        _uiState.tryEmit(
                            _uiState.value.copy(
                                isLoading = true
                            )
                        )
                    }
                    is Result.Success -> {
                        val westernList = result.data?.filter {
                            it.conference == "Western Conference"
                        }
                        val easternList = result.data?.filter {
                            it.conference == "Eastern Conference"
                        }
                        _uiState.tryEmit(
                            _uiState.value.copy(
                                isLoading = false,
                                westernTeamsList = westernList ?: emptyList(),
                                easternTeamsList = easternList ?: emptyList()
                            )
                        )
                    }
                }
            }
        }
    }

    fun changeTeamListConference() {
        _uiState.tryEmit(
            _uiState.value.copy(
                isWesternConference = !uiState.value.isWesternConference
            )
        )
    }
    data class HomeState(
        val isLoading: Boolean = false,
        val westernTeamsList: List<TeamStandingModel> = emptyList(),
        val easternTeamsList: List<TeamStandingModel> = emptyList(),
        val isWesternConference: Boolean = true
    )
}