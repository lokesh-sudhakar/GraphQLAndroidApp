package com.plcoding.graphqlcountriesapp.data

import com.apollographql.apollo3.ApolloClient
import com.plcoding.CountriesQuery
import com.plcoding.CountryQuery
import com.plcoding.graphqlcountriesapp.domain.CountryClient
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry

class ApolloCountryClient(
  private val apolloClient: ApolloClient
) : CountryClient {
  override suspend fun getCountries(): List<SimpleCountry> {
    return apolloClient
      .query(CountriesQuery())
      .execute()
      .data
      ?.countries
      ?.map { it.toSimpleCountries() }
      ?: emptyList()
  }

  override suspend fun getCountry(code: String): DetailedCountry? {
    return apolloClient.query(CountryQuery(country_code = code))
      .execute()
      .data
      ?.country
      ?.toDetailedCountry()
  }
}