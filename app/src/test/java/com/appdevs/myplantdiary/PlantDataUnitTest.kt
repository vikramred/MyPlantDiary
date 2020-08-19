package com.appdevs.myplantdiary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.appdevs.myplantdiary.dto.Plant
import com.appdevs.myplantdiary.service.PlantService
import com.appdevs.myplantdiary.ui.main.MainViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlantDataUnitTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule( )
    lateinit var mvm : MainViewModel
    var plantService =  mockk<PlantService>()
    @Test
    fun confirmEasternRedbud_outputsEasternRedbud() {
        val plant: Plant =Plant("Cercis", "canadesis", "Eastern Redbud")
        assertEquals("Eastern Redbud", plant.toString())
    }

    @Test
    fun searchForRedbud_returnsRedbud(){
        givenAFeedOfMockedDataAreAvailable()
        whenSearchForRedbud()
        thenResultContainsEasternRedbud()

    }

    @Test
    fun searchForGarbage_returnsNothing(){
        givenAFeedOfMockedDataAreAvailable()
        whenISearchForGarbage()
        thenIGetZeroResults()
    }

    private fun whenISearchForGarbage() {
        mvm.fetchPlants("lkjljfdslsjdflj")

    }

    private fun thenIGetZeroResults() {
        mvm.plants.observeForever{
            assertEquals(0, it.size)
        }
    }

    private fun thenResultContainsEasternRedbud() {
        var redbudFound = false
        mvm.plants.observeForever{
        assertNotNull(it)
        assertTrue(it.size > 0)
        it.forEach{
            if(it.genus == "Cercis" && it.species == "canadensis" && it.common.contains("Eastern Redbud")){
                redbudFound = true
                }
            }
        }
        assertTrue(redbudFound)
    }

    private fun whenSearchForRedbud() {
        mvm.fetchPlants("Redbud")  }

    private fun givenAFeedOfMockedDataAreAvailable() {
        mvm = MainViewModel()
        createMockedData()
    }

    private fun createMockedData() {
        var allPlantsLiveData= MutableLiveData<ArrayList<Plant>>()
        var allPlants = ArrayList<Plant>()
        var redbud =    Plant("Cercis", "canadensis", "Eastern Redbud")
        allPlants.add(redbud)
        var redOak =    Plant("Quercus", "rubra", "Red Oak")
        allPlants.add(redbud)
        var whiteOak =    Plant("Quercus", "alba", "White Oak")
        allPlants.add(redbud)
        allPlantsLiveData.postValue(allPlants)
        every { plantService.fetchPlants(any<String>()) } returns allPlantsLiveData
        mvm.plantService = plantService
    }


}