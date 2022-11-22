package com.freeagent.testapp

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.freeagent.testapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val postItem: String? = intent.extras!!.getString("A_STRING")
        var arr = intent.extras!!.getParcelableArrayList<PostItem>("postItem")
        Toast.makeText(
            this,
            arr.toString(),
            Toast.LENGTH_LONG
        ).show()

        val r1: TableRow = findViewById(R.id.r1)
        val r2: TableRow = findViewById(R.id.r2)
        val r3: TableRow = findViewById(R.id.r3)
        val r4: TableRow = findViewById(R.id.r4)
        val r5: TableRow = findViewById(R.id.r5)
        if (arr != null) {
            for (item in arr) {
                println(item)
                val t1: TextView = TextView(this)
                t1.setText(item.name)
                r1.addView(t1)

                val t2: TextView = TextView(this)
                t2.setText("BBBB")
                r2.addView(t2)

                val t3: TextView = TextView(this)
                t3.setText("CCCC")
                r3.addView(t3)

                val t4: TextView = TextView(this)
                t4.setText("DDDD")
                r4.addView(t4)

                val t5: TextView = TextView(this)
                t5.setText("EEEE")
                r5.addView(t5)
            }

        }









//        tableLayout.addView(r1)
//        tableLayout.addView(r2)
//        tableLayout.addView(r3)
//        tableLayout.addView(r4)
//        tableLayout.addView(r5)



        //val postItem: PostItem? = intent.extras!!.getParcelable("postItem")
//        rootsetSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_details)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_details)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}