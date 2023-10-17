package com.plcoding.graphqlcountriesapp.domain

// single responsibility usecase
class GetCountriesUseCases(val countryClient: CountryClient) {

  suspend fun execute(): List<SimpleCountry>{
    return countryClient.getCountries().sortedBy { it.name }
  }

}