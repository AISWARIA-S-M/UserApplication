package com.ibm.usersapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ibm.usersapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //navigation container setting graph bundle
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment

        val navController = navHostFragment.navController
        //val navController = findNavController(R.id.fragmentContainerView3)
        val bundle = Bundle()
        navController.setGraph(R.navigation.my_nav,bundle)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    //navigate up
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView3)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}