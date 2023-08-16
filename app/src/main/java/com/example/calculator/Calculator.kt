package com.example.calculator

class Calculator {
    var string = "25+36*2"
    fun calculate(string: String) {
        if (string != "") {
            var numbers = mutableListOf<Double>()
            var operators = mutableListOf<String>()

            var currentNumber = StringBuilder()

            for (char in string) {
                if (char.isDigit() || char == '.') {
                    currentNumber.append(char)
                } else {
                    if (currentNumber.isNotEmpty()) {
                        numbers.add(currentNumber.toString().toDouble())
                        currentNumber.clear()
                    }
                    if (char.toString().trim() != "") {
                        operators.add(char.toString())
                    }
                }
            }

            if (currentNumber.isNotEmpty()) {
                numbers.add(currentNumber.toString().toDouble())
            }

            // Виведіть зміст списків для перевірки:
            println("Numbers: $numbers")
            println("Operators: $operators")
        }
    }

    fun main() {
        val string = "25+36*2"
        calculate(string)
    }
}