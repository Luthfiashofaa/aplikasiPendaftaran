package com.example.aplikasipendaftaran.ui.add.datapribadi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasipendaftaran.R
import com.example.aplikasipendaftaran.data.dataSiswa
import com.example.aplikasipendaftaran.list.ViewModelFactory
import com.example.aplikasipendaftaran.utils.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddDataPribadi : AppCompatActivity(), DatePickerFragment.OnDateSelectedListener {

    private lateinit var viewModel: AddDataPribadiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adddatapribadi)

        supportActionBar?.title = getString(R.string.app_name)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(AddDataPribadiViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                try {
                    val nama = findViewById<EditText>(R.id.add_ed_nama).text.toString()
                    val tempatLahir = findViewById<EditText>(R.id.add_ed_tempatLahir).text.toString()
                    val tanggalLahir = findViewById<EditText>(R.id.add_ed_tanggalLahir).text.toString()
                    val agama = findViewById<EditText>(R.id.add_ed_agama).text.toString()
                    val alamat = findViewById<EditText>(R.id.add_ed_alamatRumah).text.toString()
                    val noHp = findViewById<EditText>(R.id.add_ed_noHP).text.toString()
                    val NISN = findViewById<EditText>(R.id.add_ed_NISN).text.toString()
                    

                    // Validasi title
                    if (nama.isEmpty() || tempatLahir.isEmpty() || tanggalLahir.isEmpty() ||agama.isEmpty() || alamat.isEmpty()
                        || noHp.isEmpty() || NISN.isEmpty()) {
                        throw IllegalArgumentException("Kolom tidak boleh kosong")
                    }

                    // Validasi minutesFocus

                    val siswa1 = dataSiswa(nama = nama, tempatLahir = tempatLahir, tanggalLahir = tanggalLahir, agama = agama, alamat = alamat, noHp = noHp, NISN = NISN)
                    viewModel.saveSiswa(siswa1)
                    finish()

                } catch (e: IllegalArgumentException) {
                    Toast.makeText(this, "Inputan Tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)


        }
    }

    fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDateSelected(year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        findViewById<TextView>(R.id.add_ed_tanggalLahir).text = dateFormat.format(calendar.time)
    }


}