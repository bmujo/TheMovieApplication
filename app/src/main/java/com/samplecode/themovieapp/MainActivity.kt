package com.samplecode.themovieapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.samplecode.themovieapp.databinding.ActivityMainBinding
import com.samplecode.themovieapp.viewmodels.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerSettingsMain) as NavHostFragment?

        if (navHostFragment != null) {
            val navController = navHostFragment.navController

            NavigationUI.setupActionBarWithNavController(this, navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search, menu)

        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as? SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.queryHint = "Search..."

        val coroutineScope = lifecycle.coroutineScope
        var searchJob: Job? = null
        searchView?.setQuery(viewModel.queryStateLiveData.value, false)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    viewModel.search(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                try {
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        newText?.let {
                            delay(1000)
                            if(newText.length >= 3){
                                viewModel.search(newText)
                            }
                            else {
                                viewModel.search("")
                            }
                        }
                    }
                } catch (e: Exception) {

                }

                viewModel._queryStateMutableData.value = newText

                return true
            }
        })

        return true
    }

    override fun onSupportNavigateUp(): Boolean {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerSettingsMain) as NavHostFragment?

        if (navHostFragment != null) {
            val navController = navHostFragment.navController

            NavigationUI.setupActionBarWithNavController(this, navController)

            return navController.navigateUp()
        }
        return true
    }
}

