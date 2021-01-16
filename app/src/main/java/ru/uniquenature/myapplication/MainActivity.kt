package ru.uniquenature.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.uniquenature.myapplication.view.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.main_container, FragmentMoviesList())
            .addToBackStack("FragmentMoviesList").commit()
    }

}