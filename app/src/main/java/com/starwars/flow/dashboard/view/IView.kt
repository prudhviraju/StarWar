package com.starwars.flow.dashboard.view

import com.starwars.shared.base.IBaseView

interface IView:IBaseView {
    fun navigateToDetailScreen(type:String)
}