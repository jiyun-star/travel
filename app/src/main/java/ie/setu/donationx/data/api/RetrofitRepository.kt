package ie.setu.donationx.data.api
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ie.setu.donationx.data.TravelModel

class RetrofitRepository @Inject
constructor(private val serviceApi: TravelService)  {

    suspend fun getAll(): List<TravelModel>
    {
        return withContext(Dispatchers.IO) {
            val travels = serviceApi.getall()
            travels.body() ?: emptyList()
        }
    }

    suspend fun get(id: String): List<TravelModel>
    {
        return withContext(Dispatchers.IO) {
            val travel = serviceApi.get(id)
            travel.body() ?: emptyList()
        }
    }

    suspend fun insert(travel: TravelModel) : travelWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(travel)
            wrapper
        }
    }

    suspend fun update(travel: TravelModel) : travelWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(travel._id,travel)
            wrapper
        }
    }

    suspend fun delete(travel: TravelModel) : travelWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(travel._id)
            wrapper
        }
    }
}
