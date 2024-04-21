package com.akdim.criminalintent

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    val crimes = mutableListOf<Crime>()
    suspend fun loadCrimes(): List<Crime> {
        return crimeRepository.getCrimes()
    }
}