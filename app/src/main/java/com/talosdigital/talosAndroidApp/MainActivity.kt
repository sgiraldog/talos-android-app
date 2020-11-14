package com.talosdigital.talosAndroidApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN = 444

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null && account.email!!.isNotEmpty()) updateUI(account)

        sign_in_button.setSize(SignInButton.SIZE_WIDE)
        sign_in_button.setOnClickListener {

            val signInIntent: Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun updateUI(account: GoogleSignInAccount){

        if (account.email!!.isNotEmpty() && account.email!!.endsWith("@talosdigital.com")){
            val intent = Intent(this, EventsListActivity::class.java)
            startActivity(intent)
        }else{
            mGoogleSignInClient.signOut()
            Toast.makeText(this,"You need to login with a Talos Digital account to access this app.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            if (account != null) updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Login Activity: ", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(this,"There was an error signing in.", Toast.LENGTH_LONG).show()
        }
    }

}