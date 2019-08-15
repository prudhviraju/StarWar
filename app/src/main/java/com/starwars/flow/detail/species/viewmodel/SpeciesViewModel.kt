package com.starwars.flow.detail.species.viewmodel

import com.starwars.R
import com.starwars.flow.detail.species.adapter.SpeciesAdapter
import com.starwars.flow.detail.species.model.Result
import com.starwars.flow.detail.species.view.IView
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SpeciesViewModel : BaseViewModel(),IViewModel{

    private lateinit var mView: IView
    lateinit var mstarWarServices: StarWarServices
    private val adapter = SpeciesAdapter(R.layout.custom_species_list, this)
    val speciesList = ArrayList<Result>()
    var pageNumber = 1
    override fun init(mview: IView, starWarServices: StarWarServices) {
        mView = mview
        mstarWarServices = starWarServices
        speciesList.clear()
        loadSpeciesList()
    }

    fun loadSpeciesList(){
        if(pageNumber == 1)
            mView.showProgressBar()
        mstarWarServices.getSpecies(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    mView.hideProgressBar()
                },
                onNext = {
                    mView.hideProgressBar()
                    speciesList.addAll(it.results)
                    adapter.setSpecies(speciesList)
                }
            )
    }

    fun getSpeciesDetails(position: Int): Result {
        return speciesList[position]
    }

    fun getSpeciesImage(position: Int):String{
        val url = speciesList[position].url.substring(0,speciesList[position].url.length-1)
        return url.replace("https://swapi.co/api","")
    }

    fun getAdapter(): SpeciesAdapter? {
        return adapter
    }

}