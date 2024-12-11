package com.hanif.deteksiperson.data.repo

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hanif.detectionperson.data.Resource
import com.hanif.deteksiperson.data.model.LampuModel
import com.ptpws.agrogontafarm.data.models.BayiModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class LampuRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {

    private val _lampu = MutableStateFlow<List<LampuModel>>(emptyList()) // StateFlow holding a list of LampuModel
    val lampu: StateFlow<List<LampuModel>> get() = _lampu

    open suspend fun getBayi(): Resource<List<BayiModel>> {
        val db = FirebaseDatabase.getInstance(
            "https://deteksibayi-default-rtdb.asia-southeast1.firebasedatabase.app/"
        )
        val ref = db.reference.child("riwayat")
        return suspendCancellableCoroutine { continuation ->
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = mutableListOf<BayiModel>()

                    for (childSnapshot in snapshot.children){
                        val data = childSnapshot.getValue(BayiModel::class.java)
                        Log.d("polinema", "onDataChange: $data")
                        result.add(data!!)

                    }

                    if (result.isNotEmpty()) {
                        continuation.resume(Resource.Success(result))
                    } else {
                        continuation.resume(Resource.Error("Data tidak ditemukan atau kosong"))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(Resource.Error(error.message))
                }
            })
        }
    }

     fun getLampu() {
        firebaseDatabase.reference.child("riwayat").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<LampuModel>()
                for (dataSnapshot in snapshot.children) {
                    val data = dataSnapshot.getValue(LampuModel::class.java)
                    Log.d("polinema", "onDataChange: $data")
                    if (data != null) {
                        dataList.add(data)
                    }
                }

                // If dataList is not empty, emit it
                if (dataList.isNotEmpty()) {
                    GlobalScope.launch {
                        _lampu.emit(dataList)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors here
            }
        })
    }

    fun updateLampuStatus(room: String, lamp: String, status: String) {
        firebaseDatabase.reference.child("detectionPerson").child("lampu")
            .child(room).child(lamp).setValue(status)
    }



}
