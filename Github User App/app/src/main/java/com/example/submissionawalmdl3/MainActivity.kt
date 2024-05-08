package com.example.submissionawalmdl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionawalmdl3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainViewModel
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MainAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCalback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ItemsItem) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATARURL, data.avatarUrl)
                    startActivity(it)
                }
            }
        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter
            searchhView.setupWithSearchBar(searchBar)
            searchhView
                .editText
                .setOnEditorActionListener { v, actionId, event ->
                    searchhView.hide()
                    if ((actionId == EditorInfo.IME_ACTION_SEARCH) || (event.action == KeyEvent.ACTION_DOWN)){
                        searchUser()
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
        }
        viewModel.getUsers().observe(this, {
            if (it != null){
                adapter.setListUser(it)
                showLoading(false)
            }
        })
        initData()
    }
    private fun searchUser(){
        binding.apply {
            val query = searchhView.text.toString()
            if(query.isEmpty())return
            showLoading(true)
            viewModel.setUsers(query)
        }
    }
    private fun showLoading(state: Boolean){
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
    private fun initData(){
        CoroutineScope(Dispatchers.IO).launch {
            val listShowUsers = ApiConfig.getApiService().showUsers()
            CoroutineScope(Dispatchers.Main).launch {
                adapter.setListUser(listShowUsers)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favMenu -> {
                Intent(this, FavoriteUserActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.switchTheme -> {
                Intent(this, SwitchTheme::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}