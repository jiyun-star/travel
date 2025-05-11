package ie.setu.donationx.firebase.services

import ie.setu.donationx.data.TravelModel
import kotlinx.coroutines.flow.Flow

typealias Review = TravelModel
typealias Reviews = Flow<List<Review>>

interface FirestoreService {

    suspend fun getAll(email: String) : Reviews
    suspend fun get(email: String, reviewId: String) : Review?
    suspend fun insert(email: String, review: Review)
    suspend fun update(email: String, review: Review)
    suspend fun delete(email: String, reviewId: String)
}