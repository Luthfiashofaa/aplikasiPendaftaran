package com.example.aplikasipendaftaran.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.aplikasipendaftaran.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.Executors


@Database([dataSiswa::class], version = 1, exportSchema = false)
abstract class SiswaDatabase : RoomDatabase(){
    abstract fun SiswaDao(): SiswaDao

    companion object {

        @Volatile
        private var INSTANCE: SiswaDatabase? = null

        fun getInstance(context: Context): SiswaDatabase {
//            throw NotImplementedError("Not yet implemented")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,

                    SiswaDatabase::class.java,
                    "siswa.db"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadScheduledExecutor().execute {
                                fillWithStartingData(context, getInstance(context).SiswaDao())
                            }
                        }
                    })
                    .build()

                INSTANCE = instance

                instance
            }
        }

        private fun fillWithStartingData(context: Context, dao: SiswaDao) {
            val jsonArray = loadJsonArray(context)
            try {
                if (jsonArray != null) {
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        dao.insertAll(
                            dataSiswa(
                                item.getInt("id"),
                                item.getString("nama"),
                                item.getString("tempatLahir"),
                                item.getString("tanggalLahir"),
                                item.getString("agama"),
                                item.getString("alamat"),
                                item.getString("noHp"),
                                item.getString("NISN")
                            )
                        )
                    }
                }
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
        }

        private fun loadJsonArray(context: Context): JSONArray? {
            val builder = StringBuilder()
            val `in` = context.resources.openRawResource(R.raw.siswa)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("habits")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }

    }
}