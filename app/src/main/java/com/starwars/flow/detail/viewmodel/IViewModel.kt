package com.starwars.flow.detail.viewmodel

import android.view.View
import com.starwars.flow.detail.view.IView

interface IViewModel {
    fun init(view:IView,header:String)
    fun handleBackClick(view:View)
}