package com.starwars.flow.detail.film.viewmodel

import com.starwars.R
import com.starwars.flow.detail.film.adapter.FilmAdapter
import com.starwars.flow.detail.film.view.IView
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FlimViewModel : BaseViewModel(),IViewModel{
    private lateinit var mView: IView
    lateinit var mstarWarServices: StarWarServices
    private val adapter = FilmAdapter(R.layout.custom_film_list, this)
    val filmsList = ArrayList<com.starwars.flow.detail.film.model.Result>()
    override fun init(view: IView, starWarServices: StarWarServices) {
        mView = view
        mstarWarServices = starWarServices
        filmsList.clear()
        loadFilms()
    }

    fun loadFilms(){
        mView.showProgressBar()
        mstarWarServices.getFilms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    mView.hideProgressBar()
                },
                onNext = {
                    mView.hideProgressBar()
                    filmsList.addAll(it.results)
                    adapter.setFilms(filmsList)
                }
            )
    }

    fun getFilmDetails(position: Int): com.starwars.flow.detail.film.model.Result {
        return filmsList[position]
    }

    fun getAdapter(): FilmAdapter? {
        return adapter
    }
}