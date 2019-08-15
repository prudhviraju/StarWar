package com.starwars.shared.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.starwars.flow.dashboard.viewmodel.DashboardViewModel
import com.starwars.flow.detail.character.viewmodel.CharacterViewmodel
import com.starwars.flow.detail.film.viewmodel.FlimViewModel
import com.starwars.flow.detail.planets.viewmodel.PlanetViewModel
import com.starwars.flow.detail.species.viewmodel.SpeciesViewModel
import com.starwars.flow.detail.starships.viewmodel.StarViewModel
import com.starwars.flow.detail.vehicles.viewmodel.VehicleViewModel
import com.starwars.flow.detail.viewmodel.DetailViewModel

/**
 * Factory for ViewModels
 */
object StarWarViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> DashboardViewModel() as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel() as T
            modelClass.isAssignableFrom(CharacterViewmodel::class.java) -> CharacterViewmodel() as T
            modelClass.isAssignableFrom(FlimViewModel::class.java) -> FlimViewModel() as T
            modelClass.isAssignableFrom(PlanetViewModel::class.java) -> PlanetViewModel() as T
            modelClass.isAssignableFrom(SpeciesViewModel::class.java) -> SpeciesViewModel() as T
            modelClass.isAssignableFrom(StarViewModel::class.java) -> StarViewModel() as T
            modelClass.isAssignableFrom(VehicleViewModel::class.java) -> VehicleViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}