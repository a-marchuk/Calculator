package com.example.calculator

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }

        val editText: EditText = findViewById(R.id.editTextText)
        
        fun onButtonClick(view: View){
            val buttonText = (view as Button).text.toString()
            val editTextValue = editText.text.toString()
            editText.setText(editTextValue+buttonText)
        }

        fun onCastClick(view: View) {
            editText.setText("")
        }

        fun onDotClick(view: View){
            val buttonText = (view as Button).text.toString()
            val editTextValue = editText.text.toString()
            editText.setText(editTextValue+".")
        }

        fun calculate(string: String): Double {
            if (string.isNotEmpty()) {
                var numbers = mutableListOf<Double>()
                var operators = mutableListOf<String>()

                var currentNumber = StringBuilder()
                var isNegativeNumber = false
                var isBeforeProcent = false

                for (char in string) {
                    if (char == '-' && currentNumber.isEmpty()) {
                        if (isBeforeProcent){
                            isNegativeNumber=false
                        }else{ isNegativeNumber=true}
                    } else if (char.isDigit() || char == '.') {
                        currentNumber.append(char)
                    } else {
                        if (currentNumber.isNotEmpty()) {
                            val num = currentNumber.toString().toDouble()
                            if (isNegativeNumber) {
                                numbers.add(-num)
                                isNegativeNumber = false
                            } else {
                                numbers.add(num)
                            }
                            currentNumber.clear()
                        }
                        if (char.toString().trim() != "" && char.toString().trim() =="%"){
                            isBeforeProcent=true
                            operators.add(char.toString())
                        }else if (char.toString().trim() != "") {
                            operators.add(char.toString())
                        }
                    }
                }

                if (currentNumber.isNotEmpty()) {
                    val num = currentNumber.toString().toDouble()
                    if (isNegativeNumber) {
                        numbers.add(-num)
                    } else {
                        numbers.add(num)
                    }
                }

                var i = 0
                while (i < operators.size) {
                    if (operators[i] == "X" || operators[i] == "/") {
                        when (operators[i]) {
                            "X" -> numbers[i] = numbers[i] * numbers[i + 1]
                            "/" -> numbers[i] = numbers[i] / numbers[i + 1]
                        }
                        numbers.removeAt(i + 1)
                        operators.removeAt(i)
                    } else if (operators[i] == "%"){
                        numbers[i] = numbers[i]/100
                        operators.removeAt(i)
                    } else {
                        i++
                    }
                }

                var j = 0
                while (j < operators.size) {
                    if (operators[j] == "+" || operators[j] == "-") {
                        when (operators[j]) {
                            "+" -> numbers[j] = numbers[j] + numbers[j + 1]
                            "-" ->numbers[j] = numbers[j] - numbers[j + 1]
                        }
                        numbers.removeAt(j + 1)
                        operators.removeAt(j)
                    } else {
                        j++
                    }
                }

                return if (numbers.isNotEmpty()) numbers[0] else 0.0
            }
            return 0.0
        }


        fun onEqualsClick(view: View){
            val editTextValue = editText.text.toString()
            val result: Double = "%.3f".format(calculate(editTextValue)).toDouble()
            editText.setText(result.toString())
        }

        fun onEraseClick(view: View) {
            var editTextValue = editText.text.toString()
            if (editTextValue.isNotEmpty()) {
                editTextValue = editTextValue.substring(0, editTextValue.length - 1)
                editText.setText(editTextValue)
            }
        }

        val buttonOne: Button = findViewById(R.id.buttonOne)
        val buttonTwo: Button = findViewById(R.id.buttonTwo)
        val buttonThree: Button = findViewById(R.id.buttonThree)
        val buttonFour: Button = findViewById(R.id.buttonFour)
        val buttonFive: Button = findViewById(R.id.buttonFive)
        val buttonSix: Button = findViewById(R.id.buttonSix)
        val buttonSeven: Button = findViewById(R.id.buttonSeven)
        val buttonEight: Button = findViewById(R.id.buttonEight)
        val buttonNine: Button = findViewById(R.id.buttonNine)
        val buttonZero: Button = findViewById(R.id.buttonZero)
        val buttonComa: Button = findViewById(R.id.buttonComa)
        val buttonCast: Button = findViewById(R.id.buttonCast)
        val buttonPlus: Button = findViewById(R.id.buttonPlus)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonMultiplication: Button = findViewById(R.id.buttonMultiplication)
        val buttonDivision: Button = findViewById(R.id.buttonDivision)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonProcent: Button = findViewById(R.id.buttonProcent)
        val buttonBrackets: Button = findViewById(R.id.buttonBrackets)
        val buttonImage: ImageButton= findViewById(R.id.imageButton)

        buttonOne.setOnClickListener { onButtonClick(it) }
        buttonTwo.setOnClickListener { onButtonClick(it) }
        buttonThree.setOnClickListener { onButtonClick(it) }
        buttonFour.setOnClickListener { onButtonClick(it) }
        buttonFive.setOnClickListener { onButtonClick(it) }
        buttonSix.setOnClickListener { onButtonClick(it) }
        buttonSeven.setOnClickListener { onButtonClick(it) }
        buttonEight.setOnClickListener { onButtonClick(it) }
        buttonNine.setOnClickListener { onButtonClick(it) }
        buttonZero.setOnClickListener { onButtonClick(it) }
        buttonComa.setOnClickListener { onDotClick(it) }
        buttonCast.setOnClickListener { onCastClick(it) }
        buttonEquals.setOnClickListener { onEqualsClick(it) }
        buttonPlus.setOnClickListener { onButtonClick(it) }
        buttonMultiplication.setOnClickListener { onButtonClick(it) }
        buttonDivision.setOnClickListener { onButtonClick(it) }
        buttonProcent.setOnClickListener{ onButtonClick(it)}
        buttonMinus.setOnClickListener { onButtonClick(it) }

        buttonImage.setOnClickListener{onEraseClick(it)}
    }
}
