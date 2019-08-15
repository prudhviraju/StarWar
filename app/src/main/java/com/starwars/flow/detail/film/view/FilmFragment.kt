package com.starwars.flow.detail.film.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.starwars.R
import com.starwars.databinding.FragmentFilmBinding
import com.starwars.flow.detail.character.view.CharacterFragment
import com.starwars.flow.detail.film.viewmodel.FlimViewModel
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseFragment
import com.starwars.shared.viewmodel.StarWarViewModelFactory

class FilmFragment : BaseFragment(),IView{
    private lateinit var mBinding: FragmentFilmBinding
    private val mViewModel: FlimViewModel
            by lazy {
                ViewModelProviders.of(this, StarWarViewModelFactory)
                    .get(FlimViewModel::class.java)
            }


    companion object {
        const val TAG = "FILM_FRAGMENT"
        fun newInstance() = FilmFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_film,
            container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.init(this, StarWarServices.DashboardServicesCreator.newService(Gson()))
        mBinding.film = mViewModel
    }
}