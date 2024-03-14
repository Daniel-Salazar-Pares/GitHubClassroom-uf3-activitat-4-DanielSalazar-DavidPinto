package utilities


import java.util.*

val sc = Scanner(System.`in`)
fun intRange(rango:IntRange,m1:String) :Int {
    var input:Int
    do {
        while (!sc.hasNextInt()) {
            println(m1)
            sc.nextLine()
        }
        input = sc.nextInt()
        sc.nextLine()
    }while (input !in rango)
    return input
}

fun stringSize(size:Int ,m1:String):String {
    println(m1)
    var input:String
    do {
        while (!sc.hasNextLine()) {
            println(m1)
            sc.next()
        }
        input = sc.nextLine()
    }while (input.length > size)
    input += " ".repeat(size - input.length)
    return input
}
fun stringLine(m1:String):String {
    while (!sc.hasNextLine()) {
        println(m1)
    }
    return sc.nextLine()
}

fun stringLine(m1:String, size: Int):String {
    var input: String
    do {
        while (!sc.hasNextLine()) {
            println(m1)
            sc.next()
        }
        input = sc.nextLine()
        if (input.length > size) {
            println(m1)
        }
    } while (input.length > size)
    return input.padEnd(size, ' ')
}

fun intSize(size:Int ,m1:String):Int {
    var input:Int
    do {
        while (!sc.hasNextInt()) {
            println(m1)
            sc.nextLine()
        }
        input = sc.nextInt()
        sc.nextLine()
        if (input.toString().length > size) {
            println(m1)
        }
    }while (input.toString().length > size)
    return input
}

fun int(m1:String) :Int {
    while(!sc.hasNextInt()) {
        sc.nextLine()
        println(m1)
    }
    val input = sc.nextInt()
    sc.nextLine()

    return input
}

fun boolean(m1:String):Boolean {
    while(!sc.hasNextBoolean()) {
        sc.nextLine()
        println(m1)
    }
    val input = sc.nextBoolean()
    sc.nextLine()

    return input
}