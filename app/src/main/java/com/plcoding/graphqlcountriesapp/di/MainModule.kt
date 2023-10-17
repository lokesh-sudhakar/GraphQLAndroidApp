package com.plcoding.graphqlcountriesapp.di

import com.apollographql.apollo3.ApolloClient
import com.plcoding.graphqlcountriesapp.data.ApolloCountryClient
import com.plcoding.graphqlcountriesapp.domain.CountryClient
import com.plcoding.graphqlcountriesapp.domain.GetCountriesUseCases
import com.plcoding.graphqlcountriesapp.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

  @Provides
  @Singleton
  fun provideApolloClient(): ApolloClient {
    return ApolloClient.Builder()
      .serverUrl("https://countries.trevorblades.com/graphql")
      .build()
  }

  @Provides
  @Singleton
  fun provideCountryClient(apolloClient:ApolloClient): CountryClient{
    return ApolloCountryClient(apolloClient)
  }

  @Provides
  @Singleton
  fun provideCountriesUseCase(countryClient: CountryClient)
      : GetCountriesUseCases {
    return GetCountriesUseCases(countryClient)
  }

  @Provides
  @Singleton
  fun provideCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
    return GetCountryUseCase(countryClient)
  }


}