package ie.setu.donationx.firebase.database

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import ie.setu.donationx.data.rules.Constants.REVIEW_COLLECTION
import ie.setu.donationx.data.rules.Constants.USER_EMAIL
import ie.setu.donationx.firebase.services.AuthService
import ie.setu.donationx.firebase.services.FirestoreService
import ie.setu.donationx.firebase.services.Reviews
import ie.setu.donationx.firebase.services.Review

import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.Date
import javax.inject.Inject


class FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore
) : FirestoreService {

    override suspend fun getAll(email: String): Reviews {

        return firestore.collection(REVIEW_COLLECTION)

            .whereEqualTo(USER_EMAIL, email)
            .dataObjects()
    }

    override suspend fun get(email: String,
                             reviewId: String): Review? {
        return firestore.collection(REVIEW_COLLECTION)
                .document(reviewId).get().await().toObject()
    }

    override suspend fun insert(email: String,
                                review: Review)
    {        val reviewWithEmailAndImage =
        review.copy(
            email = email,
            imageUri = auth.customPhotoUri!!.toString()
        )

        firestore.collection(REVIEW_COLLECTION)
                .add(reviewWithEmailAndImage)
                .await()

    }

    override suspend fun update(email: String,
                                review: Review) {
        val reviewWithModifiedDate =
            review.copy(dateModified = Date())

        firestore.collection(REVIEW_COLLECTION)
            .document(review._id)
            .set(reviewWithModifiedDate).await()
    }

    override suspend fun delete(email: String,
                                reviewId: String) {
            firestore.collection(REVIEW_COLLECTION)
                .document(reviewId)
                .delete().await()
    }
    override suspend fun updatePhotoUris(email: String, uri: Uri) {

        firestore.collection(REVIEW_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Timber.i("FSR Updating ID ${document.id}")
                    firestore.collection(REVIEW_COLLECTION)
                        .document(document.id)
                        .update("imageUri", uri.toString())
                }
            }
            .addOnFailureListener { exception ->
                Timber.i("Error $exception")
            }
    }

}