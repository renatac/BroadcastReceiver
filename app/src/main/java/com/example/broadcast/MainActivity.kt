package com.example.broadcast

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButton()
    }

    private fun setupButton() {
        //Modo implícito (Intent filter + ACTION + Category) - Chamada de Uma activity de outro App
        btn.setOnClickListener {
            sendBroadCast()
            val intent = Intent("CUSTOM_CATEGORY")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    private fun sendBroadCast() {
        val intent = Intent()
        intent.action = "com.example.broadcast.basicandroidproject"
        intent.putExtra("secretKey", "study")
        /* O sinalizador FLAG_INCLUDE_STOPPED_PACKAGES é adicionado ao intent antes de ser enviado para
        indicar que o intent deve ter permissão para iniciar um componente de um aplicativo interrompido. */
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.component = ComponentName("com.example.basicandroidproject", "com.example.basicandroidproject.MyReceiver")
        sendBroadcast(intent, "android.permission.MY_CUSTOM_PERMISSION")
    }
}