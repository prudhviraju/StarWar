package com.starwars.flow.detail.species.viewmodel

import com.starwars.flow.detail.species.view.IView
import com.starwars.services.StarWarServices

interface IViewModel {
    fun init(mview:IView, starWarServices: StarWarServices)
}