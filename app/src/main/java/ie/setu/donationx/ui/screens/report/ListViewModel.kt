package ie.setu.donationx.ui.screens.review

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.data.room.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {

    private val _reviews =
        MutableStateFlow<List<TravelModel>>(emptyList())
    val uiReviews: StateFlow<List<TravelModel>> =
        _reviews.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

 //   init {
  //      viewModelScope.launch {
    //        repository.getAll().collect { listOfReviews ->
   //             _reviews.value = listOfReviews
     //       }
    //    }
  //  }
 init { getReviews() }

    fun getReviews() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                _reviews.value = repository.getAll()
                isErr.value = false
                isLoading.value = false
            }
            catch(e:Exception) {
                isErr.value = true
                isLoading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }

    fun deleteReview(review: TravelModel) {
        viewModelScope.launch {
            repository.delete(review)
        }
    }

    fun addReview(newReview: TravelModel) {

    }
}
