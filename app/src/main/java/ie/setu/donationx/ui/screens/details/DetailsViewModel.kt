package ie.setu.donationx.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.data.room.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val repository: RoomRepository,
            savedStateHandle: SavedStateHandle
) : ViewModel() {

    var review = mutableStateOf(TravelModel())
    val id: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
            repository.get(id).collect { objReview: TravelModel ->
                review.value = objReview
            }
        }
    }

    fun updateReview(review: TravelModel) {
        viewModelScope.launch { repository.update(review) }
    }
}