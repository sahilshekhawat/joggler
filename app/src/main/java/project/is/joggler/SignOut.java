package project.is.joggler;

import android.util.Log;

import com.google.android.gms.plus.PlusClient;

/**
 * Created by sahil on 1/14/15.
 */
public class SignOut {
    PlusClient mPlusClient;

    public SignOut(PlusClient mPlusClient){
        this.mPlusClient = mPlusClient;
    }

    public void signOut(){
        PlusClient mPlusClient = PlusBaseActivity.mPlusClient;
        if (mPlusClient.isConnected()) {
            // Clear the default account in order to allow the user to potentially choose a
            // different account from the account chooser.
            mPlusClient.clearDefaultAccount();
            //disconnect from the play services.
            mPlusClient.disconnect();
            Log.v("TAG", "Sign out successful!");
        }
    }
}
