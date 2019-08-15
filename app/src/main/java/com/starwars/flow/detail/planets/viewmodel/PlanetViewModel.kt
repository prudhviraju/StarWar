package com.starwars.flow.detail.planets.viewmodel

import com.starwars.R
import com.starwars.flow.detail.planets.adapter.PlanetAdapter
import com.starwars.flow.detail.planets.view.IView
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PlanetViewModel : BaseViewModel(),IViewModel{

    private lateinit var mView: IView
    lateinit var mstarWarServices: StarWarServices
    private val adapter = PlanetAdapter(R.layout.custom_planet_list, this)
    val planetList = ArrayList<com.starwars.flow.detail.planets.model.Result>()
    var pageNumber = 1
    override fun init(view: IView, starWarServices: StarWarServices) {
        mView = view
        mstarWarServices = starWarServices
        planetList.clear()
        loadPlanetsList()
    }

    fun loadPlanetsList(){
        if(pageNumber == 1)
            mView.showProgressBar()
        mstarWarServices.getPlanets(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    mView.hideProgressBar()
                },
                onNext = {
                    mView.hideProgressBar()
                    planetList.addAll(it.results)
                    adapter.setPlanets(planetList)
                }
            )
    }

    fun getPlanetDetails(position: Int): com.starwars.flow.detail.planets.model.Result {
        return planetList[position]
    }

    fun getPlanetImage(position: Int):String{
        val url = planetList[position].url.substring(0,planetList[position].url.length-1)
        return url.replace("https://swapi.co/api","")
    }

    fun getAdapter(): PlanetAdapter? {
        return adapter
    }
}