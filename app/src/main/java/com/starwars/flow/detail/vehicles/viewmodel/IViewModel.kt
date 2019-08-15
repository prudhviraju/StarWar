package com.starwars.flow.detail.vehicles.viewmodel

import com.starwars.flow.detail.vehicles.view.IView
import com.starwars.services.StarWarServices

interface IViewModel {
    fun init(mview: IView, starWarServices: StarWarServices)
}