package com.example.baseassignmentapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    var drinks: MutableList<Drinks?>
) : Parcelable

@Parcelize
data class Drinks(
    var idDrink: String?,
    var strDrink: String?,
    var strTags: String?,
    var strCategory: String?,
    var strDrinkThumb: String?,
    var strInstructions: String?,
    var strIngredient1: String?,
    var strIngredient2: String?,
    var strIngredient3: String?,
    var strIngredient4: String?,
    var strIngredient5: String?,
    var strIngredient6: String?,
    var strMeasure1: String?,
    var strMeasure2: String?,
    var strMeasure3: String?,
    var strMeasure4: String?,
    var strMeasure5: String?,
    var strMeasure6: String?
) : Parcelable