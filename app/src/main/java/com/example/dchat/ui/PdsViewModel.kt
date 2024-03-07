package com.example.dchat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dchat.network.pds.Health
import com.example.dchat.network.pds.PdsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.dsl.module

val pdsViewModelModule = module {
    factory { PdsViewModel(get()) }
}

class PdsViewModel(
    private val pdsRepo: PdsRepository
) : ViewModel() {

    private val _healthFlow = MutableStateFlow<Health?>(null)
    val healthFlow: StateFlow<Health?> = _healthFlow

    init {
        getHealth()
    }

    private fun getHealth() {
        viewModelScope.launch(Dispatchers.IO) {
            _healthFlow.update {
                pdsRepo.getHealth()
            }
        }
    }
}
