package ie.setu.donationx.ui.components.review

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.data.fakeReviews
import ie.setu.donationx.ui.components.report.ReviewCard
import ie.setu.donationx.ui.theme.DonationXTheme
import java.text.DateFormat

@Composable
internal fun ReviewCardList(
    reviews: List<TravelModel>,
    modifier: Modifier = Modifier,
    onDeleteReview: (TravelModel) -> Unit,
    onClickReviewDetails: (Int) -> Unit,
) {
    LazyColumn {
        items(
            items = reviews,
            key = { review -> review.id }
        ) { review ->
            ReviewCard(
                location = review.location,  // 위치 정보 추가
                rating = review.rating,  // 평점 정보 추가
                review = review.review,  // 리뷰 내용
                dateReviewed = DateFormat.getDateTimeInstance().format(review.dateReviewed),
                onClickDelete = { onDeleteReview(review) },
                onClickReviewDetails = { onClickReviewDetails(review.id) }
            )
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Composable
fun ReviewCardListPreview() {
    DonationXTheme {
        ReviewCardList(
            reviews = fakeReviews.toMutableStateList(), // 올바른 더미 데이터 사용
            onDeleteReview = {},
            onClickReviewDetails = {},
        )
    }
}
