package com.mudryakov.taverna.activityes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.mudryakov.taverna.R
import com.mudryakov.taverna.databinding.ActivityMainBinding
import com.mudryakov.taverna.models.Users
import com.mudryakov.taverna.ui.Fragmets.ChatFragment
import com.mudryakov.taverna.ui.Objects.*
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    lateinit var myDrawer: AppDrawer
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFireBase()
        initUser {
            initFields()
            initFunc()
        }
        APP_ACTIVITY = this
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


    }


    private fun initFields() {

        mToolbar = mBinding.toolbarMain!!
        myDrawer = AppDrawer(this, mToolbar)

    }


    fun initFunc() {
        if (AUTH.currentUser == null)
            replaceActivity(RegisterActivity())
        else {
            setSupportActionBar(mToolbar)
            changeFragment(ChatFragment(), false)
            myDrawer.Create()

        }
    }


    override fun onStop() {
        super.onStop()
        appStatus.changeState(appStatus.OFFLINE)
    }

    override fun onStart() {
        super.onStart()
        appStatus.changeState(appStatus.ONLINE)
    }
}