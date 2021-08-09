package com.example.carspree.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


data class Result(
    val bodyTypeId: String,
    val city: String,
    val depositReceived: Boolean,
    val gradeScore: Double,
    val hasFinancing: Boolean,
    val hasThreeDImage: Boolean,
    val hasWarranty: Boolean,
    val id: String,
    val imageUrl: String,
    val installment: Int,
    val loanValue: Float,
    val marketplaceOldPrice: Int,
    val marketplacePrice: Int,
    val mileage: Int,
    val mileageUnit: String,
    val sellingCondition: String,
    val sold: Boolean,
    val state: String,
    val stats: Stats,
    val title: String,
    val websiteUrl: String,
    val year: Int
)