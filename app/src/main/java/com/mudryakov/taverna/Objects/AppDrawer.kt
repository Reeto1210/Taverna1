package com.mudryakov.taverna.Objects

import com.mudryakov.taverna.ui.Fragmets.contacts.ContactsFragment
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import com.mudryakov.taverna.R
import com.mudryakov.taverna.appDatabaseHelper.APP_ACTIVITY
import com.mudryakov.taverna.appDatabaseHelper.TOOLBAR
import com.mudryakov.taverna.appDatabaseHelper.USER
import com.mudryakov.taverna.ui.Fragmets.Settings.SettingsFragment


class AppDrawer{
    private lateinit var currentProfile: ProfileDrawerItem
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var appDrawerLayout: DrawerLayout
    fun Create() {
        initLoader()
        createHeader()
        createDrawer()
        appDrawerLayout = mDrawer.drawerLayout
    }

    fun enableDrawer() {
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        appDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        TOOLBAR.setNavigationOnClickListener{
            mDrawer.openDrawer()
        }
    }

    fun disableDrawer() {
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        TOOLBAR.setNavigationOnClickListener{
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    }

    private fun createDrawer() {

        mDrawer = DrawerBuilder()
            .withActivity(APP_ACTIVITY)
            .withToolbar(TOOLBAR)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Создать группу")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_create_groups),

                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Создать секретный чат")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_secret_chat),

                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Создать канал")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_create_channel),

                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Контакты")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_contacts),

                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Звонки")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_phone),

                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Избранное")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_favorites),

                PrimaryDrawerItem().withIdentifier(105)
                    .withIconTintingEnabled(true)
                    .withName("Настройки")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_settings),

                DividerDrawerItem(),

                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Пригласить друзей")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_invate),

                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Вопросы о таверне")
                    .withSelectable(selectable = false)
                    .withIcon(R.drawable.ic_menu_help)


            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                   onClickDrawer(position)
                    return false
                }
            }).build()
    }

    private fun onClickDrawer(position: Int) {
        when (position) {
            7 ->changeFragment(SettingsFragment())
            4 -> changeFragment(ContactsFragment())
                }
    }

    fun createHeader() {
        currentProfile = ProfileDrawerItem()
            .withName(setFullnameUi())
            .withEmail(USER.phoneNumber)
            .withIcon(USER.photoUrl)
            .withIdentifier(200)
        mHeader = AccountHeaderBuilder()
            .withActivity(APP_ACTIVITY)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                currentProfile
            ).build()
    }
fun updateProfile(){
    currentProfile
        .withName(setFullnameUi())
        .withEmail(USER.phoneNumber)
        .withIcon(USER.photoUrl)
mHeader.updateProfile(currentProfile)
}
private fun initLoader(){
    DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
        override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
            super.set(imageView, uri, placeholder)
           imageView.downloadAndSetImage(uri.toString())
        }
    })
}
}