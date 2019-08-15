package com.starwars.flow.detail.planets.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.starwars.R
import com.starwars.databinding.FragmentPlanetBinding
import com.starwars.flow.detail.planets.viewmodel.PlanetViewModel
import com.starwars.services.StarWarServices
import com.starwars.shared.base.BaseFragment
import com.starwars.shared.viewmodel.StarWarViewModelFactory

class PlanetFragment : BaseFragment(),IView{
    private lateinit var mBinding: FragmentPlanetBinding
    private val mViewModel: PlanetViewModel
            by lazy {
                ViewModelProviders.of(this, StarWarViewModelFactory)
                    .get(PlanetViewModel::class.java)
            }


    companion object {
        const val TAG = "PLANET_FRAGMENT"
        fun newInstance() = PlanetFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planet,
            container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.init(this, StarWarServices.DashboardServicesCreator.newService(Gson()))
        mBinding.planet = mViewModel

        mBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mViewModel.getAdapter()!!.itemCount - 1) {
                    if (mViewModel.getAdapter()!!.itemCount > 4) {
                        mViewModel.pageNumber++
                        mViewModel.loadPlanetsList()
                    }
                }
            }
        })
    }
}