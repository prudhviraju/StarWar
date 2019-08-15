package com.starwars.flow.detail.vehicles.viewmodel

import com.starwars.R
import com.starwars.flow.detail.starships.adapter.StarShipsAdapter
import com.starwars.flow.detail.vehicles.adapter.VehiclesAdapter
import com.starwars.flow.detail.vehicles.model.Result
import com.starwars.flow.detail.vehicles.view.IView
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class VehicleViewModel : BaseViewModel(),IViewModel{

    private lateinit var mView: IView
    lateinit var mstarWarServices: StarWarServices
    private val adapter = VehiclesAdapter(R.layout.custom_vehicles_list, this)
    val vehiclesList = ArrayList<Result>()
    var pageNumber = 1
    override fun init(mview: IView, starWarServices: StarWarServices) {
        mView = mview
        mstarWarServices = starWarServices
        vehiclesList.clear()
        loadVehiclesList()
    }

    fun loadVehiclesList(){
        if(pageNumber == 1)
            mView.showProgressBar()
        mstarWarServices.getVehicles(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    mView.hideProgressBar()
                },
                onNext = {
                    mView.hideProgressBar()
                    vehiclesList.addAll(it.results)
                    adapter.setVehicles(vehiclesList)
                }
            )
    }

    fun getVehiclesDetails(position: Int): Result {
        return vehiclesList[position]
    }

    fun getVehicleImage(position: Int):String{
        val url = vehiclesList[position].url.substring(0,vehiclesList[position].url.length-1)
        return url.replace("https://swapi.co/api","")
    }

    fun getAdapter(): VehiclesAdapter? {
        return adapter
    }

}