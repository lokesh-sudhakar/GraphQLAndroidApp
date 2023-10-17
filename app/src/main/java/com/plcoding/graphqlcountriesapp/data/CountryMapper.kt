package com.plcoding.graphqlcountriesapp.data

import com.plcoding.CountriesQuery
import com.plcoding.CountryQuery
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry? {

  return DetailedCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital?:"No Capital",
    currency= currency?: "No Currency",
    languages = languages.mapNotNull { it.name },
    continent = continent?.name?: "no continent"
  )

}

fun CountriesQuery.Country.toSimpleCountries(): SimpleCountry{
  return SimpleCountry(
    code = code,
    name= name,
    emoji=emoji,
    capital=capital?:"No Capital",
  )
}