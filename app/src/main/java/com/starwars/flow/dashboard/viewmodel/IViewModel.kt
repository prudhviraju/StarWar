package com.starwars.flow.dashboard.viewmodel

import android.view.View
import com.starwars.flow.dashboard.view.IView

interface IViewModel {
    fun init(view: IView)
    fun handleItemClick(position:Int)

}