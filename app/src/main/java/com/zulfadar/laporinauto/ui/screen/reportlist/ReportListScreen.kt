package com.zulfadar.laporinauto.ui.screen.reportlist

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zulfadar.laporinauto.data.remote.response.AllReportResponseItem
import com.zulfadar.laporinauto.ui.screen.common.UiState
import com.zulfadar.laporinauto.ui.screen.reportlist.component.HomeBottomBar
import com.zulfadar.laporinauto.ui.screen.reportlist.component.ReportCardItem

@Composable
fun ReportListScreen(
    modifier: Modifier = Modifier,
    viewModel: ReportListViewModel = viewModel()
) {
    val reportState by viewModel.reportListState.collectAsState(initial = UiState.Loading)

    Scaffold(
        modifier = modifier,
        topBar = {

        },
        bottomBar = {
            HomeBottomBar(
               onCreateClick = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            when (reportState) {
                is UiState.Loading -> {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val reports = (reportState as UiState.Success<List<AllReportResponseItem>>).data
                    Log.d("ReportListScreen", "Jumlah laporan: ${reports.size}")
                    if (reports.isNotEmpty()) {
                        ReportList(
                            modifier = Modifier,
                            reportList = reports
                        )
                    } else {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Tidak ada laporan ditemukan")
                        }
                    }
                }

                is UiState.Error -> {
                    val errorMsg = (reportState as UiState.Error).errorMessage
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = errorMsg,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { viewModel.getAllReport()},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Coba lagi",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReportList(
    modifier: Modifier = Modifier,
    reportList: List<AllReportResponseItem>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(reportList) { data ->
            ReportCardItem(
                modifier = Modifier.padding(12.dp),
                reportId = data.reportId ?: "reportId",
                createdAt = data.createdAt ?: "created at",
                status = data.reportStatus ?: "status",
                vehicleName = data.vehicleName ?: "Vehicle name",
                vehicleLicensePlate = data.vehicleLicenseNumber ?: "Vehicle License Plate",
                createdBy = data.createdBy ?: "created by",
                photo = data.photo ?: null,
                note = data.note ?: ""
            )
        }
    }
}
