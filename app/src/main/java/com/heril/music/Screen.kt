package com.heril.music

import androidx.annotation.DrawableRes

sealed class Screen(val title: String,val route:String) {

    sealed class BottomBarScreen(val bTitle: String,val bRoute: String,@DrawableRes val icon: Int)
        :Screen(bTitle,bRoute){
        object Home : BottomBarScreen(
            "Home",
            "home",
            R.drawable.ic_home
        )
        object Browse : BottomBarScreen(
            "Browse",
            "browse",
            R.drawable.ic_browse
        )
        object Library : BottomBarScreen(
            "Library",
            "library",
            R.drawable.ic_library
        )
    }

    sealed class DrawerScreen(val dTitle: String,val dRoute: String,@DrawableRes val icon: Int)
        :Screen(dTitle,dRoute) {
        object Account : DrawerScreen(
            "Account",
            "account",
            R.drawable.ic_account
        )

        object Subscription : DrawerScreen(
            "Subscription",
            "subscription",
            R.drawable.ic_subscribe
        )

        object AddAccount : DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )
    }
}

val screensInBottomBar = listOf(
    Screen.BottomBarScreen.Home,
    Screen.BottomBarScreen.Browse,
    Screen.BottomBarScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)