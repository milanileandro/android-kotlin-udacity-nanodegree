package com.milanileandro.shoestore

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val navController by lazy { findNavController(R.id.navHostFragment) }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.shoesFragment), drawerLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuItemLogout -> {
                navController.popBackStack()
                navController.navigate(R.id.loginFragment)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true;
    }

    private fun setupNavigation() {
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                R.id.shoesFragment -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    drawerToggleDelegate?.setActionBarUpIndicator(
                        ContextCompat.getDrawable(
                            this, R.drawable.ic_menu
                        ), R.string.menu
                    )
                }
            }
        }
    }
}