package ie.setu.donationx.ui.screens.donate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.data.api.RetrofitRepository
import ie.setu.donationx.data.room.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject
constructor(private val repository: RetrofitRepository) : ViewModel() {

    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)


    // fun insert(reviews: TravelModel)
   //         = viewModelScope.launch {
   //             repository.insert(reviews)
  //  }
   fun insert(reviews: TravelModel) =
       viewModelScope.launch {
           try {
               isLoading.value = true
               repository.insert(reviews)
               isErr.value = false
               isLoading.value = false
           } catch (e: Exception) {
               isErr.value = true
               error.value = e
               isLoading.value = false
           }
       }

}