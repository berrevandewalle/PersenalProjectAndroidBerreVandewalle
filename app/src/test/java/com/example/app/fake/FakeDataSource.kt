package com.example.app.fake

import com.example.app.model.Bike
import com.example.app.network.ApiBike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


object FakeDataSource {
    const val nameOne = "Giant TCR Advanced SL"
    const val nameTwo = "Orbea orca"
    const val priceOne = 2333.0
    const val priceTwo = 4444.0
    const val src1 = "https://images2.giant-bicycles.com/b_white,c_pad,h_850,q_80/slwxiv1x4bopvdhzvetd/MY23TCRAdvancedSL0Disc_ColorARawCarbon.jpg"
    const val src2 = "https://www.orbea.com/img/products/product/over/large/R123TTCC-AN-SIDE-ORCA_M11eLTD_PWR.jpg"
    const val descriptionOne = "Combining best-in-class stiffness-to-weight ratio with strategically placed aerodynamic tubes, this frame is designed for faster climbing, explosive acceleration and unyielding pedal stiffness for race-winning performance."
    const val descriptionTwo = "Orca isn’t just light. It is 6.7kg of climbing technology designed to change how you reach the top. "

    val bikes = listOf(
        ApiBike(11,nameOne, priceOne, src1, descriptionOne),
        ApiBike(12, nameTwo, priceTwo, src2, descriptionTwo),
    )

    fun getBikesFlow(): Flow<List<Bike>> {
        return flow {
            val bikeList = bikes.map { apiBike ->
                Bike(apiBike.id, apiBike.name, apiBike.price, apiBike.imgSrc, apiBike.description)
            }
            emit(bikeList)
        }
    }
}


