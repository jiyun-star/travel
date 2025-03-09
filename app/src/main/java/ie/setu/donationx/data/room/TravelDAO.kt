package ie.setu.donationx.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ie.setu.donationx.data.TravelModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TravelDAO {
    @Query("SELECT * FROM TravelModel")
    fun getAll(): Flow<List<TravelModel>>

    @Query("SELECT * FROM travelmodel WHERE id=:id")
    fun get(id: Int): Flow<TravelModel>

    @Insert
    suspend fun insert(donation: TravelModel)

    @Query("UPDATE travelmodel SET review=:message WHERE id = :id")
    suspend fun update(id: Int, message:String)

    @Delete
    suspend fun delete(review: TravelModel)
}