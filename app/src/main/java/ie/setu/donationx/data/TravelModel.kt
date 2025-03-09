package ie.setu.donationx.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import kotlin.random.Random

@Entity
data class TravelModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val location: String = "N/A",
    val rating: Int = 5,
    var review: String = "tourist trap!",
    val dateReviewed: Date = Date()
)

val fakeDonations = List(30) { i ->
    TravelModel(
        id = 12345 + i,
        location = "spot $i",
        review= "good! $i"
    )
}
