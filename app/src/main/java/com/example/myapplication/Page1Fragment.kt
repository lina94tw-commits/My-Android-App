package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit

class Page1Fragment : Fragment(R.layout.fragment_p1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 綁定按鈕 (對應原本的 binding.button 和 binding.button2)
        val button = view.findViewById<Button>(R.id.button)
        val button2 = view.findViewById<Button>(R.id.button2)

        // 2. 準備 Fragment 實體
        // 這裡直接呼叫即可。如果你之前沒刪掉 myFragment1 裡面的預設程式碼，
        // 也可以維持你原本的寫法：val fm1 = myFragment1.newInstance("", "")
        val fm1 = myFragment1()
        val fm2 = myFragment2()

        // 3. 按鈕 1 的點擊事件
        button.setOnClickListener {
            // ⭐ 關鍵：改成 childFragmentManager
            childFragmentManager.commit {
                setCustomAnimations(R.anim.slide_in, R.anim.fade_out)
                setReorderingAllowed(true)
                // ⚠️ 注意：這裡的 ID 要改成 fragment_p1.xml 裡面的 Container ID
                // 假設你照著我上一篇的建議，取名叫 innerFragmentContainer
                replace(R.id.fragmentContainerView, fm1)
                addToBackStack(null)
            }
        }

        // 4. 按鈕 2 的點擊事件
        button2.setOnClickListener {
            childFragmentManager.commit {
                setCustomAnimations(R.anim.slide_in, R.anim.fade_out)
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView, fm2)
                addToBackStack(null)
            }
        }
    }
}