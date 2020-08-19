package com.appdevs.myplantdiary.service

import androidx.lifecycle.MutableLiveData
import com.appdevs.myplantdiary.dto.Plant

class PlantService {
    fun fetchPlants(plantName:String): MutableLiveData<ArrayList<Plant>>{
        return MutableLiveData<ArrayList<Plant>>()
    }
}