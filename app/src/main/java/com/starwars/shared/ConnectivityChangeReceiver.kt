package com.starwars.shared

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.starwars.extensions.showAlertDialog

class ConnectivityChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val connMgr = context!!
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val allNetworks = connMgr.allNetworks
        var canReachInternet = false
        for (network in allNetworks) {
            if (connMgr.getNetworkCapabilities(network).hasCapability(NetworkCapabilities.TRANSPORT_CELLULAR)
                || connMgr.getNetworkCapabilities(network).hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                canReachInternet = true
                break
            }
        }
        if (!canReachInternet) {
            showAlertDialog(context, "Internet Connection Lost",
                "Please retry with a stable connection")
        }
    }
}