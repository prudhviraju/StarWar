package com.starwars.flow.detail.film.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.starwars.BR
import com.starwars.flow.detail.film.model.Result
import com.starwars.flow.detail.film.viewmodel.FlimViewModel

class FilmAdapter(@param:LayoutRes private val layoutId: Int,
                  private val viewModel: FlimViewModel
)
    : RecyclerView.Adapter<FilmAdapter.ListViewHolder>() {

    private var filmsList: ArrayList<Result> = ArrayList()

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
        return filmsList.size
    }

    fun setFilms(characterList: ArrayList<Result>) {
        this.filmsList.clear()
        this.filmsList.addAll(characterList)
        notifyDataSetChanged()
    }


    class ListViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: FlimViewModel, position: Int?) {
            try {
                binding.setVariable(BR.film, viewModel)
                binding.setVariable(BR.position, position)
                binding.executePendingBindings()
            } catch (e: Exception) {
                Log.d("...Error...", e.localizedMessage)
            }
        }
    }


}