package com.starwars.flow.detail.character.viewmodel

import com.starwars.R
import com.starwars.flow.detail.character.adapter.CharacterAdapter
import com.starwars.flow.detail.character.model.Result
import com.starwars.flow.detail.character.view.IView
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterViewmodel : BaseViewModel(),IViewModel{

    private lateinit var mView:IView
    lateinit var mstarWarServices: StarWarServices
    private val adapter = CharacterAdapter(R.layout.custom_character_list, this)
    val characterList = ArrayList<Result>()
    var pageNumber = 1
    override fun init(view: IView, starWarServices: StarWarServices) {
        mView = view
        mstarWarServices = starWarServices
        characterList.clear()
        loadCharacters()
    }

    fun loadCharacters(){
        if(pageNumber == 1)
            mView.showProgressBar()
        mstarWarServices.getCharacters(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    mView.hideProgressBar()
                },
                onNext = {
                    mView.hideProgressBar()
                    characterList.addAll(it.results)
                    adapter.setCharacters(characterList)
                }
            )
    }

    fun getCharcterDetails(position: Int): Result {
        return characterList[position]
    }

    fun getAdapter(): CharacterAdapter? {
        return adapter
    }

}