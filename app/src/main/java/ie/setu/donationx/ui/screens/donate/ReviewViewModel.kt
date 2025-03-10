package ie.setu.donationx.ui.screens.donate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.donationx.data.TravelModel
import ie.setu.donationx.data.room.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {

    fun insert(reviews: TravelModel)
            = viewModelScope.launch {
                repository.insert(reviews)
    }
}