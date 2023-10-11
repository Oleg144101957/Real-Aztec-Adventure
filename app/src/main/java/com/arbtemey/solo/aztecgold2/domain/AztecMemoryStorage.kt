package com.arbtemey.solo.aztecgold2.domain

interface AztecMemoryStorage {

    //Player info
    fun readGamerName() : String
    fun saveGamerName(name: String)

    //Destination saver
    fun saveDestination(data: String)
    fun readDestination(): String

    companion object{
        const val PLAYERNAME = "player_name"
        const val PLAYERNAMEDEFAULT = "player_name_name"
        const val DESTINATION = "destination"
        const val DEFAULTDESTINATION = "default_destination"
    }
}