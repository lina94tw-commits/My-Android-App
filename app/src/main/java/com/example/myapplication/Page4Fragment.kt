package com.example.myapplication

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.example.myapplication.databinding.FragmentP4Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Page4Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page4Fragment : Fragment(R.layout.fragment_p4) {
    private var _binding: FragmentP4Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. 綁定畫面與 Binding
        _binding = FragmentP4Binding.bind(view)

        // 3. 將圖片的背景轉換為 AnimationDrawable
        // 注意：必須使用 binding.imgFrame 取得元件
        val frameAnim = binding.imgFrame.background as AnimationDrawable

        // 4. 設定開始按鈕點擊事件
        binding.btnStart.setOnClickListener {
            frameAnim.start() // 開始播放貓咪梗圖
        }

        // 5. 設定停止按鈕點擊事件
        binding.btnStop.setOnClickListener {
            frameAnim.stop() // 停止播放
        }
        // 1. 透明度動畫 (Alpha)
        binding.btnAlpha.setOnClickListener {
            val anim = AlphaAnimation(
                1.0f,  // 起始透明度
                0.2f   // 結束透明度
            )
            anim.duration = 1000 // 動畫持續 1 秒
            binding.imgTween.startAnimation(anim) // 執行對象是 binding.imgTween
        }

        // 2. 縮放動畫 (Scale)
        binding.btnScale.setOnClickListener {
            val anim = ScaleAnimation(
                1.0f,  // X 起始比例
                1.5f,  // X 結束比例
                1.0f,  // Y 起始比例
                1.5f   // Y 結束比例
            )
            anim.duration = 1000
            binding.imgTween.startAnimation(anim)
        }

        // 3. 位移動畫 (Translate)
        binding.btnTranslate.setOnClickListener {
            val anim = TranslateAnimation(
                0f,     // X 起點
                100f,   // X 終點
                0f,     // Y 起點
                -100f   // Y 終點
            )
            anim.duration = 1000
            binding.imgTween.startAnimation(anim)
        }

        // 4. 旋轉動畫 (Rotate)
        binding.btnRotate.setOnClickListener {
            val anim = RotateAnimation(
                0f,      // 起始角度
                360f,    // 結束角度
                RotateAnimation.RELATIVE_TO_SELF, // X 以自身位置旋轉
                0.5f,    // X 旋轉中心點
                RotateAnimation.RELATIVE_TO_SELF, // Y 以自身位置旋轉
                0.5f     // Y 旋轉中心點
            )
            anim.duration = 1000
            binding.imgTween.startAnimation(anim)
        }
    }

    // 畫面銷毀時清空 Binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}