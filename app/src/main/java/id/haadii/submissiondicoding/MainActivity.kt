package id.haadii.submissiondicoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import id.haadii.submissiondicoding.about.AboutFragment
import id.haadii.submissiondicoding.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_drawer.*
import kotlinx.android.synthetic.main.nav_header_drawer.view.*
import kotlinx.android.synthetic.main.nav_header_drawer.view.textViewName

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        setupDrawerLayout()

        displaySelectedFragment(HomeFragment.newInstance())
        title = "Home"

    }

    private fun setupDrawerLayout() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val navViewFooter = findViewById<NavigationView>(R.id.nav_view_footer)

        val userName = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val photo = intent.getStringExtra("photo")

        navView.getHeaderView(0).apply {
            this.textViewName.text = userName
            this.textViewEmail.text = email
            Glide.with(this)
                .load(photo)
                .into(this.imageView)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navViewFooter.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when(menu.itemId) {
            R.id.home -> {
                toolbar.title = "Home"
                val homeFragment = HomeFragment.newInstance()
                displaySelectedFragment(homeFragment)
            }
            R.id.about -> {
                toolbar.title = "About"
                val aboutFragment = AboutFragment.newInstance()
                displaySelectedFragment(aboutFragment)
            }
            R.id.nav_logout -> {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        finish()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
            }
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun displaySelectedFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contentFrame, fragment)
        fragmentTransaction.commit()
    }

}
