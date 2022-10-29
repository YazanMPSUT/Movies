package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import com.example.movies.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etMovieName: EditText = findViewById(R.id.etMovieName)
        val btAdd: Button = findViewById(R.id.btAddToList)
        val btRem: Button = findViewById(R.id.btRemoveFromList)
        val spList: Spinner = findViewById(R.id.spList)
        val lvToWatch: ListView = findViewById(R.id.lvToWatch)
        val lvWatched: ListView = findViewById(R.id.lvWatched)
        val options = arrayOf("Yet To Watch", "Already Watched")
        var listChosen = "Already Watched"
        var toWatchArray = arrayOf<String>()
        var watchedArray = arrayOf<String>()

        spList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options);

        spList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listChosen = options.get(p2);
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        btAdd.setOnClickListener { view ->

            if (listChosen.equals("Yet To Watch"))
            {

                toWatchArray += etMovieName.text.toString().trim()
                toWatchArray = toWatchArray.distinct().toTypedArray()
                lvToWatch.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, toWatchArray);
            }
            else
            {
                watchedArray += etMovieName.text.toString().trim()
                watchedArray = watchedArray.distinct().toTypedArray()
                lvWatched.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, watchedArray);
            }
        }

        btRem.setOnClickListener { view ->

            var newArr: Array<String>? = null

            if (listChosen.equals("Yet To Watch"))
            {
               newArr = arrayOf<String>();

                for(i in toWatchArray.indices)
                {
                    if(toWatchArray[i] != etMovieName.text.toString().trim())
                        newArr += toWatchArray[i]
                }

                toWatchArray = newArr

                lvToWatch.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, toWatchArray);
            }
            else
            {
                 newArr = arrayOf<String>();

                for(i in watchedArray.indices)
                {
                    if(watchedArray[i] != etMovieName.text.toString().trim())
                        newArr += watchedArray[i]
                }

                watchedArray = newArr

                lvWatched.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, watchedArray);
            }
        }

    }
}