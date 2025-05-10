package ie.setu.donationx.data.api
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ie.setu.donationx.data.TravelModel

class RetrofitRepository @Inject
constructor(private val serviceApi: TravelService)  {

    suspend fun getAll(email: String): List<TravelModel>
    {
        return withContext(Dispatchers.IO) {
            val travels = serviceApi.getall(email)
            travels.body() ?: emptyList()
        }
    }

    suspend fun get(email: String, id: String): List<TravelModel>
    {
        return withContext(Dispatchers.IO) {
            val travel = serviceApi.get(email,id)
            travel.body() ?: emptyList()
        }
    }

    suspend fun insert(email: String,travel: TravelModel) : travelWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(email, travel)
            wrapper
        }
    }

    suspend fun update(email: String,travel: TravelModel) : travelWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(email, travel._id, travel)
            wrapper
        }
    }

    suspend fun delete(email: String,travel: TravelModel) : travelWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(email, travel._id)
            wrapper
        }
    }
}
