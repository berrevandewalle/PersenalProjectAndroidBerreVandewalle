package com.example.app.fake

import com.example.app.network.ApiBike


object FakeDataSource {
    const val nameOne = "feed dog"
    const val nameTwo = "feed cat"
    const val priceOne = 2333.0
    const val priceTwo = 4444.0
    const val src1 = "src1"
    const val src2 = "src2"
    const val descriptionOne = "food is in the cellar"
    const val descriptionTwo = "food is also in the cellar"

    val tasks = listOf(
        ApiBike(1,nameOne, priceOne, src1, descriptionOne),
        ApiBike(1, nameTwo, priceTwo, src2, descriptionTwo),
    )
}
