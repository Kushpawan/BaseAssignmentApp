package com.example.baseassignmentapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import coil.load
import com.example.baseassignmentapp.R
import com.example.baseassignmentapp.models.Drinks
import kotlinx.android.synthetic.main.activity_detail2.*

class DetailActivity : AppCompatActivity() {

    private lateinit var drinks: Drinks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        if (intent.hasExtra("details_drink")) {
            intent.getParcelableExtra<Drinks>("details_drink")?.let {
                drinks = it
            }

            setDetails()
        }

    }

    private fun setDetails() {
        back_image.setOnClickListener {
            finish()
        }

        drink_image.load(drinks.strDrinkThumb)
        drink_type.text = drinks.strCategory
        drink_name.text = drinks.strDrink
        details.text = drinks.strInstructions

        table_view.isStretchAllColumns = true
        table_view.bringToFront()

        if (drinks.strIngredient1 != null && drinks.strMeasure1 != null)
            addRow(drinks.strIngredient1, drinks.strMeasure1)

        if (drinks.strIngredient2 != null && drinks.strMeasure2 != null)
            addRow(drinks.strIngredient2, drinks.strMeasure2)

        if (drinks.strIngredient3 != null && drinks.strMeasure3 != null)
            addRow(drinks.strIngredient3, drinks.strMeasure3)

        if (drinks.strIngredient4 != null && drinks.strMeasure4 != null)
            addRow(drinks.strIngredient4, drinks.strMeasure4)

        if (drinks.strIngredient5 != null && drinks.strMeasure5 != null)
            addRow(drinks.strIngredient2, drinks.strMeasure2)

        if (drinks.strIngredient6 != null && drinks.strMeasure6 != null)
            addRow(drinks.strIngredient3, drinks.strMeasure3)

        if (drinks.strIngredient7 != null && drinks.strMeasure7 != null)
            addRow(drinks.strIngredient7, drinks.strMeasure7)

        if (drinks.strIngredient8 != null && drinks.strMeasure8 != null)
            addRow(drinks.strIngredient8, drinks.strMeasure8)

        if (drinks.strIngredient9 != null && drinks.strMeasure9 != null)
            addRow(drinks.strIngredient9, drinks.strMeasure9)

        if (drinks.strIngredient10 != null && drinks.strMeasure10 != null)
            addRow(drinks.strIngredient10, drinks.strMeasure10)

    }

    fun addRow(str1: String?, str2: String?) {
        val tr = TableRow(this)
        val txtGeneric = TextView(this)
        txtGeneric.text = "$str1  - $str2"
        tr.addView(txtGeneric)
        /*txtGeneric.setHeight(30); txtGeneric.setWidth(50);   txtGeneric.setTextColor(Color.BLUE);*/
        table_view.addView(tr);
    }
}