package com.example.aplikasipendaftaran.ui.add.datapribadi

import androidx.lifecycle.ViewModel
import com.example.aplikasipendaftaran.data.SiswaRepository
import com.example.aplikasipendaftaran.data.dataSiswa

class AddDataPribadiViewModel (private val habitRepository: SiswaRepository) : ViewModel() {
    fun saveSiswa(siswa1: dataSiswa) {
        habitRepository.insertHabit(siswa1)
    }
}