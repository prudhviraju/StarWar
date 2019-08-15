package com.starwars.shared.base

import android.app.Application
import com.starwars.shared.viewmodel.ObservableAndroidViewModel

abstract class BaseAndroidViewModel(application: Application)
    : ObservableAndroidViewModel(application), IBaseViewModel {

    open lateinit var baseView: IBaseView

}