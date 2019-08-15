package com.starwars.flow.detail.starships.viewmodel

import com.starwars.flow.detail.starships.view.IView
import com.starwars.services.StarWarServices

interface IViewModel {
    fun init(mview: IView, starWarServices: StarWarServices)
}