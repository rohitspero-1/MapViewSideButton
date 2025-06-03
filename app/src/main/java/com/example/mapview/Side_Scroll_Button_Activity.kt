package com.example.mapview
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.slidetoact.SlideToActView

class Side_Scroll_Button_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_side_scroll_button)

        val slideView = findViewById<SlideToActView>(R.id.slide_view)
        slideView.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {

                val intent = Intent(this@Side_Scroll_Button_Activity,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@Side_Scroll_Button_Activity, "Action confirmed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}