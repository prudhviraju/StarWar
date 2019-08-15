package com.starwars.flow.detail.character.viewmodel

import com.starwars.flow.detail.character.view.IView
import com.starwars.services.StarWarServices

interface IViewModel {
    fun init(view:IView, starWarServices:StarWarServices)
}