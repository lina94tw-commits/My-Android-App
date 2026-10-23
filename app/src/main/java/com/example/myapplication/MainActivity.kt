package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    lateinit var optionMenu:Menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        val adaptor = ViewPagerAdaptor(supportFragmentManager, this.lifecycle)
        viewPager2.adapter = adaptor

        viewPager2.offscreenPageLimit = 1

        val tb=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(tb)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        optionMenu=menu!!
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            /*R.id.item1-> {
                findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.pic1)
                optionMenu.getItem(1).isChecked=false
            }*/
            R.id.item1-> {
                item.setChecked(!item.isChecked)
                if(item.isChecked)
                    findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.pic1)
                else
                    findViewById<ImageView>(R.id.imageView).setImageBitmap(null)
            }

            R.id.item2-> {
                item.setChecked(!item.isChecked)
                if(item.isChecked)
                    findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.pic2)
                else
                    findViewById<ImageView>(R.id.imageView).setImageBitmap(null)
            }
            R.id.item3-> {
                item.setChecked(!item.isChecked)
                if(item.isChecked)
                    findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.pic3)
                else
                    findViewById<ImageView>(R.id.imageView).setImageBitmap(null)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}