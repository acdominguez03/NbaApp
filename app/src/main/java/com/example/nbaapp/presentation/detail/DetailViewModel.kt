package com.example.nbaapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaapp.data.Result
import com.example.nbaapp.domain.model.TeamDetailModel
import com.example.nbaapp.domain.model.TeamStandingModel
import com.example.nbaapp.domain.use_case.GetTeamDetailUseCase
import com.example.nbaapp.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetTeamDetailUseCase,
    private val state: SavedStateHandle
): ViewModel() {

    private val _uiState: MutableStateFlow<DetailState> by lazy { MutableStateFlow(DetailState()) }
    val uiState: StateFlow<DetailState> get() = _uiState

    init {
        viewModelScope.launch {
            state.get<Int>("teamId")?.let {
                useCase.invoke(it).collect { result ->
                    when(result) {
                        is Result.Error -> {}
                        is Result.Loading -> {}
                        is Result.Success ->  {
                            _uiState.tryEmit(
                                _uiState.value.copy(
                                    team = result.data
                                )
                            )
                        }
                    }
                }
            }
        }
    }



    data class DetailState(
        val isLoading: Boolean = false,
        val team: TeamDetailModel? = null
    )
}