package com.example.aplikasipendaftaran.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    // Interface untuk mengirim tanggal yang dipilih ke activity pemanggil
    interface OnDateSelectedListener {
        fun onDateSelected(year: Int, month: Int, day: Int)
    }

    // Listener yang akan dipanggil saat tanggal dipilih
    private var onDateSelectedListener: OnDateSelectedListener? = null

    // Fungsi untuk menetapkan listener
    fun setOnDateSelectedListener(listener: OnDateSelectedListener) {
        onDateSelectedListener = listener
    }

    // Metode yang dipanggil saat dialog tanggal dibuat
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Gunakan tanggal saat ini sebagai tanggal awal di dialog
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Buat dan kembalikan DatePickerDialog
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Teruskan tanggal yang dipilih ke listener jika ada
        onDateSelectedListener?.onDateSelected(year, month, dayOfMonth)
    }
}

