package org.example.controllers

import models.Client
import utilities.*
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.File

fun main() {
    ex1(File("./numeros.dat"))
    ex1(File("./clients.dat"))
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

fun opcio1(file: File) {
    val fileWriter = DataOutputStream(FileOutputStream(file))

    println("**Alta d’un client**")
    println("Digues el codi de l'usuari: ")
    fileWriter.writeInt(int("Prova un altre cop: "))

    println("Digues el nom de l'usuari: ")
    fileWriter.writeChars(stringLine("Prova un altre cop: "))

    println("Digues el cognoms de l'usuari: ")
    fileWriter.writeChars(stringLine("Prova un altre cop: "))

    println("Digues la data de naixement de l'usuari en format DDMMAAAA: ")
    fileWriter.writeInt(intRange(8..8,"Prova un altre cop: "))


    println("Digues l'adreça postal de l'usuari: ")
    fileWriter.writeChars(stringLine("Prova un altre cop: "))

    println("Digues el e-mail de l'usuari: ")
    fileWriter.writeChars(stringLine("Prova un altre cop: "))

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
