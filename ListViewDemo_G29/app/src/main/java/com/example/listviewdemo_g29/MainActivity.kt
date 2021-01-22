package com.example.listviewdemo_g29

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var mainListView: ListView
    private val techList = arrayOf<String>(
            "Google",
            "Facebook",
            "Signal",
            "Instagram",
            "Amazon",
            "TikTok",
            "Apple",

            "Google",
            "Facebook",
            "Signal",
            "Instagram",
            "Amazon",
            "TikTok",
            "Apple",

            "Google",
            "Facebook",
            "Signal",
            "Instagram",
            "Amazon",
            "TikTok",
            "Apple",
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainListView = findViewById(R.id.appListView)

        // supportActionBar?.hide()

//        val color = Color.parseColor("#4b7bec");
//        mainListView.setBackgroundColor(color)

        mainListView.adapter = TechAdapter(this, techList)

        mainListView.setOnItemClickListener { _, _, position, id ->
            Log.i("TECH_CLICKED", techList[position])
            Toast.makeText(this, "${techList[position]} - Position:$position", Toast.LENGTH_SHORT).show()
        }

    }

}



private class TechAdapter(val context: Context, val dataSource: Array<String> ): BaseAdapter() {
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return  dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        lateinit var listViewLayout: View

        if( convertView == null ) {
            val layoutInflater = LayoutInflater.from(context)
            listViewLayout = layoutInflater.inflate(R.layout.main_listview_layout, parent, false)

            Log.i("FINDING", "SEARCHING");

            val techNameView = listViewLayout.findViewById<TextView>(R.id.techNameView)
            val techPosition = listViewLayout.findViewById<TextView>(R.id.techPosition)

            val viewHolder = ViewHolder(techNameView, techPosition);

            listViewLayout.tag = viewHolder;
        }
        else {
            listViewLayout = convertView
        }

        val viewHolder = listViewLayout.tag as ViewHolder;

        viewHolder.techNameView.text = dataSource[position];
        viewHolder.techPosition.text = "Position - $position"


        return listViewLayout

//        val textView = TextView(context)
//        textView.text = dataSource[position]
//        val color = Color.parseColor("#4b7bec")
//        textView.setTextColor(color)
//
//        return  textView
    }
}

private class ViewHolder(var techNameView: TextView, var techPosition: TextView)

