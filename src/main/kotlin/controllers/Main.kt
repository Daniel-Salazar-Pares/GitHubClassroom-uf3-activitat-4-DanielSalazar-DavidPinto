package org.example.controllers

import models.Client
import utilities.*
import java.io.*

fun main() {
    ex2(File("./clients.dat"))
}

fun ex1(file:File) {
    val fileWriter = DataOutputStream(FileOutputStream(file))
    do {
        println("Digues un numero: ")
        val input = int("Prova un altre cop: ")
        if (input != 0) {
            fileWriter.writeInt(input)
        }
    }while(input != 0)
    fileWriter.flush()
    fileWriter.close()
}

fun ex2(file:File) {
    do {
        val opcion = menu()
        when (opcion) {
            1 -> opcio1(file)
            2 -> opcio2(file)
            3 -> opcio3(file)
            4 -> opcio4(file)
            5 -> opcio5(file)
            6 -> opcio6(file)
        }
    } while (opcion != 7)
}

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
        file.delete()
    }
}
fun opcio5(file: File) {
    var fileReader = DataInputStream(FileInputStream(file))
    val c = buscarPerCodi(fileReader)
    fileReader.close()

    if (c != null) {
        val fileWriter = DataOutputStream(FileOutputStream(file,false))
        val fileWriterTemp = DataOutputStream(FileOutputStream("./temp.dat",true))
        try {
            fileReader = DataInputStream(FileInputStream(file))
            while (true) {
                val newClient = readClient(fileReader)
                if (newClient.getCodi() != c.getCodi())  {
                    fileWriterTemp.writeInt(c.getCodi())
                    fileWriterTemp.writeUTF(c.getNom())
                    fileWriterTemp.writeUTF(c.getCognoms())
                    fileWriterTemp.writeInt(c.getDia())
                    fileWriterTemp.writeInt(c.getMes())
                    fileWriterTemp.writeInt(c.getAny())
                    fileWriterTemp.writeUTF(c.getAdrecaPostal())
                    fileWriterTemp.writeUTF(c.getEmail())
                    fileWriterTemp.writeBoolean(c.isEsVIP())
                }
            }
        }catch (_:Exception){} finally {
            fileWriterTemp.flush()
            fileWriterTemp.close()
            fileReader.close()
            fileWriter.writeUTF("")
            fileWriter.flush()
            fileWriter.close()
        }
        tempToFIle(File("./temp.dat"))
    }
}

fun opcio4(file: File) {
}

fun opcio6(file: File) {
    val fileReader = DataInputStream(FileInputStream(file))
    try {
        while (true) {
            val c = readClient(fileReader)
            println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
        }
    } catch (_:Exception) {}
    fileReader.close()
}

fun opcio2(file: File) {
    val fileReader = DataInputStream(FileInputStream(file))
    println("Digues la posició del client: ")
    val pos = int("Prova un altre cop: ")
        try {
            var c = Client(0,"","",0,0,0,"","",false)
            repeat(pos) {
                c = readClient(fileReader)
            }
            println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
        } catch (e:Exception) {
            println("No hi ha un usuari a la posició $pos")
        }
    fileReader.close()

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
fun opcio3(file: File) {
    val fileReader = DataInputStream(FileInputStream(file))
    val c = buscarPerCodi(fileReader)
    if (c != null) {
        println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
    }

    fileReader.close()
}

fun opcio1(file: File) {
    val fileWriter = DataOutputStream(FileOutputStream(file,true))

    println("**Alta d’un client**")
    println("Digues el codi de l'usuari: ")
    fileWriter.writeInt(int("Prova un altre cop: "))

    println("Digues el nom de l'usuari: ")
    fileWriter.writeUTF(stringLine("Prova un altre cop: "))

    println("Digues el cognoms de l'usuari: ")
    fileWriter.writeUTF(stringLine("Prova un altre cop: "))

    println("Digues el dia de naixement de l'usuari: ")
    fileWriter.writeInt(intSize(2,"Prova un altre cop: "))
    println("Digues el mes de naixement de l'usuari: ")
    fileWriter.writeInt(intSize(2,"Prova un altre cop: "))
    println("Digues el any de naixement de l'usuari: ")
    fileWriter.writeInt(intSize(4,"Prova un altre cop: "))


    println("Digues l'adreça postal de l'usuari: ")
    fileWriter.writeUTF(stringLine("Prova un altre cop: "))

    println("Digues el e-mail de l'usuari: ")
    fileWriter.writeUTF(stringLine("Prova un altre cop: "))

    println("Digues si l'usuari es vip: ")
    fileWriter.writeBoolean(boolean("Prova un altre cop: "))

    fileWriter.flush()
    fileWriter.close()
}


fun menu():Int {
    println("1- Alta d’un client \n" +
            "2- Consulta d’un client per posició\n" +
            "3- Consulta d’un client per codi\n" +
            "4- Modificar un client\n" +
            "5- Esborrar un client\n" +
            "6- Llistat de tots els clients\n" +
            "7- Sortir")
    return intRange(1..7,"Prova un altre cop: ")
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
