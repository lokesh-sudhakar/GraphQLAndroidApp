package com.plcoding.graphqlcountriesapp.domain

class GetCountryUseCase(private val countryClient: CountryClient) {

  suspend fun getCountry(code:String): DetailedCountry?{
    return countryClient.getCountry( code)
  }
}