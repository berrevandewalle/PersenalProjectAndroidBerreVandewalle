package com.example.app.ui.components.bike


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.app.R
import com.example.app.ui.theme.BikeAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBike(
    bikeName: String,
    bikePrice: Double,
    bikeImgSrc: String,
    onBikeNameChanged: (String) -> Unit,
    onBikePriceChanged: (Double) -> Unit,
    onBikeImgSrcChanged: (String) -> Unit,
    onBikeSaved: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            shape = RoundedCornerShape(dimensionResource(R.dimen.cardCornerRadius)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.largePadding)),
            ) {
                OutlinedTextField(
                    value = bikeName,
                    onValueChange = onBikeNameChanged,
                    label = { Text("bike name") },
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.smallSpacer)))
                OutlinedTextField(
                    value = bikePrice.toString(),
                    onValueChange = {
                        // Probeer de ingevoerde waarde naar een Double om te zetten
                        val newValue = it.toDoubleOrNull() ?: 0.0
                        // Geef de nieuwe waarde door aan onBikePriceChanged
                        onBikePriceChanged(newValue)
                    },
                    label = { Text("price bike") },
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.mediumSpacer)))
                OutlinedTextField(
                    value = bikeImgSrc,
                    onValueChange = onBikeImgSrcChanged,
                    label = { Text("bike img src") },
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.mediumSpacer)))
                Row {
                    Spacer(Modifier.weight(1F))
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.smallSpacer)))
                    TextButton(onClick = onBikeSaved) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateTaskPreview() {
    BikeAppTheme {
        CreateBike("todo", 0.0,"src", {}, {}, {},{}, { /* on dismiss */ })
    }
}