package com.zulfadar.laporinauto.ui.screen.reportlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.laporinauto.data.remote.connection.ApiConfig
import com.zulfadar.laporinauto.data.remote.connection.ApiService
import com.zulfadar.laporinauto.data.remote.response.AllReportResponseItem
import com.zulfadar.laporinauto.ui.screen.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReportListViewModel(
    private val reportApiService: ApiService = ApiConfig.getApiService(),
): ViewModel() {
    private val _reportListState = MutableStateFlow<UiState<List<AllReportResponseItem>>>(UiState.Loading)
    val reportListState: StateFlow<UiState<List<AllReportResponseItem>>> = _reportListState

    init {
        getAllReport()
    }

    fun getAllReport() {
        viewModelScope.launch {
            _reportListState.value = UiState.Loading
            try {
                val response = reportApiService.getAllLaporan()
                _reportListState.value = UiState.Success(response)
            } catch (e: Exception) {
                _reportListState.value = UiState.Error("Gagal memuat data: ${e.message}")
            }
        }
    }
}