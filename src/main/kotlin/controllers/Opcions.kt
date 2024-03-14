package controllers

import org.example.controllers.mapaCodisAPosicions
import utilities.*
import java.io.*

fun opcio1(file: File, fileMap: File) {
    val fileWriter = RandomAccessFile(file, "rw")
    val posicioInicial = fileWriter.length()
    println("**Alta d’un client**")

    println("Digues el codi de l'usuari: ")
    val codi = int("Prova un altre cop: ")
    fileWriter.seek(posicioInicial)
    fileWriter.writeInt(codi)

    println("Digues el nom de l'usuari: ")
    val nom = stringLine("Prova un altre cop (longitud maxima 20 caracters): ", 20)
    fileWriter.writeChars(nom.padEnd(20, ' '))

    println("Digues els cognoms de l'usuari: ")
    val cognoms = stringLine("Prova un altre cop (longitud maxima 50 caracters): ", 50)
    fileWriter.writeChars(cognoms.padEnd(50, ' '))

    println("Digues el dia de naixement de l'usuari: ")
    fileWriter.writeInt(intSize(2, "Prova un altre cop: "))
    println("Digues el mes de naixement de l'usuari: ")
    fileWriter.writeInt(intSize(2, "Prova un altre cop: "))
    println("Digues el any de naixement de l'usuari: ")
    fileWriter.writeInt(intSize(4, "Prova un altre cop: "))

    println("Digues l'adreça postal de l'usuari: ")
    val adrecaPostal = stringLine("Prova un altre cop (longitud maxima 5 caracters): ", 5)
    fileWriter.writeChars(adrecaPostal.padEnd(5, ' '))

    println("Digues el e-mail de l'usuari: ")
    val email = stringLine("Prova un altre cop (longitud maxima 50 caracters): ", 50)
    fileWriter.writeChars(email.padEnd(50, ' '))

    println("Digues si l'usuari es vip: ")
    fileWriter.writeBoolean(boolean("Prova un altre cop: "))

    mapaCodisAPosicions[codi] = posicioInicial
    fileWriter.close()
    actualizarArchiuMapa(codi, posicioInicial, fileMap)
}

fun opcio2(file: File) {
    println("**Consulta d’un client per posició**")

    println("Digues la posició del client: ")
    val pos = int("Prova un altre cop: ")

    val midaRegistre = 267

    DataInputStream(FileInputStream(file)).use { fileReader ->
        try {
            val desplacament = (pos - 1) * midaRegistre.toLong()

            if (desplacament < file.length()) {
                fileReader.skip(desplacament)

                val c = readClient(fileReader)
                println("Client trobat:")
                println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
            } else {
                println("La posció no existeix")
            }
        } catch (e: IOException) {
            println("Error al llegir l'arxiu: ${e.message}")
        }
    }

}

fun opcio3(file: File) {
    println("**Consulta d’un client per codi**")
    busquedaPerCodi(file)
}

fun opcio4(file: File) {
    println("**Modificar un client**")
    //no funciona be
    println("Digues el codi del client: ")
    val codiBuscat = int("Prova un altre cop: ")
    busquedaPerCodi(file, codiBuscat)
    println("Quin camp vols modificar?")
    println("1. Nom")
    println("2. Cognoms")
    println("3. Data naixement")
    println("4. Adreça postal")
    println("5. E-mail")
    println("6. Es VIP")
    val opcio = intRange(1..6, "Prova un altre cop: ")
    when (opcio) {
        1 -> nom(file, codiBuscat)
        2 -> cognoms(file, codiBuscat)
        3 -> data(file, codiBuscat)
        4 -> adrecaPostal(file, codiBuscat)
        5 -> eMail(file, codiBuscat)
        6 -> vip(file, codiBuscat)
        else -> println("Opció no vàlida")
    }
}

fun opcio5(file: File, fileMap: File) {
    println("**Esborrar un client**")
    println("Digues el codi del client: ")
    val codiBuscat = int("Prova un altre cop: ")
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "rw").use { raf ->
            raf.seek(posicio)
            val client = readClient(DataInputStream(FileInputStream(raf.fd)))
            println("Codi: ${client.getCodi()}, Nom: ${client.getNom()}, Cognom: ${client.getCognoms()}, Data naixement: ${client.getDia()}/${client.getMes()}/${client.getAny()}, Adreça postal: ${client.getAdrecaPostal()}, E-mail: ${client.getEmail()}, Es vip: ${client.isEsVIP()}")
            mapaCodisAPosicions.remove(codiBuscat)
            raf.seek(posicio)
            raf.writeBoolean(false)
            println("Client esborrat correctament")
            actualizarMapaEliminar(codiBuscat, posicio, 267)
            actualizarArchiuMapaEliminar(fileMap)
        }
    } else {
        println("No s'ha trobat cap client amb el codi $codiBuscat.")
    }

}

fun opcio6(file: File) {
    println("**Llistat de tots els clients**")
    val codisOrdenats = mapaCodisAPosicions.keys.sortedWith(compareBy { it })
    RandomAccessFile(file, "r").use { raf ->
        for (codi in codisOrdenats) {
            val posicio = mapaCodisAPosicions[codi]
            if (posicio != null) {
                raf.seek(posicio)
                val client = readClient(DataInputStream(FileInputStream(raf.fd)))
                println("Codi: ${client.getCodi()}, Nom: ${client.getNom()}, Cognom: ${client.getCognoms()}, Data naixement: ${client.getDia()}/${client.getMes()}/${client.getAny()}, Adreça postal: ${client.getAdrecaPostal()}, E-mail: ${client.getEmail()}, Es vip: ${client.isEsVIP()}")
            }
        }
    }
}

