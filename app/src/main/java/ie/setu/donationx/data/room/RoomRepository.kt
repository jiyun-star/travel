package ie.setu.donationx.data.room

import ie.setu.donationx.data.DonationModel
import ie.setu.donationx.data.TravelModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject
constructor(private val travelDAO: TravelDAO) {
    fun getAll(): Flow<List<TravelModel>>
            = travelDAO.getAll()

    fun get(id: Int) = travelDAO.get(id)

    suspend fun insert(review: TravelModel)
            { travelDAO.insert(review) }

    suspend fun update(review: TravelModel)
            { travelDAO.update(review.id,review.review) }

    suspend fun delete(review: TravelModel)
            { travelDAO.delete(review) }
}