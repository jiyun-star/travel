package ie.setu.donationx.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date
import kotlin.random.Random

@Entity
data class TravelModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val _id: String = "N/A",
    @SerializedName("Location")
    val location: String = "N/A",
    @SerializedName("rating")
    val rating: Int = 5,
    var review: String = "tourist trap!",
    @SerializedName("dateReviewed")
    val dateReviewed: Date = Date()
)

val fakeReviews = List(30) { i ->
    TravelModel(
        id = 12345 + i,
        _id ="12345" +i,
        location = "spot $i",
        review= "good! $i"
    )
}
