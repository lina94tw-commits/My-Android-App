package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Page2Fragment : Fragment(R.layout.fragment_p2) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 綁定畫面上的三顆按鈕
        // ⚠️ 請務必確認你的 fragment_p2.xml 裡面有這三個 ID 的 Button
        val button = view.findViewById<Button>(R.id.button3)
        val button2 = view.findViewById<Button>(R.id.button4)
        val button3 = view.findViewById<Button>(R.id.button5)

        // 2. 第一顆按鈕：撥打電話 (ACTION_DIAL)
        button.setOnClickListener {
            val uri = Uri.parse("tel:0912345678")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }

        // 3. 第二顆按鈕：開啟網頁 (ACTION_VIEW)
        button2.setOnClickListener {
            // (已幫你修正 developer.android.com 的拼字)
            val uri = Uri.parse("https://byeorisim.itch.io/brush-jjaemu")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        // 4. 第三顆按鈕：開啟地圖特定座標 (ACTION_VIEW)
        button3.setOnClickListener {
            // 我幫你把字串稍微整理乾淨了，直接把經緯度用逗號接起來即可
            val uri = Uri.parse("geo:25.033612949543535,121.56477681335085")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}