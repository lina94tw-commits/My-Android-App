package com.example.myapplication

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentP3Binding
import android.Manifest

class Page3Fragment : Fragment(R.layout.fragment_p3) {
    // --- 1. Fragment 專用的 Binding 宣告方式 ---
    private var _binding: FragmentP3Binding? = null
    private val binding get() = _binding!!

    // --- 2. 修正後的變數宣告 ---
    private var fgPermission = true
    private lateinit var picUri: Uri // 這裡改成冒號 :

    // --- 3. 註冊權限與相機回呼 (必須在類別最外層) ---
    private val reqPermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        result.forEach { (permission, isGranted) ->
            if (!isGranted) {
                fgPermission = false
            }
        }
    }

    private val getPicture = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // 使用 binding 直接操控 imageView
            binding.imageView4.setImageURI(picUri)
        }
    }

    // --- 4. 畫面建立與按鈕邏輯 ---
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 將畫面與 Binding 綁定
        _binding = FragmentP3Binding.bind(view)

        // 檢查權限
        val permissions = checkPermissions()
        if (permissions.isNotEmpty()) {
            reqPermissions.launch(permissions.toTypedArray())
        }

        // 使用 binding 設定按鈕點擊事件
        binding.button6.setOnClickListener {
            if (fgPermission) {
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "test1.png")
                }

                // Fragment 中使用 contentResolver 要加上 requireContext()
                val imgURI = requireContext().contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
                )

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                    putExtra(MediaStore.EXTRA_OUTPUT, imgURI)
                }

                picUri = imgURI!!
                getPicture.launch(intent)
            } else {
                Toast.makeText(requireContext(), "需要取得照相機的權限", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // --- 5. 釋放 Binding (Fragment 必做) ---
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 畫面銷毀時清空，避免記憶體洩漏
    }

    // --- 6. 權限檢查副程式 ---
    private fun checkPermissions(): ArrayList<String> {
        val permissions = ArrayList<String>()

        val camera = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.CAMERA
        )
        val writeStorage = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (camera != PackageManager.PERMISSION_GRANTED) {
            permissions.add(android.Manifest.permission.CAMERA)
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            if (writeStorage != PackageManager.PERMISSION_GRANTED) {
                permissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
        return permissions
    }
}