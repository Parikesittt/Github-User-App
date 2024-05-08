package com.example.submissionawalmdl3

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionawalmdl3.databinding.FragmentFollowtabBinding

class FragmentFollowing: Fragment(R.layout.fragment_followtab) {
    private var _binding: FragmentFollowtabBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: ViewModelFollowing
    private lateinit var adapter: MainAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding =FragmentFollowtabBinding.bind(view)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        binding?.apply {
            rvFollow.setHasFixedSize(true)
            rvFollow.layoutManager = LinearLayoutManager(activity)
            rvFollow.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ViewModelFollowing::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null){
                adapter.setListUser(it)
                showLoading(false)
            }
        })
    }
    private fun showLoading(state: Boolean){
        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}