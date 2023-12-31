package com.example.app.ui.clothesOverview

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.app.BikeApplication
import com.example.app.data.BikesRepository
import com.example.app.data.ClothesRepository
import com.example.app.model.Bike
import com.example.app.model.Clothes
import com.example.app.ui.bikeOverview.BikeApiState
import com.example.app.ui.bikeOverview.BikeListState
import com.example.app.ui.bikeOverview.BikeOverviewState
import com.example.app.ui.bikeOverview.WorkerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.NumberFormat

class ClothesOverviewViewModel(private val clothesRepository: ClothesRepository) : ViewModel()  {
    /*

    * Note: uiState is a cold flow. Changes don't come in from above unless a
    * refresh is called...
    * */
    private val _uiState = MutableStateFlow(ClothesOverviewState(/*BikeSampler.getAll()*/))
    val uiState: StateFlow<ClothesOverviewState> = _uiState.asStateFlow()

    lateinit var uiListState: StateFlow<ClothesListState>

    var clothesApiState: ClothesApiState by mutableStateOf(ClothesApiState.Loading)
        private set

    lateinit var wifiWorkerState: StateFlow<WorkerState>

    init {
        // initializes the uiListState
        getRepoClothes()
        Log.i("vm inspection", "ClothesOverviewViewModel init")
    }

    fun addClothes() {
        // saving the new bike (to db? to network? --> doesn't matter)
        viewModelScope.launch { saveClothes(
            Clothes(_uiState.value.newClothesId, _uiState.value.newClothesName,
            _uiState.value.newClothesPrice, uiState.value.newClothesDescription, _uiState.value.newClothesImgSrc)
        ) }

        // reset the input fields
        _uiState.update {
                currentState ->
            currentState.copy(
                /*currentBikesList = currentState.currentBikeList +
                    Bike(currentState.newBikeName, currentState.newBikeDescription),*/
                // clean up previous values
                newClothesId = 0,
                newClothesName = "",
                newClothesPrice = 0.0,
                newClothesDescription = "",
                newClothesImgSrc = "",
                // whenever this changes, scrollToItemIndex should be scrolled into view
                scrollActionIdx = currentState.scrollActionIdx.plus(1),
                scrollToItemIndex = uiListState.value.clothesList.size,
            )
        }
    }

    fun Bike.formatedPrice(): String {
        return NumberFormat.getCurrencyInstance().format(price)
    }

    private fun validateInput(): Boolean {
        return with(_uiState) {
            value.newClothesId > 0 && value.newClothesName.length > 0 && value.newClothesPrice > 0
        }
    }

    fun setNewClothesId(newClothesId: Int) {
        _uiState.update {
            it.copy(newClothesId = newClothesId)
        }
    }
    fun setNewClothesName(newClothesName: String) {
        _uiState.update {
            it.copy(newClothesName = newClothesName)
        }
    }

    fun setNewClothesPrice(newClothesPrice: Double) {
        _uiState.update {
            it.copy(newClothesPrice = newClothesPrice)
        }
    }

    fun setNewClothesDescription(newClothesDescription: String){
        _uiState.update {
            it.copy(newClothesDescription = newClothesDescription)
        }
    }

    fun setNewClothesImgSrc(newClothesImgSrc: String) {
        _uiState.update {
            it.copy(newClothesImgSrc = newClothesImgSrc)
        }
    }

    private fun getRepoClothes() {
        try {
            viewModelScope.launch { clothesRepository.refresh() }

            uiListState = clothesRepository.getClothes().map { ClothesListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = ClothesListState(),
                )
            clothesApiState = ClothesApiState.Success

            wifiWorkerState = clothesRepository.wifiWorkInfo.map { WorkerState(it) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = WorkerState(),
            )
        } catch (e: IOException) {
            // show a toast? save a log on firebase? ...
            // set the error state
            clothesApiState = ClothesApiState.Error
        }
    }

    private suspend fun saveClothes(clothing: Clothes) {
        if (validateInput()) {
            clothesRepository.insertClothing(clothing)
        }
    }

    fun onVisibilityChanged() {
        _uiState.update {
            it.copy(isAddingVisible = !_uiState.value.isAddingVisible)
        }
    }

    // object to tell the android framework how to handle the parameter of the viewmodel
    companion object {
        private var Instance: ClothesOverviewViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BikeApplication)
                    val clothesRepository = application.container.clothesRepository
                    Instance = ClothesOverviewViewModel(clothesRepository = clothesRepository)
                }
                Instance!!
            }
        }
    }
}