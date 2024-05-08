package com.example.submissionawalmdl3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionawalmdl3.database.FavoriteUser
import com.example.submissionawalmdl3.databinding.ItemUserBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.UserViewHolder> () {

    private val list = ArrayList<ItemsItem>()


    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCalback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListUser(user: ArrayList<ItemsItem>){
        list.clear()
        list.addAll(user)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user: ItemsItem){
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .centerCrop()
                    .into(photoProfile)
                txtUsername.text = user.login
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: ItemsItem)
    }

}