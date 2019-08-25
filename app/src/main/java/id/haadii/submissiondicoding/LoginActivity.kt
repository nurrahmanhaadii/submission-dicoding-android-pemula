package id.haadii.submissiondicoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                val userData = HashMap<String, Any>()
                userData["uid"] = user?.uid.toString()
                userData["email"] = user?.email.toString()
                userData["username"] = user?.displayName.toString()
                FirebaseFirestore.getInstance().collection("users").document(user?.uid.toString())
                    .set(userData)

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", user?.email.toString())
                intent.putExtra("username", user?.displayName.toString())
                intent.putExtra("photo", user?.photoUrl.toString())
                startActivity(intent)
            } else {

            }
        }

    }

    companion object {
        const val RC_SIGN_IN = 123
    }
}
