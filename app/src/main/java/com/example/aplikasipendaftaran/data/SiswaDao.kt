package com.example.aplikasipendaftaran.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface SiswaDao{
    @RawQuery([dataSiswa::class])
    fun getSiswa(query: SupportSQLiteQuery): DataSource.Factory<Int, dataSiswa>

    @Query("SELECT * FROM siswa WHERE id = :siswaId")
    fun getSiswaById(siswaId: Int): LiveData<dataSiswa>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSiswa(habit: dataSiswa): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg siswa: dataSiswa)

    @Delete
    fun deleteSiswa(siswa: dataSiswa)

}