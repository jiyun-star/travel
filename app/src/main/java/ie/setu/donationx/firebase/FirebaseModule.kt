package ie.setu.donationx.firebase

import android.app.Application
import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
//import com.google.android.gms.auth.api.identity.SignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ie.setu.donationx.R
import ie.setu.donationx.firebase.auth.AuthRepository
import ie.setu.donationx.firebase.database.FirestoreRepository
import ie.setu.donationx.firebase.services.AuthService
import ie.setu.donationx.firebase.services.FirestoreService
import ie.setu.donationx.firebase.services.StorageService
import ie.setu.donationx.firebase.storage.StorageRepository

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideFirebaseFirestore()
            : FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirestoreRepository(
        auth: AuthService,
        firebaseFirestore: FirebaseFirestore
    ) : FirestoreService = FirestoreRepository(
        auth = auth,
        firestore = firebaseFirestore
    )

//    @Provides
//    fun provideGoogleSignInOptions(
//        app: Application
//    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(app.getString(R.string.web_client_id))
//        .requestEmail()
//        .build()

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        storage: StorageService
    ): AuthService = AuthRepository(
        firebaseAuth = auth,
        storageService = storage)

    @Provides
    fun provideCredentialManager(
        @ApplicationContext
        context: Context
    ) = CredentialManager.create(context)

    @Provides
    fun provideGoogleIdOptions(
        app: Application
    ) = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(app.getString(R.string.web_client_id))
        .build()

    @Provides
    fun getCredentialRequest(
        googleIdOption: GetGoogleIdOption
    ) = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    @Provides
    fun provideFirebaseStorage() : FirebaseStorage = Firebase.storage

    @Provides
    fun provideStorageRepository(
        firebaseStorage: FirebaseStorage
    ) : StorageService = StorageRepository(
        storage = firebaseStorage)

}
