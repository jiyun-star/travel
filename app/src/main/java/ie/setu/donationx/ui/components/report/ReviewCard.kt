package ie.setu.donationx.ui.components.report

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.donationx.R
import ie.setu.donationx.ui.theme.DonationXTheme
import ie.setu.donationx.ui.theme.endGradientColor
import ie.setu.donationx.ui.theme.startGradientColor
import java.text.DateFormat
import java.util.Date

@Composable
fun ReviewCard(
    location: String,
    rating: Int,
    review: String,
    dateReviewed: String,
    onClickDelete: () -> Unit,
    onClickReviewDetails: () -> Unit,
    onRefreshList: () -> Unit,

    ) {
    Card(
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
    ) {
        TravelCardContent(
            location,
            rating,
            review,
            dateReviewed,
            onClickDelete,
            onClickReviewDetails,
            onRefreshList
        )
    }
}

@Composable
private fun TravelCardContent(
    location: String,
    rating: Int,
    review: String,
    dateReviewed: String,
    onClickDelete: () -> Unit,
    onClickReviewDetails: () -> Unit,
    onRefreshList: () -> Unit,

    ) {
    var expanded by remember { mutableStateOf(false) }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(2.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        startGradientColor,
                        endGradientColor,
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = location,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "⭐ $rating",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            Text(
                text = "Reviewed on $dateReviewed", style = MaterialTheme.typography.labelSmall
            )
            if (expanded) {
                Text(modifier = Modifier.padding(vertical = 16.dp), text = review)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FilledTonalButton(onClick = onClickReviewDetails) {
                        Text(text = "Show More")
                    }
                    FilledTonalIconButton(onClick = {
                        showDeleteConfirmDialog = true
                    }) {
                        Icon(Icons.Filled.Delete, "Delete Review")
                    }
                    if (showDeleteConfirmDialog) {
                        showDeleteAlert(
                            onDismiss = { showDeleteConfirmDialog = false },
                            onDelete = onClickDelete,
                            onRefresh = onRefreshList
                        )
                    }
                }
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Composable
fun showDeleteAlert(
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    onRefresh: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_message)) },
        confirmButton = {
            Button(
                onClick = {
                    onDelete()
                    onRefresh()
                }
            ) { Text("Yes") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("No") }
        }
    )
}

@Preview
@Composable
fun TravelReviewCardPreview() {
    DonationXTheme {
        ReviewCard (
            location = "seoul, korea",
            rating = 4,
            review = "ok",
            dateReviewed = DateFormat.getDateTimeInstance().format(Date()),
            onClickDelete = { },
            onClickReviewDetails = {},
            onRefreshList ={}
        )
    }
}