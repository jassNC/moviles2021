package com.jasson.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ans: Float?
        ans = null
        loadButtons(ans)
        solveBtn.setOnClickListener{
            try {
                var a = textScreen.text.toString()
                var result = ExpressionBuilder(a).build().evaluate().toFloat()
                ans = result
                textScreen.text = result.toString()
            }catch (e: Exception){
                textScreen.text = "Syntax Error"
            }
        }

        ansBtn.setOnClickListener{
            clearSE()
            when(ans!=null) {
                true -> textScreen.text = "${textScreen.text}$ans"
            }
        }
    }

    fun buttonPressed(value: Char){
        textScreen.text = "${textScreen.text}$value"
    }

    fun clearSE(){
        if(textScreen.text.equals("Syntax Error")||textScreen.text.isEmpty()){
            textScreen.text=""
        }
    }

    fun loadButtons(ans: Float?){
        number1Btn.setOnClickListener{
            clearSE()
            buttonPressed('1')
        }
        number2Btn.setOnClickListener{
            clearSE()
            buttonPressed('2')
        }
        number3Btn.setOnClickListener{
            clearSE()
            buttonPressed('3')

        }
        number4Btn.setOnClickListener{
            clearSE()
            buttonPressed('4')

        }
        number5Btn.setOnClickListener{
            clearSE()
            buttonPressed('5')

        }
        number6Btn.setOnClickListener{
            clearSE()
            buttonPressed('6')

        }
        number7Btn.setOnClickListener{
            clearSE()
            buttonPressed('7')

        }
        number8Btn.setOnClickListener{
            clearSE()
            buttonPressed('8')

        }
        number9Btn.setOnClickListener{
            clearSE()
            buttonPressed('9')

        }
        number0Btn.setOnClickListener{
            clearSE()
            buttonPressed('0')
        }

        plusBtn.setOnClickListener{
            clearSE()
            buttonPressed('+')
        }

        divBtn.setOnClickListener{
            clearSE()
            buttonPressed('/')
        }

        multBtn.setOnClickListener{
            clearSE()
            buttonPressed('*')
        }

        minBtn.setOnClickListener{
            clearSE()
            buttonPressed('-')
        }

        clearBtn.setOnClickListener{
            textScreen.text=""
        }

        delBtn.setOnClickListener{
            clearSE()
            textScreen.text=textScreen.text.dropLast(1)
        }

        dotBtn.setOnClickListener{
            clearSE()
            buttonPressed('.')
        }
    }

}