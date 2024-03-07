package controllers

import models.Client
import utilities.boolean
import utilities.*
import java.io.*

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

fun opcio2(file: File) {
    println("**Consulta d’un client per posició**")
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

fun opcio3(file: File) {
    println("**Consulta d’un client per codi**")
    val fileReader = DataInputStream(FileInputStream(file))
    val c = buscarPerCodi(fileReader)
    if (c != null) {
        println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
    }

    fileReader.close()
}

fun opcio4(file: File) {
    println("**Modificar un client**")

    val tempFile = File(file.parentFile, "temp.dat")

    val fileReader = DataInputStream(FileInputStream(file))
    val fileWriterTemp = DataOutputStream(FileOutputStream(tempFile))

    try {
        println("Digues el codi del client a modificar: ")
        val codiModificar = int("Introdueix un codi vàlid: ")
        var clienteEncontrado = false

        var finDeArchivo = false
        while (!finDeArchivo) {
            try {
                val client = readClient(fileReader)
                if (client.getCodi() == codiModificar) {
                    println("Quina informació vols modificar?")
                    println("1- Nom")
                    println("2- Cognoms")
                    println("3- Dia de naixement")
                    println("4- Mes de naixement")
                    println("5- Any de naixement")
                    println("6- Adreça postal")
                    println("7- E-mail")
                    println("8- Es VIP")
                    var cambi = intRange(1..8,"Prova un altre cop: ")
                    when (cambi) {
                        1 -> {
                            println("Nom actual (${client.getNom()}), introduir nou nom: ")
                            val nuevoNombre = stringLine("Prova un altre cop: ")
                            client.setNom(nuevoNombre)
                        }
                        2 -> {
                            println("Cognom actual (${client.getCognoms()}), introduir nou cognom: ")
                            val nuevoCognom = stringLine("Prova un altre cop: ")
                            client.setCognoms(nuevoCognom)
                        }
                        3 -> {
                            println("Dia de naixement actual (${client.getDia()}), introduir nou dia: ")
                            val nuevoDia = intSize(2,"Prova un altre cop: ")
                            client.setDia(nuevoDia)
                        }
                        4 -> {
                            println("Mes de naixement actual (${client.getMes()}), introduir nou mes: ")
                            val nuevoMes = intSize(2,"Prova un altre cop: ")
                            client.setMes(nuevoMes)
                        }
                        5 -> {
                            println("Any de naixement actual (${client.getAny()}), introduir nou any: ")
                            val nuevoAny = intSize(4,"Prova un altre cop: ")
                            client.setAny(nuevoAny)
                        }
                        6 -> {
                            println("Adreça postal actual (${client.getAdrecaPostal()}), introduir nova adreça: ")
                            val nuevaAdreca = stringLine("Prova un altre cop: ")
                            client.setAdrecaPostal(nuevaAdreca)
                        }
                        7 -> {
                            println("E-mail actual (${client.getEmail()}), introduir nou e-mail: ")
                            val nuevoEmail = stringLine("Prova un altre cop: ")
                            client.setEmail(nuevoEmail)
                        }
                        8 -> {
                            println("Es VIP actual (${client.isEsVIP()}), introduir nou valor: ")
                            val nuevoVIP = boolean("Prova un altre cop: ")
                            client.setEsVIP(nuevoVIP)
                        }
                    }
                    writeClient(fileWriterTemp, client)
                    clienteEncontrado = true
                } else {
                    writeClient(fileWriterTemp, client)
                }
            } catch (_: EOFException) {
                finDeArchivo = true
            }
        }

        if (!clienteEncontrado) {
            println("No s'ha trobat cap client amb el codi $codiModificar")
        }
    } finally {
        fileReader.close()
        fileWriterTemp.flush()
        fileWriterTemp.close()
    }

    if (!file.exists() || (file.delete() && tempFile.renameTo(file))) {
        println("Client modificat correctament")
    } else {
        println("Error al modificar el client")
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


fun opcio6(file: File) {
    println("**Llistat de tots els clients**")
    val fileReader = DataInputStream(FileInputStream(file))
    try {
        while (true) {
            val c = readClient(fileReader)
            println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
        }
    } catch (_:Exception) {}
    fileReader.close()
}
