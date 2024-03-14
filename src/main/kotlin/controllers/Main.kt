package org.example.controllers

import controllers.*
import utilities.*
import java.io.*

var mapaCodisAPosicions = mutableMapOf<Int, Long>()
fun main() {
    if (!File("./clients.dat").exists()) {
        val fileWriter = DataOutputStream(FileOutputStream(File("./clients.dat")))
        fileWriter.close()
    }

    if (!File("./mapaCodisAPosicions.txt").exists()) {
        val fileWriter = DataOutputStream(FileOutputStream(File("./mapaCodisAPosicions.txt")))
        fileWriter.close()
    }
    mapaCodisAPosicions = cargarMapaDesDeArchiu(File("./mapaCodisAPosicions.txt"))
    gestioDeClients(File("./clients.dat"), File("./mapaCodisAPosicions.txt"))
}


fun gestioDeClients(file:File, fileMap:File) {
    do {
        val opcion = menu()
        when (opcion) {
            1 -> opcio1(file, fileMap)
            2 -> opcio2(file)
            3 -> opcio3(file)
            4 -> opcio4(file)
            5 -> opcio5(file, fileMap)
            6 -> opcio6(file)
        }
    } while (opcion != 7)
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


