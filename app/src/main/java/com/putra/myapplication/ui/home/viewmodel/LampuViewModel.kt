package com.hanif.deteksiperson.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanif.detectionperson.data.Resource
import com.hanif.deteksiperson.data.model.LampuModel
import com.hanif.deteksiperson.data.repo.LampuRepository
import com.ptpws.agrogontafarm.data.models.BayiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LampuViewModel @Inject constructor(private val lampuRepository: LampuRepository) : ViewModel() {

    private val _bayiState = MutableStateFlow<Resource<List<BayiModel>>?>(null)
    val bayiState: StateFlow<Resource<List<BayiModel>>?> = _bayiState


    val lampu: StateFlow<List<LampuModel>> = lampuRepository.lampu

    init {
        lampuRepository.getLampu()
    }

    init {
        viewModelScope.launch {
            getBayi()
        }
    }


    fun updateLampuStatus(room: String, lamp: String, status: String) {
        lampuRepository.updateLampuStatus(room, lamp, status)
    }


    fun getBayi() {
        viewModelScope.launch {
            _bayiState.value = Resource.Loading// Set loading state
            val result = lampuRepository.getBayi() // Call the repository function
            _bayiState.value = result // Update the state with the result
        }
    }

}