package com.example.app.data

import com.example.app.model.Clothes

object ClothesSampler {

    private val sampleClothes = listOf(
        Clothes(1, "Wielershirt mannen 2023 - The Vélodrome", 75.0,
            "Het Vélodrome-wielershirt is het limited edition-shirt dat onze renners en rensters dragen tijdens de Tour de France van 2023. Ons nieuwe shirt staat in het teken van het waarmaken van een droom dat afgebeeld staat in een sterrenbeeld op het shirt. ",
            "https://dvy7d3tlxdpkf.cloudfront.net/team-jumbo/_1755xAUTO_resize_center-center_85_none/1984393/Tourshirt-mannen-1.webp"),
        Clothes(
            2, "Windbody mannen 2023 - The Vélodrome", 65.0,
            "De Vélodrome-windbody is in hetzelfde design ontworpen als het limited edition-shirt van de Tour de France van 2023. Ons nieuwe shirt staat in het teken van het waarmaken van een droom dat afgebeeld staat in een sterrenbeeld op het shirt.",
            "https://dvy7d3tlxdpkf.cloudfront.net/team-jumbo/_1755xAUTO_resize_center-center_85_none/1984424/wind-1.webp"
        ),
        Clothes(
            3, "Bibshort mannen 2023 - Team Jumbo-Visma", 85.0,
            "‘Tijdens een lange etappe zit ik soms wel vijf uur op de fiets. Ik laat mijn benen het werk doen, die blijven maar malen. Dat kan alleen als ik fijn op mijn fiets zit. Een broek met goede zeem is dan ook heel belangrijk.’ - Koen Bouwman",
           " https://dvy7d3tlxdpkf.cloudfront.net/team-jumbo/_1755xAUTO_resize_center-center_85_none/1636454/49035901_main_01.webp"
        ),
        Clothes(
            4,"Kampioenstrui België - Wout van Aert", 80.0,
            "Wout van Aert veroverde de Belgische tijdrit-kampioenstrui voor de 3e keer door de concurrentie op 50 seconden achter zich te laten op een kletsnat parcours in Herzele. Wout van Aert zal in deze kampioenstrui rijden in de tijdrit van de Tour de France.",
            "https://dvy7d3tlxdpkf.cloudfront.net/team-jumbo/_1755xAUTO_resize_center-center_85_none/2008254/Wout1-15.webp"
        ),
        Clothes(
            5, "Koerspet 2023 - The Vélodrome", 17.0,
            "Maak je Tour de France-wieleroutfit compleet met de koerspet in het design van The Vélodrome!",
            "https://dvy7d3tlxdpkf.cloudfront.net/team-jumbo/_1755xAUTO_resize_center-center_85_none/1984438/koerspet-2.webp"
        )
    )


    val getAll: () -> MutableList<Clothes> = {
        val list = mutableListOf<Clothes>()
        for (clothing in sampleClothes) {
            list.add(clothing)
        }
        list
    }


}