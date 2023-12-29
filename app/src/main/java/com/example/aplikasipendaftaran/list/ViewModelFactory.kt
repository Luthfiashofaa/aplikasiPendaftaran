package com.example.aplikasipendaftaran.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasipendaftaran.data.SiswaRepository
import com.example.aplikasipendaftaran.ui.add.datapribadi.AddDataPribadi
import com.example.aplikasipendaftaran.ui.add.datapribadi.AddDataPribadiViewModel

class ViewModelFactory private constructor(private val siswaRepository: SiswaRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    SiswaRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AddDataPribadi::class.java) -> {
                AddDataPribadiViewModel(siswaRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}