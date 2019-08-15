package com.starwars.flow.detail.starships.viewmodel

import com.starwars.R
import com.starwars.flow.detail.starships.adapter.StarShipsAdapter
import com.starwars.flow.detail.starships.view.IView
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class StarViewModel : BaseViewModel(),IViewModel{
    private lateinit var mView: IView
    lateinit var mstarWarServices: StarWarServices
    private val adapter = StarShipsAdapter(R.layout.custom_starship_list, this)
    val starshipList = ArrayList<com.starwars.flow.detail.starships.model.Result>()
    var pageNumber = 1
    override fun init(mview: IView, starWarServices: StarWarServices) {
        mView = mview
        mstarWarServices = starWarServices
        starshipList.clear()
        loadStarShipList()
    }

    fun loadStarShipList(){
        if(pageNumber == 1)
            mView.showProgressBar()
        mstarWarServices.getStarShips(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    mView.hideProgressBar()
                },
                onNext = {
                    mView.hideProgressBar()
                    starshipList.addAll(it.results)
                    adapter.setStarShips(starshipList)
                }
            )
    }

    fun getStarShipDetails(position: Int): com.starwars.flow.detail.starships.model.Result {
        return starshipList[position]
    }

    fun getStarShipImage(position: Int):String{
        val url = starshipList[position].url.substring(0,starshipList[position].url.length-1)
        return url.replace("https://swapi.co/api","")
    }

    fun getAdapter(): StarShipsAdapter? {
        return adapter
    }

}