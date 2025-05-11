package ie.setu.donationx.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import com.google.gson.annotations.SerializedName
import java.util.Date


data class TravelModel(
    @DocumentId val _id: String = "N/A",

    val location: String = "N/A",
    val rating: Int = 5,
    var review: String = "tourist trap!",
    val dateReviewed: Date = Date(),
    val dateModified: Date = Date(),
    var email: String = "joe@bloggs.com",
    var imageUri: String = ""

)

val fakeReviews = List(30) { i ->
    TravelModel(
        _id ="12345" +i,
        location = "spot $i",
        review= "good! $i",

    )
}
