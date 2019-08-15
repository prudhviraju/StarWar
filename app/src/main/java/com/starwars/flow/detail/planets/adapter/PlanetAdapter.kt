package com.starwars.flow.detail.planets.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.starwars.BR
import com.starwars.flow.detail.planets.viewmodel.PlanetViewModel

class PlanetAdapter(@param:LayoutRes private val layoutId: Int,
                    private val viewModel: PlanetViewModel
)
    : RecyclerView.Adapter<PlanetAdapter.ListViewHolder>() {

    private var planetList: ArrayList<com.starwars.flow.detail.planets.model.Result> = ArrayList()

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
        return planetList.size
    }

    fun setPlanets(planetList: ArrayList<com.starwars.flow.detail.planets.model.Result>) {
        this.planetList.clear()
        this.planetList.addAll(planetList)
        notifyDataSetChanged()
    }


    class ListViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: PlanetViewModel, position: Int?) {
            try {
                binding.setVariable(BR.planet, viewModel)
                binding.setVariable(BR.position, position)
                binding.executePendingBindings()
            } catch (e: Exception) {
                Log.d("...Error...", e.localizedMessage)
            }
        }
    }


}