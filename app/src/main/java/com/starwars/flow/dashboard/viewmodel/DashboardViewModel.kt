package com.starwars.flow.dashboard.viewmodel

import com.starwars.R
import com.starwars.flow.dashboard.adapter.DashboardAdapter
import com.starwars.flow.dashboard.view.IView
import com.starwars.shared.base.BaseViewModel

class DashboardViewModel : BaseViewModel(),IViewModel{

    lateinit var mView: IView
    private val adapter = DashboardAdapter(R.layout.custom_dashboard_list, this)
    val dashboardList = ArrayList<String>()
    override fun init(view: IView) {
        mView = view
        loadDefaultTypes()
    }

    private fun loadDefaultTypes(){
        val list: List<String> = listOf( "CHARACTER","FILMS","SPECIES","STARSHIPS","VEHICLES","PLANETS")
        dashboardList.addAll(list)
        adapter.setDashboard(dashboardList)
    }

    fun getTypesList(position: Int): String {
        return dashboardList[position]
    }

    fun getAdapter(): DashboardAdapter? {
        return adapter
    }

    override fun handleItemClick(position: Int) {
        mView.navigateToDetailScreen(dashboardList[position])
    }

}