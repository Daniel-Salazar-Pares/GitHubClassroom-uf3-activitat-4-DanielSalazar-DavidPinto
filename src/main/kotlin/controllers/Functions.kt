package controllers

import models.*
import org.example.controllers.mapaCodisAPosicions
import utilities.boolean
import utilities.int
import utilities.intSize
import utilities.stringLine
import java.io.*
fun cargarMapaDesDeArchiu(fileMap: File): MutableMap<Int, Long> {
    val mapa = mutableMapOf<Int, Long>()
    fileMap.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val (clave, valor) = line.split(",").map { it.trim() }
            mapa[clave.toInt()] = valor.toLong()
        }
    }
    return mapa
}

fun actualizarArchiuMapa(codi: Int, posicio: Long, fileMap: File) {
    PrintWriter(FileOutputStream(fileMap, true)).use { pw ->
        pw.println("$codi,$posicio")
    }
}

fun readClient(fileReader: DataInputStream): Client {
    val codi = fileReader.readInt()

    val nom = CharArray(20) { fileReader.readChar() }.concatToString().trimEnd()
    val cognom = CharArray(50) { fileReader.readChar() }.concatToString().trimEnd()

    val dia = fileReader.readInt()
    val mes = fileReader.readInt()
    val any = fileReader.readInt()

    val adrecaPostal = CharArray(5) { fileReader.readChar() }.concatToString().trimEnd()
    val email = CharArray(50) { fileReader.readChar() }.concatToString().trimEnd()

    val esVIP = fileReader.readBoolean()

    return Client(codi, nom, cognom, dia, mes, any, adrecaPostal, email, esVIP)
}

fun busquedaPerCodi (file: File) {
    println("Digues el codi del client: ")
    val codiBuscat = int("Prova un altre cop: ")
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "r").use { fileReader ->
            fileReader.seek(posicio)
            val c = readClient(DataInputStream(FileInputStream(fileReader.fd)))
            println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
        }
    } else {
        println("No s'ha trobat cap client amb el codi $codiBuscat.")
    }
}
fun busquedaPerCodi (file: File, codiBuscat: Int) {
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "r").use { fileReader ->
            fileReader.seek(posicio)
            val c = readClient(DataInputStream(FileInputStream(fileReader.fd)))
            println("Codi: ${c.getCodi()}, Nom: ${c.getNom()}, Cognom: ${c.getCognoms()}, Data naixement: ${c.getDia()}/${c.getMes()}/${c.getAny()}, Adreça postal: ${c.getAdrecaPostal()}, E-mail: ${c.getEmail()}, Es vip: ${c.isEsVIP()}")
        }
    } else {
        println("No s'ha trobat cap client amb el codi $codiBuscat.")
    }
}
fun vip(file: File, codiBuscat: Int) {
    println("Digues si l'usuari es vip: ")
    val esVIP = boolean("Prova un altre cop: ")
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "rw").use { raf ->
            raf.seek(posicio + 264)
            raf.writeBoolean(esVIP)
        }
    }
}

fun eMail(file: File, codiBuscat: Int) {
    println("Digues el nou e-mail: ")
    val email = stringLine("Prova un altre cop (longitud maxima 50 caracters): ", 50)
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "rw").use { raf ->
            raf.seek(posicio + 214)
            for (char in email) {
                raf.writeChar(char.code)
            }
        }
    }
}

fun adrecaPostal(file: File, codiBuscat: Int) {
    println("Digues la nova adreça postal: ")
    val adrecaPostal = stringLine("Prova un altre cop (longitud maxima 5 caracters): ", 5)
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "rw").use { raf ->
            raf.seek(posicio + 164)
            raf.writeChars(adrecaPostal.padEnd(5, ' '))
        }
    }
}

fun data(file: File, codiBuscat: Int) {
    println("Digues el nou dia de naixement: ")
    val dia = intSize(2, "Prova un altre cop: ")
    println("Digues el nou mes de naixement: ")
    val mes = intSize(2, "Prova un altre cop: ")
    println("Digues el nou any de naixement: ")
    val any = intSize(4, "Prova un altre cop: ")
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "rw").use { raf ->
            raf.seek(posicio + 84)
            raf.writeInt(dia)
            raf.writeInt(mes)
            raf.writeInt(any)
        }
    }
}

fun cognoms(file: File, codiBuscat: Int) {
    println("Digues els nous cognoms: ")
    val cognoms = stringLine("Prova un altre cop (longitud maxima 50 caracters): ", 50)
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "rw").use { raf ->
            raf.seek(posicio + 24)
            raf.writeChars(cognoms.padEnd(50, ' '))
        }
    }
}

fun nom(file: File, codiBuscat: Int) {
    println("Digues el nou nom: ")
    val nom = stringLine("Prova un altre cop (longitud maxima 20 caracters): ", 20)
    val posicio = mapaCodisAPosicions[codiBuscat]
    if (posicio != null) {
        RandomAccessFile(file, "rw").use { raf ->
            raf.seek(posicio)
            raf.skipBytes(4)
            raf.writeChars(nom.padEnd(20, ' '))
        }
    }
}
fun actualizarMapaEliminar(codi: Int, posicioClientEliminat: Long, midaRegistre: Long) {
    mapaCodisAPosicions.remove(codi)
    mapaCodisAPosicions = mapaCodisAPosicions.mapValues { (codi, pos) ->
        if (pos > posicioClientEliminat) {
            pos - midaRegistre
        } else {
            pos
        }
    }.toMutableMap()
}
fun actualizarArchiuMapaEliminar(fileMap: File) {
    PrintWriter(FileOutputStream(fileMap)).use { pw ->
        mapaCodisAPosicions.forEach { (codi, posicio) ->
            pw.println("$codi,$posicio")
        }
    }
}