package ie.setu.donationx.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.data.api.RetrofitRepository
import ie.setu.donationx.data.room.RoomRepository
import ie.setu.donationx.firebase.services.AuthService
import ie.setu.donationx.firebase.services.FirestoreService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService,
            savedStateHandle: SavedStateHandle
) : ViewModel() {

    var review = mutableStateOf(TravelModel())
    val id: String = checkNotNull(savedStateHandle["id"])
    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)
    init {
        viewModelScope.launch {
            try {
                isLoading.value = true
                review.value = repository.get(authService.email!!,id)!!
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
    }

    fun updateReview(review: TravelModel) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.update(authService.email!!,review)
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
    }
}