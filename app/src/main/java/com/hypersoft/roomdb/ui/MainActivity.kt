package com.hypersoft.roomdb.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hypersoft.roomdb.R
import com.hypersoft.roomdb.adapters.AdapterCountry
import com.hypersoft.roomdb.databinding.ActivityMainBinding
import com.hypersoft.roomdb.db.tables.City
import com.hypersoft.roomdb.db.tables.CountryTable
import com.hypersoft.roomdb.di.DIComponent
import com.hypersoft.roomdb.interfaces.OnCountryItemClickListener
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapterCountry: AdapterCountry

    private val diComponent by lazy { DIComponent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbarMenu()
        initRecyclerView()
        initObservers()

        binding.fabAdd.setOnClickListener {
            diComponent.exampleViewModel.insertCountry(countryTableList[Random.nextInt(0, 23)])
        }
    }

    private fun initRecyclerView() {
        adapterCountry = AdapterCountry(object : OnCountryItemClickListener {
            override fun onCountryClick(countryTable: CountryTable) {
                Toast.makeText(this@MainActivity, "${countryTable.countryName} -> ${countryTable.city.cityName}", Toast.LENGTH_SHORT).show()
            }
        })
        binding.countryRecyclerView.adapter = adapterCountry

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                val paint = Paint()
                val icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView: View = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3
                    if (dX > 0) {
                        paint.color = Color.parseColor("#D32F2F")
                        val background = RectF(
                            itemView.left.toFloat(),
                            itemView.top.toFloat(),
                            dX,
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, paint)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.img_delete)
                        val iconDest = RectF(
                            itemView.left.toFloat() + width,
                            itemView.top.toFloat() + width,
                            itemView.left.toFloat() + 2 * width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, iconDest, paint)
                    } else {
                        paint.color = Color.parseColor("#388E3C")
                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, paint)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.img_delete)
                        val iconDest = RectF(
                            itemView.right.toFloat() - 2 * width,
                            itemView.top.toFloat() + width,
                            itemView.right.toFloat() - width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, iconDest, paint)
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
                    diComponent.exampleViewModel.deleteCountry(adapterCountry.currentList[viewHolder.adapterPosition])
                }
            }
        }).attachToRecyclerView(binding.countryRecyclerView)
    }

    private fun initToolbarMenu() {
        val menuHost = this as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_delete, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_item_delete -> diComponent.exampleViewModel.deleteAllCountry()
                }
                return true
            }
        }, this, Lifecycle.State.RESUMED)
    }

    private fun initObservers() {
        diComponent.exampleViewModel.allCountryList.observe(this) {
            adapterCountry.submitList(it)
            if (it.isEmpty()) {
                binding.mtvEmpty.visibility = View.VISIBLE
            } else {
                binding.mtvEmpty.visibility = View.GONE
            }
        }
    }


    val countryTableList = listOf(
        CountryTable(countryName = "Africa", countryCode = "01",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "China", countryCode = "02", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Finland", countryCode = "03",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "France", countryCode = "04",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "German", countryCode = "05",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "India", countryCode = "06",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Italy", countryCode = "07",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Japan", countryCode = "08",  city = City(cityName = "Tokyo", cityPostalCode = "51310")),
        CountryTable(countryName = "Malaysia", countryCode = "09",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Netherlands", countryCode = "10",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Pakistan", countryCode = "11",  city = City(cityName = "Islamabad", cityPostalCode = "51310")),
        CountryTable(countryName = "Poland", countryCode = "12",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Portugal", countryCode = "13",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Russia", countryCode = "14",  city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Saudi Arabia", countryCode = "15", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "South Korea", countryCode = "16", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Spain", countryCode = "17", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Sweden", countryCode = "18", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Thailand", countryCode = "19", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Turkey", countryCode = "20", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Ukraine", countryCode = "21", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "United Keyboard", countryCode = "22", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
        CountryTable(countryName = "Vietnam", countryCode = "23", city = City(cityName = "CapitalName", cityPostalCode = "51310")),
    )

}