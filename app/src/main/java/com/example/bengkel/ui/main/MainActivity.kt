package com.example.bengkel.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.example.bengkel.R
import com.example.bengkel.databinding.ActivityMainBinding
import com.example.bengkel.ui.login.LoginActivity
import com.example.bengkel.ui.login.LoginViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigationrail.NavigationRailView
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: NavigationRailView = binding.navigationRail
        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.nav_dashboard ->{
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(
                        R.id.nav_dashboard
                    )
                    true
                }
                R.id.nav_admin ->{
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(
                        R.id.nav_admin
                    )
                    true
                }
                R.id.nav_pelanggan ->{
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(
                        R.id.nav_pelanggan
                    )
                    true
                }
                R.id.nav_service ->{
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(
                        R.id.nav_service
                    )
                    true
                }
                R.id.nav_cadang ->{
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(
                        R.id.nav_cadang
                    )
                    true
                }
                R.id.nav_history -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(
                        R.id.nav_history
                    )
                    true
                }

                else -> false
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout){
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("are you sure to logout?")
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("logout") { _, _ ->
                    viewModel.logout()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }.show()
        }


        return super.onOptionsItemSelected(item)
    }
}