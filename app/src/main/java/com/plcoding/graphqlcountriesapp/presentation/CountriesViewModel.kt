package com.plcoding.graphqlcountriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.GetCountriesUseCases
import com.plcoding.graphqlcountriesapp.domain.GetCountryUseCase
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
  private val countriesUseCases: GetCountriesUseCases,
  private val countryUseCase: GetCountryUseCase,
) : ViewModel() {

  private val _state = MutableStateFlow(CountriesState())
  val state = _state.asStateFlow()
  data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: DetailedCountry?=null
  )

  init {
    viewModelScope.launch {
      _state.update {
        it.copy(
          isLoading = true
        )
      }
      _state.update {
        it.copy(
          countries = countriesUseCases.execute(),
          isLoading = false
        )
      }
    }
  }

  fun selectCountry(countryCode: String) {
    viewModelScope.launch {
      _state.update {
        it.copy(
          selectedCountry = countryUseCase.getCountry(countryCode)
        )
      }
    }
  }

  fun dismissCountryDialog(){
    _state.update {
      it.copy(
        selectedCountry = null
      )
    }
  }





}