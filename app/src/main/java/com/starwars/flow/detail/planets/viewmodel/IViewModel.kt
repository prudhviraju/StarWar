package com.starwars.flow.detail.planets.viewmodel

import com.starwars.flow.detail.planets.view.IView
import com.starwars.services.StarWarServices

interface IViewModel {
    fun init(view: IView, starWarServices: StarWarServices)
}