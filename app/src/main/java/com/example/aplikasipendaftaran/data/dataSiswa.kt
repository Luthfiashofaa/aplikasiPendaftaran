package com.example.aplikasipendaftaran.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity("Siswa")
data class dataSiswa(
    @PrimaryKey(true)
    @ColumnInfo("id") @NotNull
    val id: Int = 0,

    @ColumnInfo("nama") @NotNull
    val nama: String,

    @ColumnInfo("tempatLahir") @NotNull
    val tempatLahir: String,

    @ColumnInfo("tanggalLahir") @NotNull
    val tanggalLahir: String,

    @ColumnInfo("agama") @NotNull
    val agama: String,

    @ColumnInfo("alamat") @NotNull
    val alamat: String,

    @ColumnInfo("noHp") @NotNull
    val noHp: String,

    @ColumnInfo("NISN") @NotNull
    val NISN: String
) : Parcelable