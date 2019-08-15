package com.starwars.flow.detail.starships.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.starwars.BR
import com.starwars.flow.detail.starships.viewmodel.StarViewModel

class StarShipsAdapter(@param:LayoutRes private val layoutId: Int,
                       private val viewModel: StarViewModel
)
    : RecyclerView.Adapter<StarShipsAdapter.ListViewHolder>() {

    private var starshipsList: ArrayList<com.starwars.flow.detail.starships.model.Result> = ArrayList()

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
        return starshipsList.size
    }

    fun setStarShips(starshipsList: ArrayList<com.starwars.flow.detail.starships.model.Result>) {
        this.starshipsList.clear()
        this.starshipsList.addAll(starshipsList)
        notifyDataSetChanged()
    }


    class ListViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: StarViewModel, position: Int?) {
            try {
                binding.setVariable(BR.starship, viewModel)
                binding.setVariable(BR.position, position)
                binding.executePendingBindings()
            } catch (e: Exception) {
                Log.d("...Error...", e.localizedMessage)
            }
        }
    }


}