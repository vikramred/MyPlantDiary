package com.appdevs.myplantdiary.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appdevs.myplantdiary.dto.Plant
import com.appdevs.myplantdiary.service.PlantService

class MainViewModel : ViewModel() {
    var plants: MutableLiveData<ArrayList<Plant>> = MutableLiveData<ArrayList<Plant>>()
    var plantService: PlantService = PlantService()

    fun fetchPlants(plantName: String) {
        plants = plantService.fetchPlants(plantName)

    }
    // TODO: Implement the ViewModel
}