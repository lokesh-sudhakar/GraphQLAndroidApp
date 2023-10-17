package com.plcoding.graphqlcountriesapp.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry


@Composable
fun CountryScreen(
  state: CountriesViewModel.CountriesState,
  onSelectCountry: (String) -> Unit,
  onDismissCountryDialog: () -> Unit
) {

  Box(Modifier.fillMaxSize()) {
    if (state.isLoading) {
      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center)
      )
    } else {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.countries) { country ->

          CountryItem(
            Modifier
              .fillMaxWidth()
              .padding(bottom = 12.dp)
              .clickable {
                onSelectCountry(country.code)
              }, country
          )

        }
      }
      state.selectedCountry?.let {
        CountryDialog(
          modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(16.dp),
          country = it, onDismiss = onDismissCountryDialog
        )
      }
    }
  }


}

@Composable
fun CountryItem(modifier: Modifier, country: SimpleCountry) {

  Row(modifier = modifier) {
    Text(text = country.emoji, fontSize = 30.sp)
    Spacer(modifier = Modifier.width(16.dp))
    Column(modifier = Modifier.weight(1f)) {
      Text(text = country.name, fontSize = 16.sp)
      Text(text = country.capital, fontSize = 16.sp)
    }
  }
}

@Composable
fun CountryDialog(modifier: Modifier, country: DetailedCountry, onDismiss: () -> Unit) {

  val joinedLanguages = remember(country.languages) {
    country.languages.joinToString(separator = ",")
  }

  Dialog(onDismissRequest = { onDismiss() }) {
    Column(modifier = modifier) {
      Row(modifier=Modifier.fillMaxWidth()) {
        Text(text = country.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = country.name, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))
      }
      Spacer(modifier = Modifier.height(16.dp))
      Text(text = "Continent: ${country.continent}")
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = "Currency: ${country.currency}")
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = "Capital: ${country.capital}")
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = "Language(s): ${joinedLanguages}")
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}