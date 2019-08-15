package com.starwars.flow.detail.film.viewmodel

import com.starwars.flow.detail.film.view.IView
import com.starwars.services.StarWarServices
import com.starwars.shared.base.IBaseViewModel

interface IViewModel:IBaseViewModel {
    fun init(view: IView, starWarServices: StarWarServices)
}