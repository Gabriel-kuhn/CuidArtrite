package com.example.cuidartrite.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.cuidartrite.proto.UserProto
import com.example.cuidartrite.network.models.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object UserDataStore {

    private lateinit var userDataStore: DataStore<UserProto>

    fun initialize(appContext: Context) {
        userDataStore = appContext.applicationContext.userDataStore
    }

    // Ler o usuário salvo
    suspend fun read(): User? {
        val proto = userDataStore.data.first()
        return proto.takeIf { it.username.isNotEmpty() }?.let {
            User(
                id = it.id,
                username = it.username,
                nome = it.nome,
                email = it.email,
                idade = it.idade,
                sexo = it.sexo,
                telefone = it.telefone,
                tamanhoFonte = it.tamanhoFonte,
                contraste = it.contraste,
                leituraVoz = it.leituraVoz,
                coletarDados = it.coletarDados,
                password = it.password
            )
        }
    }

    // Observar mudanças reativas
    fun observe() = userDataStore.data.map { proto ->
        if (proto.username.isEmpty()) null else
            User(
                id = proto.id,
                username = proto.username,
                nome = proto.nome,
                email = proto.email,
                idade = proto.idade,
                sexo = proto.sexo,
                telefone = proto.telefone,
                tamanhoFonte = proto.tamanhoFonte,
                contraste = proto.contraste,
                leituraVoz = proto.leituraVoz,
                coletarDados = proto.coletarDados,
                password = proto.password
            )
    }

    // Salvar usuário
    suspend fun write(user: User) {
        userDataStore.updateData {
            it.toBuilder()
                .setId(user.id ?: 0)
                .setUsername(user.username)
                .setNome(user.nome)
                .setEmail(user.email)
                .setIdade(user.idade)
                .setSexo(user.sexo)
                .setTelefone(user.telefone)
                .setTamanhoFonte(user.tamanhoFonte)
                .setContraste(user.contraste)
                .setLeituraVoz(user.leituraVoz)
                .setColetarDados(user.coletarDados)
                .setPassword(user.password ?: "")
                .build()
        }
        Log.i("DataStore", "UserDataStore: atualizado! (${user.username})")
    }

    // Limpar usuário (logout)
    suspend fun clear() {
        userDataStore.updateData { UserProto.getDefaultInstance() }
        Log.i("DataStore", "UserDataStore: limpo.")
    }

    private val Context.userDataStore: DataStore<UserProto> by dataStore(
        fileName = "user_prefs",
        serializer = UserSerializer
    )
}
