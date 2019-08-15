package com.starwars.shared.base

import com.starwars.shared.viewmodel.ObservableViewModel

abstract class BaseViewModel : ObservableViewModel(), IBaseViewModel {

    open lateinit var baseView: IBaseView
}