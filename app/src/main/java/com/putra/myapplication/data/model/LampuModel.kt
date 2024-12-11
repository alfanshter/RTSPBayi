package com.hanif.deteksiperson.data.model

import com.google.gson.annotations.SerializedName
data class LampuModel(
	var created_at: String? = null,
	var event: String? = null
)

data class LampuDetail(
	var ruangan_a: Ruangan? = null,
	var ruangan_b: Ruangan? = null
)

data class Ruangan(
	var lampu1: String = "",
	var lampu2: String = "",
	var lampu3: String = ""
)

