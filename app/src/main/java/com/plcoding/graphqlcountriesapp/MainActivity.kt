package com.plcoding.graphqlcountriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.graphqlcountriesapp.presentation.CountriesViewModel
import com.plcoding.graphqlcountriesapp.presentation.CountryScreen
import com.plcoding.graphqlcountriesapp.ui.theme.GraphQlCountriesAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val viewModel: CountriesViewModel = hiltViewModel()
      val state = viewModel.state.collectAsState()
      GraphQlCountriesAppTheme {
        CountryScreen(
          state = state.value,
          onSelectCountry = viewModel::selectCountry,
          onDismissCountryDialog = viewModel::dismissCountryDialog
        )
      }
    }
  }
}