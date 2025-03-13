package ie.setu.donationx.ui.screens.donate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.ui.screens.review.ListViewModel
import ie.setu.donationx.ui.theme.DonationXTheme

@Composable
fun ReviewScreen(modifier: Modifier = Modifier,
                 reviewViewModel: ReviewViewModel = hiltViewModel()
) {
    var location by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var review by remember { mutableStateOf("") }

    var isLocationError by remember { mutableStateOf(false) }
    var isRatingError by remember { mutableStateOf(false) }
    var isReviewError by remember { mutableStateOf(false) }

    fun validate(): Boolean {
        isLocationError = location.isBlank()
        isRatingError = rating.toIntOrNull() == null || rating.toInt() !in 1..5
        isReviewError = review.length < 2

        return !isLocationError && !isRatingError && !isReviewError
    }

    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Text("Review a Place", fontSize = 24.sp)

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            isError = isLocationError,
            trailingIcon = {
                if (isLocationError) Icon(Icons.Filled.Warning, contentDescription = "Error", tint = Color.Red)
            }
        )

        OutlinedTextField(
            value = rating,
            onValueChange = {
                val newRating = it.toIntOrNull()
                if (newRating != null && newRating in 1..5) {
                    rating = it //
                }
            },
            label = { Text("Rating (1-5)") },
            isError = isRatingError,
            trailingIcon = {
                if (isRatingError) Icon(Icons.Filled.Warning, contentDescription = "Error", tint = Color.Red)
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = review,
            onValueChange = { review = it },
            label = { Text("Review") },
            isError = isReviewError,
            maxLines = 3,
            trailingIcon = {
                if (isReviewError) Icon(Icons.Filled.Warning, contentDescription = "Error", tint = Color.Red)
            }
        )

        Button(
            onClick = {
                if (validate()) {
                    val newReview = TravelModel(
                        location = location,
                        rating = rating.toInt(),
                        review = review
                    )
                    reviewViewModel.insert(newReview)
                }

            },
            enabled = validate(),
        ) {
            Text("Save")
        }
    }
}
