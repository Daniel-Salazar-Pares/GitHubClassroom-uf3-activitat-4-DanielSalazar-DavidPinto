package org.example.controllers

import controllers.*
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


