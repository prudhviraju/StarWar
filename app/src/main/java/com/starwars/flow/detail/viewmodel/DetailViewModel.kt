package com.starwars.flow.detail.viewmodel

import android.view.View
import androidx.databinding.Bindable
import com.starwars.BR
import com.starwars.flow.detail.view.IView
import com.starwars.shared.base.BaseViewModel

class DetailViewModel : BaseViewModel(),IViewModel{

    lateinit var mView: IView
    override fun init(mview:IView,header: String) {
        mView = mview
        title = header
    }

    override fun handleBackClick(view: View) {
        mView.navigateToBack()
    }

    var title = ""
        @Bindable
        get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
}
