package controllers

import models.Client
import utilities.int
import java.io.*

fun tempToFIle(file: File) {
    val fileWriter = DataOutputStream(FileOutputStream(file,true))
    val fileReaderTemp = DataInputStream(FileInputStream("./temp.dat"))

    try {
        while (true) {
            val newClient = readClient(fileReaderTemp)
            fileWriter.writeInt(newClient.getCodi())
            fileWriter.writeUTF(newClient.getNom())
            fileWriter.writeUTF(newClient.getCognoms())
            fileWriter.writeInt(newClient.getDia())
            fileWriter.writeInt(newClient.getMes())
            fileWriter.writeInt(newClient.getAny())
            fileWriter.writeUTF(newClient.getAdrecaPostal())
            fileWriter.writeUTF(newClient.getEmail())
            fileWriter.writeBoolean(newClient.isEsVIP())

        }
    }catch (_:Exception){} finally {
        fileWriter.flush()
        fileWriter.close()
        fileReaderTemp.close()
        File("./temp.dat").delete()
    }
}

fun writeClient(fileWriter: DataOutputStream, client: Client) {
    fileWriter.writeInt(client.getCodi())
    fileWriter.writeUTF(client.getNom())
    fileWriter.writeUTF(client.getCognoms())
    fileWriter.writeInt(client.getDia())
    fileWriter.writeInt(client.getMes())
    fileWriter.writeInt(client.getAny())
    fileWriter.writeUTF(client.getAdrecaPostal())
    fileWriter.writeUTF(client.getEmail())
    fileWriter.writeBoolean(client.isEsVIP())
    fileWriter.flush()
}

fun buscarPerCodi(fileReader:DataInputStream):Client? {
    println("Digues el codi del client: ")
    val codi = int("Prova un altre cop: ")
    var c = Client(0,"","",0,0,0,"","",false)
    try {
        var found = false
        while(!found) {
            c = readClient(fileReader)
            if (c.getCodi() == codi) found = true
        }
        return c
    } catch (e:Exception) {
        println("No hi ha un usuari amb el codi $codi")
    }
    return null
}

fun readClient(fileReader: DataInputStream):Client {
    val codi = fileReader.readInt()
    val nom = fileReader.readUTF()
    val cognom = fileReader.readUTF()
    val dia = fileReader.readInt()
    val mes = fileReader.readInt()
    val any = fileReader.readInt()
    val adrecaPostal = fileReader.readUTF()
    val email = fileReader.readUTF()
    val esVIP = fileReader.readBoolean()
    return Client(codi,nom,cognom,dia,mes,any,adrecaPostal,email, esVIP)
}