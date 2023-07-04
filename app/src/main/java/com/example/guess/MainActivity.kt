package com.example.guess

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.number

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","secret:"+secretNumber.secret)
    }

    fun check(v: View) {
        val n = number.text.toString().toInt()
        println("number: $n")
        Log.d("MainActivity", "number:"+n)

        // 可以在 Logcat 先看到 secretNumber
        val diff = secretNumber.validate(n)   // 使用者輸入的 n 與隨機產生的數字差
        var message = "Yes, you goy it"
        if (diff < 0) {
            /* 假設比對數字小於 0，要有訊息告訴人家，可以使用 Toast 浮動元件，呼叫 makeText() 方法，
                此方法中需要三個參數「context 物件」「text: CharSequence 物件」「Int 整數值」，
                然後因為 MainActivity 繼承了 AppCompatActivity，而上層的類別中有一個叫 Context
                的類別(註1)，所以 MainActivity 也算是 context 類別的一員，所以可以把 MainActivity 放進
                context 物件，此時可以用「this」代表 MainActivity。
                CharSequence 是 Java 中 String 的父類別，所以可以直接使用字串。
                最後的整數值使用 Toast 裡面定義好的一些「常數」，「LENGTH_LONG」代表 1，「LENGTH_SHORT」代表 0
                最後要再呼叫 .show() 他才會顯示在畫面上
             */
            message = "Bigger"
        } else if (diff > 0) {
            message = "Smaller"
        }
        // 使用 Toast 的類別去完成訊息顯示，特色是稍縱即逝
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()

        /* 也可以用另一種叫做「對話筐的顯示」，使用 Android 裡面另一個類別「AlertDialog」，他有兩個可選，
        一個是原生性的 AlertDialog (android.app)，一個是 AlertDialog (androidx.appcompat.app)，
        因為有新版舊版問題，建議使用後者。使用此類別身上的 Builder 模式，可以方便我們產生各種複雜型態的物件，
        要產生的複雜物件就是「對話筐物件」，所以先用 .setTitle() 設定標題，然後用 .setMessage 設定訊息，
        還可用 .setPositiveButton 設定他有沒有按鈕（之後會再介紹），最後在用 .show 顯示出來。使用 Builder
        時通常會跳行比較清楚
         */
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK", null)  // 有一顆按鈕叫 OK，按了不會有反應
            .show()
    }
}

