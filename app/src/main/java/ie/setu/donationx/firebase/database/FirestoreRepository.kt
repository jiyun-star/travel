package ie.setu.donationx.firebase.database

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
import java.util.Date
import javax.inject.Inject


class FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore
) : FirestoreService {

    override suspend fun getAll(email: String): Reviews {

        return firestore.collection(REVIEW_COLLECTION)
//            .orderBy("dateModified",
//                if(sortAsending)
//                    Query.Direction.ASCENDING
//                else
//                    Query.Direction.DESCENDING)
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
    {
        val reviewWithEmail = review.copy(email = email)

        firestore.collection(REVIEW_COLLECTION)
                .add(reviewWithEmail)
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
}