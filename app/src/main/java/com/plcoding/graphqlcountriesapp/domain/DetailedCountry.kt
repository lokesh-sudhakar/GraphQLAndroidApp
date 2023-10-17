package com.plcoding.graphqlcountriesapp.domain

import com.plcoding.type.Continent
import java.util.Currency

data class DetailedCountry(
  var code: String,
  var name: String,
  var emoji: String,
  var capital: String,
  var currency: String,
  val languages: List<String>,
  val continent: String
)
