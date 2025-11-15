package com.example.cuidartrite.datastore


import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.cuidartrite.proto.UserProto
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<UserProto> {
    override val defaultValue: UserProto = UserProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserProto {
        try {
            return UserProto.parseFrom(input)
        } catch (e: Exception) {
            throw CorruptionException("Erro ao ler UserProto", e)
        }
    }

    override suspend fun writeTo(t: UserProto, output: OutputStream) = t.writeTo(output)
}
