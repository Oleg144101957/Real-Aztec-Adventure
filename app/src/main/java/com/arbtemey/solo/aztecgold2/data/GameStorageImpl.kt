package com.arbtemey.solo.aztecgold2.data

import com.arbtemey.solo.aztecgold2.domain.AztecMemoryStorage
import io.paperdb.Paper

class GameStorageImpl() : AztecMemoryStorage{

    override fun readGamerName(): String {
        return Paper.book().read<String>(AztecMemoryStorage.PLAYERNAME) ?: AztecMemoryStorage.PLAYERNAMEDEFAULT
    }

    override fun saveGamerName(name: String) {
        Paper.book().write(AztecMemoryStorage.PLAYERNAME, name)
    }

    override fun saveDestination(data: String) {
        Paper.book().write(AztecMemoryStorage.DESTINATION, data)
    }

    override fun readDestination(): String {
        return Paper.book().read<String>(AztecMemoryStorage.PLAYERNAME) ?: AztecMemoryStorage.DEFAULTDESTINATION
    }
}