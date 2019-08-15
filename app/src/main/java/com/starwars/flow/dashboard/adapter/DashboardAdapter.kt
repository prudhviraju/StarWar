package com.starwars.flow.dashboard.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.starwars.BR
import com.starwars.flow.dashboard.viewmodel.DashboardViewModel

class DashboardAdapter(@param:LayoutRes private val layoutId: Int,
                       private val viewModel: DashboardViewModel
)
    : RecyclerView.Adapter<DashboardAdapter.ListViewHolder>() {

    private var dashboardList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
            viewType, parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId
    }

    override fun getItemCount(): Int {
        return dashboardList.size
    }

    fun setDashboard(typesList: ArrayList<String>) {
        dashboardList.clear()
        dashboardList.addAll(typesList)
        notifyDataSetChanged()
    }


    class ListViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: DashboardViewModel, position: Int?) {
            try {
                binding.setVariable(BR.dashboard, viewModel)
                binding.setVariable(BR.position, position)
                binding.executePendingBindings()
            } catch (e: Exception) {
                Log.d("...Error...", e.localizedMessage)
            }
        }
    }


}