package com.example.cutie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import kotlin.math.pow



class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var currentInput: String = ""
    private var operator: String = ""
    private var firstOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //------------------- Кнопки цифр и операций-----------------------
        val button0: Button = findViewById(R.id.button13)
        val button1: Button = findViewById(R.id.button12)
        val button2: Button = findViewById(R.id.button15)
        val button3: Button = findViewById(R.id.button20)
        val button4: Button = findViewById(R.id.button11)
        val button5: Button = findViewById(R.id.button16)
        val button6: Button = findViewById(R.id.button19)
        val button7: Button = findViewById(R.id.button10)
        val button8: Button = findViewById(R.id.button17)
        val button9: Button = findViewById(R.id.button18)

        val buttonAC: Button = findViewById(R.id.button1)
        val buttonSign: Button = findViewById(R.id.button2)
        val buttonFact: Button = findViewById(R.id.button3)
        val buttonPow: Button = findViewById(R.id.button4)
        val buttonDel: Button = findViewById(R.id.button5)
        val buttonMult: Button = findViewById(R.id.button6)
        val buttonMinus: Button = findViewById(R.id.button7)
        val buttonPlus: Button = findViewById(R.id.button8)
        val buttonEqual: Button = findViewById(R.id.button9)
        val buttonComma: Button = findViewById(R.id.button14)

        textView = findViewById(R.id.textView)

        // --------------- определение функций ------------------
        fun appendComma() {
            if (!currentInput.contains(".")) {
                if (currentInput.isEmpty()) {
                    currentInput = "0."
                } else {
                    currentInput += "."
                }
                textView.text = currentInput
            }
        }

        fun appendNumber(num: String) {
            if (currentInput == "0" && num != ".") {
                currentInput = num
            } else {
                currentInput += num
            }
            textView.text = currentInput
        }


        fun setOperator(op: String){
            if(currentInput.isNotEmpty()){
                firstOperand = currentInput.toDouble()
                textView.text = currentInput + op
                currentInput = ""
                operator = op
            }
        }

        fun clearAll(){
            currentInput = ""
            textView.text = "0"
            firstOperand = 0.0
            operator = ""

        }

        fun changeSign(){
            if(currentInput.isNotEmpty()){
                currentInput = (currentInput.toDouble() * (-1)).toString()
                textView.text = currentInput
            }
        }

        fun factorial(num: Double): Double {
            if (num < 0 || num != num.toInt().toDouble())
                return Double.NaN
            return if (num == 0.0 || num == 1.0) {
                1.0
            } else {
                num * factorial(num - 1.0)
            }
        }

        fun Factorial(){
            if(currentInput.isEmpty()){
                textView.text = "Ошибка"
            } else {
                firstOperand = currentInput.toDouble()
                var num = firstOperand
                textView.text =
                    if (factorial(num).isNaN()) "Ошибка" else factorial(num).toString()
              }
        }

        fun calculateResult(){
            if(currentInput.isNotEmpty() && operator.isNotEmpty()){
                val secondOperand = currentInput.toDouble()
                val result = when (operator) {
                    "+" -> firstOperand + secondOperand
                    "-" -> firstOperand - secondOperand
                    "x" -> firstOperand * secondOperand
                    "/" -> if (secondOperand!= 0.0) firstOperand / secondOperand else Double.NaN
                    "^" -> firstOperand.pow(secondOperand)
                    else -> Double.NaN
                }
                textView.text = if (result.isNaN()) "На ноль делить нельзя" else result.toString()
                currentInput = result.toString()
                operator = ""
                firstOperand = result
            }
        }


        // ------------------- Установка обработчиков кнопок ----------------------
        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }

        buttonPlus.setOnClickListener { setOperator("+") }
        buttonMinus.setOnClickListener { setOperator("-") }
        buttonDel.setOnClickListener { setOperator("/") }
        buttonMult.setOnClickListener { setOperator("x") }
        buttonPow.setOnClickListener { setOperator("^") }
        buttonFact.setOnClickListener { Factorial() }
        buttonEqual.setOnClickListener { calculateResult() }
        buttonComma.setOnClickListener { appendComma() }
        buttonSign.setOnClickListener { changeSign() }
        buttonAC.setOnClickListener { clearAll() }



    }
}
