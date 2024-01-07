package com.example.app.data


import com.example.app.model.Bike

object BikeSampler {
    private val sampleBikes = listOf(Bike(1, "Cervelo S5", 12344.0, "https://www.lease-a-bike.be/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/cervelo-s5-black.png",
        "The S5 has reduced drag by 65 grams of aerodynamic drag " +
                "reduced and increased the surface area of the frame, while the overall " +
                "weight has been brought down. The enlarged frame sections maximize the " +
                "shapes allowed by the UCI. Meanwhile, the iconic " +
                "v-stem has been further refined and a new fork designed to make the entire front end " +
                "simplify. In addition, a groundbreaking new " +
                "wheel design concept was developed to increase stability and " +
                "reduce drag in turbulent aerodynamic " +
                "conditions. "),
        Bike(2, "S-Works Tarmac SL8", 14000.0, "https://assets.specialized.com/i/specialized/94924-02_TARMAC-SL8-SW-ETAP-FOGTNT-GRNGSTPRL-REDGSTPRL_HERO",
            "After a decade of working in the Win Tunnel, our engineers put aero where it matters, not just where it looks good. The result? A new Nose Cone called the ‘Speed Sniffer’ helps the new Tarmac SL8 be 16.6 seconds faster over 40km than the Tarmac SL7. And yes, more aero than the Venge.\n" +
                    "We saw how the lightest production road bike in the world - the Aethos -  could marry blistering response in the mountains with a supple, all-day ride. The Tarmac SL8 took those learnings from Aethos to be easily built at the UCI minimum with a full aero set up.\n" +
                    "The Tarmac SL8 delivers a massive 33% improvement in stiffness to weight vs the SL7, all while being 6% smoother in the saddle. With World Championship proven geometry and 32mm tire clearance, it adds up to an unprecedented ride quality."),
        Bike(3, "Trek Madone SLR 6", 300.0, "https://media.trekbikes.com/image/upload/f_auto,fl_progressive:semi,q_auto,w_1920,h_1440,c_pad/MadoneSLR7_23_37025_A_Primary",
            "The Madone SLR 6 is a racing machine that builds speed from the moment it sees asphalt. " +
                    "It all starts with an 800 Series OCLV Carbon frame with exclusive IsoFlow technology that provides increased comfort, increased aerodynamics and reduced weight. " +
                    "Completing the package are solid components such as a Shimano 105 Di2 12-speed wireless electronic shifting system that keeps working flawlessly under the toughest conditions. " +
                    "It also has useful upgrades such as carbon wheels with high rims for even more aerodynamic gains."),
        Bike(4, "Ridley FALCN RS", 6999.0,
            "https://images.cyclingfactory.be/craft/featureditems/1x-43a581d2360aa6e6e83fa8de9f48c31801fa9f6d2f6d846a9bc9bca7ff6f054d.png",
            "The Falcn RS is the ultimate road bike for any rider who wants to push their limits and take their performance to the next level. It is made for those who demand top quality and want a reliable and efficient speed machine to achieve their goals. With its aero and lightweight carbon frame and responsive handling, the Falcn RS is ready for all your competitive cycling goals. This versatile road bike can handle any road race, whether it's a grueling mountain stage or a classic, fast road race. It is the perfect tool for the spring classics, known for their tough and challenging courses on bad roads.\n" +
                    "With a frame suitable for tires up to 34 mm and its responsive handling, the Falcn RS is ready for any challenge you give it.\n" +
                    "The lightweight 830-gram frame helps you conquer the highest mountains, and the exceptional aerodynamics of the frame and fork turn every watt into pure speed when you're in the breakaway."),
        Bike(5, "Factor Ostro VAM", 8599.0,
            "https://images.ctfassets.net/uyc32o2uod42/7mLNS27c9f797nY2WfpAQn/532d97d61ba8bcac23ca1de660631315/6_pdp_hero_Ostro_chrome_ds.webp",
            "The OSTRO VAM is an aero disc-brake frame which weighs just 830g (painted, size 54, Chrome design). We have used the very best materials to reach such a low weight, laying up the ultimate carbon materials perfectly to ensure low weight, responsive ride, and durability. It can easily be built up to below the UCI 6.8kg weight limit, which is unique among aero frames. That means that the aero advantages come without the usual weight penalty. Few rides or race routes are exclusively mountainous or flat roads so, naturally, you want it all: speed and lightweight. The OSTRO is the everything bike, making you fast whatever the terrain."),
        Bike(
            6, "Cube Litening Air", 7299.0,
            "https://multicycle.de/wp-content/uploads/2022/11/679700_F1_00.png",
            "As aero as possible, as light as possible and fully UCI-compliant: that's the Litening AIR Race. Built around our brand-new ultra-light carbon frame, we started with Shimano's Ultegra Di2 components and a compact chainset. Newmen Advanced wheels shod with Continental Grand Prix tyres and super-light Schwalbe Aerothan tubes get your power down to the road. And the light, comfortable full carbon fork complements the exceptional performance of the  carbon frame. It's ready to race. Are you?"
        ),
        Bike(
            7   , "Canyon Aeroad CFR MVDP", 9999.0,
            "https://hips.hearstapps.com/hmg-prod/images/full-2022-aeroad-cfr-disc-mvdp-3055-p08-p5-64621a00b37de.png",
            "A special highlight of this exclusive Mathieu van der Poel design – a three-dimensional MVDP logo on the front of the head tube. Plus with an exclusive MVDP artwork, this bike's sure to catch the eye.\n" +
                    "With complete cable and wire integration, get super-clean optics and reduced drag at the same time. And with single-tool adjustable height and width, big spacer stacks and time-consuming bar swaps are a thing of the past.\n" +
                    "The Aeroad CFR is built around a frame we developed together with the aero experts at Swiss Side. Tested in the wind tunnel, it combines supreme efficiency with agile handling and innovative adjustability. This is a race bike that makes zero compromises.\n" +
                    "This full-carbon fork helps reduce the Aeroad’s frontal area to an absolute minimum, with complete cable integration reducing drag for even greater aerodynamic efficiency. Thanks to VCLS technology, the fork also absorbs vibrations and gives you excellent handling and durability even in the most extreme of situations."
        ),
        Bike(
                8   , "Pinarello DOGMA X", 16100.0,
        "https://pinarello.com/storage/Variant/cff22838c3f40059bf83fc0d70bf22e9.png",
            "Inspired by riders who enjoy long days in the saddle, the DOGMA X is about more than just performance; the bike has been engineered from the ground up to heighten the emotional experience of cycling and reshape how we think about endurance and speed.\n" +
                    "It will redefine how we think about performance, offering real-world comfort.\n" +
                    "High tensile and high modulus carbon fiber with patented Nanoalloy Technology for extreme performances.\n" +
                    "A brand new approach that strikes the perfect balance between high-calibre performance and real-world comfort.\n" +
                    "A new Pinarello technology capable of absorbing vibrations maintaining a light weight frame and a very reactive BB stiffness.\n" +
                    "For first Pinarello understood that, while the thrust applied to both pedals is the same, the pull on the chain applies to the right side only. For this reason, all frames are optimized to compensate these unbalanced forces.\n" +
                    "This bike has 35mm tires to unlock the frames’ true potential in terms of both comfort and speed."
    )
        )


    val getAll: () -> MutableList<Bike> = {
        val list = mutableListOf<Bike>()
        for (bike in sampleBikes) {
            list.add(bike)
        }
        list
    }



}