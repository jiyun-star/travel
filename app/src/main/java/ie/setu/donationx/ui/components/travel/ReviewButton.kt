package ie.setu.donationx.ui.components.travel

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.donationx.R
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.ui.components.general.ShowLoader
import ie.setu.donationx.ui.screens.donate.ReviewViewModel
import ie.setu.donationx.ui.screens.review.ListViewModel

@Composable
fun ReviewButton(
    modifier: Modifier = Modifier,
    review: TravelModel,
    reviewViewModel: ReviewViewModel = hiltViewModel(),
    listViewModel: ListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isError = reviewViewModel.isErr.value
    val error = reviewViewModel.error.value

    Row {
        Button(
            onClick = {
                Toast.makeText(
                    context,
                    context.getString(R.string.donateTitle), // fix
                    Toast.LENGTH_SHORT
                ).show()
            },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Review")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.donateButton),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
    if(isError)
        Toast.makeText(context,"Unable to review at this Time...",
            Toast.LENGTH_SHORT).show()
    //else
    //    ListViewModel.getReviews()
}