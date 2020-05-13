package com.example.mobdevass3.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.mobdevass3.utils.Consts.SECURE_FILE_NAME
import com.example.mobdevass3.utils.Consts.TOKEN_SECRET_KEY


object Prefs {
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    lateinit var sharedPreferences: SharedPreferences

    fun createEncryptedPreferences(context: Context) {
        sharedPreferences = EncryptedSharedPreferences
            .create(
                SECURE_FILE_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }

    fun putToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_SECRET_KEY, token).apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN_SECRET_KEY, "").toString()
    }
}
