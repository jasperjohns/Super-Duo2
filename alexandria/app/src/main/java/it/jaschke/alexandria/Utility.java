package it.jaschke.alexandria;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by asaldanha on 9/3/2015.
 */
public class Utility {


/*
    static public boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm =
                (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
*/



    static public boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm =
                (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);

        ConnectivityManager connectivityManager=(ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=null;
        if (connectivityManager != null) {
            networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (!networkInfo.isAvailable()) {
                networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (!networkInfo.isAvailable()) {
                    // If down then display message
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
        else
        {

            return false;
        }
    }


}
