package com.example.aplikasipendaftaran.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SiswaRepository(private val siswaDao: SiswaDao, private val executor: ExecutorService) {

    companion object {
        const val PAGE_SIZE = 30
        const val PLACEHOLDERS = false

        @Volatile
        private var instance: SiswaRepository? = null

        fun getInstance(context: Context): SiswaRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = SiswaDatabase.getInstance(context)
                    instance = SiswaRepository(
                        database.SiswaDao(),
                        Executors.newSingleThreadExecutor()
                    )
                }
                return instance as SiswaRepository
            }

        }
    }

    //TODO 4 : Use SortUtils.getSortedQuery to create sortable query and build paged list (ok)

    //TODO 5 : Complete other function inside repository (ok)
    fun getSiswaById(siswaId: Int): LiveData<dataSiswa> {
        return siswaDao.getSiswaById(siswaId)
    }

    fun insertHabit(newSiswa : dataSiswa): Long {
        val siswaData = Callable { siswaDao.insertSiswa(newSiswa) }
        val executor = executor.submit(siswaData)
        return executor.get()
    }

    fun deleteHabit(siswa1: dataSiswa) {
        executor.execute {
            siswaDao.deleteSiswa(siswa1)
        }
    }
}