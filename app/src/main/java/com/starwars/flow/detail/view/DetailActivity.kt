package com.starwars.flow.detail.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.starwars.R
import com.starwars.databinding.ActivityDetailBinding
import com.starwars.flow.detail.character.view.CharacterFragment
import com.starwars.flow.detail.film.view.FilmFragment
import com.starwars.flow.detail.planets.view.PlanetFragment
import com.starwars.flow.detail.species.view.SpeciesFragment
import com.starwars.flow.detail.starships.view.StarShipFragment
import com.starwars.flow.detail.vehicles.view.VehicleFragment
import com.starwars.flow.detail.viewmodel.DetailViewModel
import com.starwars.shared.base.BaseActivity
import com.starwars.shared.viewmodel.StarWarViewModelFactory

class DetailActivity : BaseActivity(),IView{

    lateinit var mBinding : ActivityDetailBinding
    private val mViewModel: DetailViewModel
            by lazy {
                ViewModelProviders.of(this, StarWarViewModelFactory)
                    .get(DetailViewModel::class.java)
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_detail, parentViewGroup, true)
        mBinding.detail = mViewModel
        mViewModel.init(this,intent.getStringExtra("TYPE"))

        if (savedInstanceState == null) {
            navigateToSelectedScreen(intent.getStringExtra("TYPE"))
        }

    }

    private fun navigateToSelectedScreen(type:String){
        when(type){
            "CHARACTER" -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, CharacterFragment.newInstance(), CharacterFragment.TAG)
                    .addToBackStack(CharacterFragment.TAG)
                    .commit()
            }
            "FILMS" -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, FilmFragment.newInstance(), FilmFragment.TAG)
                    .addToBackStack(FilmFragment.TAG)
                    .commit()
            }
            "VEHICLES" -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, VehicleFragment.newInstance(), VehicleFragment.TAG)
                    .addToBackStack(VehicleFragment.TAG)
                    .commit()
            }
            "SPECIES" -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, SpeciesFragment.newInstance(), SpeciesFragment.TAG)
                    .addToBackStack(SpeciesFragment.TAG)
                    .commit()
            }
            "STARSHIPS" -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, StarShipFragment.newInstance(), StarShipFragment.TAG)
                    .addToBackStack(StarShipFragment.TAG)
                    .commit()
            }
            "PLANETS" -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, PlanetFragment.newInstance(), PlanetFragment.TAG)
                    .addToBackStack(PlanetFragment.TAG)
                    .commit()
            }
        }

    }

    override fun onBackPressed() {
        val fragmentManager = this.supportFragmentManager
        val fragmentList = fragmentManager.fragments
        val var2 = fragmentList.iterator()

        while (var2.hasNext()) {
            val fragment = var2.next()
            if (fragment != null && fragment.isVisible) {
                val var5 = fragment.childFragmentManager
                if (var5.backStackEntryCount > 0) {
                    var5.popBackStack()
                    return
                }
            }
        }
        if (fragmentManager.backStackEntryCount === 1)
            finish()
        else
            super.onBackPressed()
    }

    override fun navigateToBack() {
        finish()
    }
}