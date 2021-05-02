package com.nristekk.app.flickrsearchapi.main

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.nristekk.app.flickrsearchapi.R
import com.nristekk.app.flickrsearchapi.databinding.ActivityMainBinding
import com.nristekk.app.flickrsearchapi.history.HistoryFragment
import com.nristekk.app.flickrsearchapi.FlickrSearchApp


class MainActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private var isHomeAsUp = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inject this!
        (application as FlickrSearchApp).appComponent.mainComponent().create().inject(this)

        /*  UI elements Part  */
        //Set content views
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Prepare support action bar, ActionBar-Drawer Toggle and Drawyer Layout
        setSupportActionBar(viewDataBinding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, viewDataBinding.drawerLayout, viewDataBinding.toolbar, R.string.app_name, R.string.app_name)
        viewDataBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        viewDataBinding.menuHistory.setOnClickListener {
            transacHistoryFragmt()
        }
        setupToolbarNavigated()

        //transac Main Fragment
        transacMainFragmt()

    }


    /*
    * ------------Fragment and Data Part-------
    */

    //If user click keyword from HistoryFragment, this will be called throught HistoryViewModel's event
    fun keywordFromHistoryClicked(){
        transacMainFragmt()
    }

    fun transacMainFragmt(){
        supportFragmentManager?.commit {
            setReorderingAllowed(true)
            replace<MainFragment>(R.id.fragment_container)
        }
        setHomeAsUp(false)
    }

    fun transacHistoryFragmt(){
        supportFragmentManager?.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace<HistoryFragment>(R.id.fragment_container)
        }
        setHomeAsUp(true)
    }


    /*
    *  -----------UI functions PART----------
    */
    //crucial function which will be used for category the activities applied onto Navigation and Home Indicator
    private fun setupToolbarNavigated(){
        viewDataBinding.toolbar.setNavigationOnClickListener{v->
            if(viewDataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                //If the Drawer was Open set it close
                viewDataBinding.drawerLayout.closeDrawer(GravityCompat.START)
            }else if(isHomeAsUp){
                /* If the Home was set as up then apply BackPressed() and
                * set it to be false again
                */
                onBackPressed()
                setHomeAsUp(false)
            }else{
                //Apart of All -> open the Drawer
                viewDataBinding.drawerLayout.openDrawer(GravityCompat.START)
            }


        }
    }

    // Set home as 'up' or not
    private fun setHomeAsUp( isHomeAsUp:Boolean){
        if(this.isHomeAsUp != isHomeAsUp){
            this.isHomeAsUp = isHomeAsUp
        }
        val animator = if(isHomeAsUp) ValueAnimator.ofFloat(0f,1f) else ValueAnimator.ofFloat(1f,0f)
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
                val slideOffset = valueAnimator.animatedValue as? Float
                slideOffset?.let {
                    actionBarDrawerToggle.onDrawerSlide(viewDataBinding.drawerLayout, slideOffset)
                }
            }
        })
        animator.setInterpolator(DecelerateInterpolator())
        animator.setDuration(400)
        animator.start()
    }

    //hide/show history button
    fun hideMenuHistory(){
        viewDataBinding.menuHistory.visibility = View.INVISIBLE
    }

    fun showMenuHistory(){
        viewDataBinding.menuHistory.visibility = View.VISIBLE
    }



}