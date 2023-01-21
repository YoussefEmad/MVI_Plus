package com.example.mviplus
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var numberTv: TextView
    lateinit var addNumberBtn: Button
    private val viewModel: AddNumberViewModel by lazy{
        ViewModelProvider(this)[AddNumberViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberTv = findViewById(R.id.number_textview)
        addNumberBtn = findViewById(R.id.Add_Number_Button)
        render()
        addNumberBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intentChannel.send(MainIntent.AddNumber)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun render() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                when(it){
                    is MainViewState.Idle -> numberTv.text= "Idle"
                    is MainViewState.Number-> numberTv.text = it.number.toString()
                    is MainViewState.Error -> numberTv.text = it.error

                }
            }
        }
    }
}