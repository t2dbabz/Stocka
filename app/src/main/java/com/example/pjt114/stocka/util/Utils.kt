package com.example.pjt114.stocka.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


    fun getBase64(filePath: String?): String? {
        var encodeString: String? = null
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            val bitmap = BitmapFactory.decodeFile(filePath)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()
            encodeString =
                Base64.encodeToString(imageBytes, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return encodeString
    }
