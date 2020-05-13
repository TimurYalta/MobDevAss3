package com.example.mobdevass3.presentation.infoView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdevass3.R
import com.example.mobdevass3.presentation.mainview.MainViewModel
import com.example.mobdevass3.utils.downloadImage
import kotlinx.android.synthetic.main.activity_info.*
import org.koin.android.ext.android.inject

class AnimalInfoActivity : AppCompatActivity(){
    private val mainVm: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        initData()
    }


    private fun initData(){
        val animal = mainVm.lastDetailedAnimal

        val imageUrl = animal.photos?.get(0)?.small
        animal_pic_full.downloadImage(imageUrl)

        name.text = "Name: " + animal.name
        age.text = "Age: " + animal.age
        gender.text ="Gender: " + animal.gender
        description.text = animal.description
        size.text ="Size: " + animal.size
        url.text ="Url: " + animal.url
    }
}