package com.example.app.ui.bikeOverview

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
import com.example.app.model.Bike
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.NumberFormat

/**
 * Bike overview view model
 *
 * @property bikesRepository
 * @constructor Create empty Bike overview view model
 */
class BikeOverviewViewModel(private val bikesRepository: BikesRepository) : ViewModel()  {
    /*

    * Note: uiState is a cold flow. Changes don't come in from above unless a
    * refresh is called...
    * */
    private val _uiState = MutableStateFlow(BikeOverviewState(/*BikeSampler.getAll()*/))
    val uiState: StateFlow<BikeOverviewState> = _uiState.asStateFlow()

    lateinit var uiListState: StateFlow<BikeListState>

    var bikeApiState: BikeApiState by mutableStateOf(BikeApiState.Loading)
        private set

    lateinit var wifiWorkerState: StateFlow<WorkerState>

    init {
        // initializes the uiListState
        getRepoBikes()
        Log.i("vm inspection", "BikeOverviewViewModel init")
    }

    /**
     * Add bike
     *
     */
    fun addBike() {
        // saving the new bike (to db? to network? --> doesn't matter)
        Log.d("ViewModel", "Adding bike: ${_uiState.value.newBikeId}, ${_uiState.value.newBikeName}, ${_uiState.value.newBikePrice}, " +
                "${_uiState.value.newBikeImgSrc}, ${_uiState.value.newBikeDescription}")

        viewModelScope.launch { saveBike(Bike(_uiState.value.newBikeId, _uiState.value.newBikeName, _uiState.value.newBikePrice, _uiState.value.newBikeImgSrc,
            _uiState.value.newBikeDescription))
            Log.d("ViewModel", "Bike added successfully")}


        // reset the input fields
        _uiState.update {
                currentState ->
            currentState.copy(
                /*currentBikesList = currentState.currentBikeList +
                    Bike(currentState.newBikeName, currentState.newBikeDescription),*/
                // clean up previous values
                newBikeId = 0,
                newBikeName = "",
                newBikePrice = 0.0,
                newBikeImgSrc = "",
                newBikeDescription = "",
                // whenever this changes, scrollToItemIndex should be scrolled into view
                scrollActionIdx = currentState.scrollActionIdx.plus(1),
                scrollToItemIndex = uiListState.value.bikeList.size,
            )
        }
    }

    /**
     * Formated price
     *
     * @return
     */
    fun Bike.formatedPrice(): String {
        return NumberFormat.getCurrencyInstance().format(price)
    }

    private fun validateInput(): Boolean {
        return with(_uiState) {
            value.newBikeId > 0 && value.newBikeName.isNotEmpty()
        }
    }

    /**
     * Set new bike id
     *
     * @param newBikeId
     */
    fun setNewBikeId(newBikeId: Int) {
        _uiState.update {
            it.copy(newBikeId = newBikeId)
        }
    }

    /**
     * Set new bike name
     *
     * @param newBikeName
     */
    fun setNewBikeName(newBikeName: String) {
        _uiState.update {
            it.copy(newBikeName = newBikeName)
        }
    }

    /**
     * Set new bike price
     *
     * @param newBikePrice
     */
    fun setNewBikePrice(newBikePrice: Double) {
        _uiState.update {
            it.copy(newBikePrice = newBikePrice)
        }
    }

    /**
     * Set new bike img src
     *
     * @param newBikeImgSrc
     */
    fun setNewBikeImgSrc(newBikeImgSrc: String) {
        _uiState.update {
            it.copy(newBikeImgSrc = newBikeImgSrc)
        }
    }

    /**
     * Set new bike description
     *
     * @param newBikeDescription
     */
    fun setNewBikeDescription(newBikeDescription: String) {
        _uiState.update {
            it.copy(newBikeDescription = newBikeDescription)
        }
    }

    private fun getRepoBikes() {
        try {
            viewModelScope.launch { bikesRepository.refresh() }

            uiListState = bikesRepository.getBikes().map { BikeListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = BikeListState(),
                )
            bikeApiState = BikeApiState.Success

            wifiWorkerState = bikesRepository.wifiWorkInfo.map { WorkerState(it)}.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = WorkerState(),
            )
        } catch (e: IOException) {
            // show a toast? save a log on firebase? ...
            // set the error state
            bikeApiState = BikeApiState.Error
        }
    }

    /**
     * Save bike
     *
     * @param bike
     */
    suspend fun saveBike(bike: Bike) {
        if (validateInput()) {
            Log.d("addBike", bike.name)
            withContext(Dispatchers.IO) {
                // This block will now run on the background thread
                bikesRepository.insertBike(bike)
            }
        }else {
            Log.d("saveBike", "Validation failed.")
        }
    }

    // object to tell the android framework how to handle the parameter of the viewmodel
    companion object {
        private var Instance: BikeOverviewViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BikeApplication)
                    val bikesRepository = application.container.bikesRepository
                    Instance = BikeOverviewViewModel(bikesRepository = bikesRepository)
                }
                Instance!!
            }
        }
    }
}