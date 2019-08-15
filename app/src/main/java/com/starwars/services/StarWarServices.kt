package com.starwars.services

import com.google.gson.Gson
import com.starwars.flow.dashboard.model.GetTypesResponse
import com.starwars.flow.detail.character.model.CharacterResponse
import com.starwars.flow.detail.film.model.FilmsResponse
import com.starwars.flow.detail.planets.model.PlanetResponse
import com.starwars.flow.detail.species.model.SpeciesResponse
import com.starwars.flow.detail.starships.model.StarShipsResponse
import com.starwars.flow.detail.vehicles.model.VehiclesResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface StarWarServices {

    @GET("/api")
    fun getTypes(): Observable<GetTypesResponse>

    @GET("/api/people")
    fun getCharacters(@Query("page") page:Int): Observable<CharacterResponse>

    @GET("/api/films")
    fun getFilms(): Observable<FilmsResponse>

    @GET("/api/planets")
    fun getPlanets(@Query("page") page:Int): Observable<PlanetResponse>

    @GET("/api/starships")
    fun getStarShips(@Query("page") page:Int): Observable<StarShipsResponse>

    @GET("/api/species")
    fun getSpecies(@Query("page") page:Int): Observable<SpeciesResponse>

    @GET("/api/vehicles")
    fun getVehicles(@Query("page") page:Int): Observable<VehiclesResponse>

    object DashboardServicesCreator {
        fun newService(gson: Gson): StarWarServices {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(interceptor)
            httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
            httpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
            httpClientBuilder.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }

            val retrofit = Retrofit.Builder()
                .baseUrl("https://swapi.co/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build()
            return retrofit.create(StarWarServices::class.java)
        }
    }
}