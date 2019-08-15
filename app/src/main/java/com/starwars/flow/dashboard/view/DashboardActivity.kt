package com.starwars.flow.dashboard.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.starwars.R
import com.starwars.databinding.ActivityDashboardBinding
import com.starwars.flow.dashboard.viewmodel.DashboardViewModel
import com.starwars.flow.detail.view.DetailActivity
import com.starwars.shared.base.BaseActivity
import com.starwars.shared.viewmodel.StarWarViewModelFactory

class DashboardActivity : BaseActivity(),IView{
    lateinit var mBinding : ActivityDashboardBinding
    private val mViewModel: DashboardViewModel
            by lazy {
                ViewModelProviders.of(this, StarWarViewModelFactory).get(DashboardViewModel::class.java)
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_dashboard, parentViewGroup, true)
        mBinding.dashboard = mViewModel

        mViewModel.init(this)
    }

    override fun navigateToDetailScreen(type: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("TYPE",type)
        startActivity(intent)
    }

}