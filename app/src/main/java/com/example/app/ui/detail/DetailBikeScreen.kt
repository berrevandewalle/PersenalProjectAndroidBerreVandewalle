package com.example.app.ui.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.app.R
import com.example.app.model.Bike
import com.example.app.ui.navigation.BikeOverviewScreen
import kotlinx.coroutines.flow.firstOrNull
import java.text.NumberFormat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope

/**
 * Detail bike screen
 *
 * @param name
 * @param modifier
 * @param navController
 * @param viewModel
 */
@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun DetailBikeScreen(
    name: String,
    modifier: Modifier = Modifier,
    navController: NavHostController,
  //  navigateBack: () -> Unit,
    viewModel: BikeDetailsViewModel = viewModel(factory = BikeDetailsViewModel.factory)
) {
    val detailBike: Bike? = runBlocking {
        viewModel.getBike(name).firstOrNull()
    }

    Card(
        modifier = modifier.verticalScroll(state = rememberScrollState()),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Log.d("detail", detailBike.toString())
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                if (detailBike != null) {
                    AsyncImage(

                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(detailBike.imgSrc)
                            .build(),
                        contentDescription = stringResource(R.string.mars_photo),
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }

            // Text Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f) // Take available space
                )
                if (detailBike != null) {
                    Text(
                        text = NumberFormat.getCurrencyInstance().format(detailBike.price),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Bike description",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                if (detailBike != null) {
                    Text(
                        text = detailBike.description,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }

        }
        Button(
                onClick =  {
                    viewModel.viewModelScope.launch {
                        Log.d("delete", "delete bike")
                        viewModel.deleteBike(detailBike)
                        navController.popBackStack(
                            BikeOverviewScreen.Start.name,
                            inclusive = false,
                        )
                    }
                },
        modifier = modifier
            .fillMaxWidth(),
        ) {
        Text(text = "delete", fontSize = 20.sp)
    }
    }


}


