package ie.setu.donationx.ui.screens.report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.donationx.R
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.data.fakeDonations
import ie.setu.donationx.ui.components.general.Centre
import ie.setu.donationx.ui.components.report.ReportText
import ie.setu.donationx.ui.theme.DonationXTheme


@Composable
fun ReviewScreen(modifier: Modifier = Modifier,
                 onClickReviewDetails: (Int) -> Unit,
                 reportViewModel: ReportViewModel = hiltViewModel()) {

    val reviews = reportViewModel.uiReviews.collectAsState().value

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            ReportText()
            if(reviews.isEmpty())
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_list)
                    )
                }
            else
                ReviewCardList(
                    reviews = reviews,
                    onClickReviewDetails = onClickReviewDetails,
                    onDeleteReview = {
                            reviews: TravelModel ->
                                reportViewModel.deleteReview(review)
                    }
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
    DonationXTheme {
        PreviewReportScreen( modifier = Modifier,
            donations = fakeDonations.toMutableStateList()
        )
    }
}

@Composable
fun PreviewReportScreen(modifier: Modifier = Modifier,
                        donations: SnapshotStateList<TravelModel>
) {

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            ReportText()
            if(reviews.isEmpty())
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_list)
                    )
                }
            else
                ReviewCardList(
                    reviews = reviews,
                    onDeleteReview = {},
                    onClickReviewnDetails = { }
                )
        }
    }
}