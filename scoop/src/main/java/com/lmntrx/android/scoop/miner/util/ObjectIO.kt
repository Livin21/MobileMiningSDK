package com.lmntrx.android.scoop.miner.util

import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@Suppress("UNCHECKED_CAST")
fun <T> getObjectByString(content: String): T? {
    return try {
        val byteIS = ByteArrayInputStream(Base64.decode(content.toByteArray(Charsets.ISO_8859_1), Base64.DEFAULT))
        val ois = ObjectInputStream(byteIS)
        val t: T = ois.readObject() as T
        ois.close()
        byteIS.close()
        t
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun getStringByObject(any: Any): String {
    return try {
        val byteOS = ByteArrayOutputStream()
        val oos = ObjectOutputStream(byteOS)
        oos.writeObject(any)
        oos.flush()
        oos.close()
        byteOS.close()
        Base64.encode(byteOS.toByteArray(), Base64.DEFAULT).toString(Charsets.ISO_8859_1)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
