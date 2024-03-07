package models

class Client {


    private val codi:Int
    private val nom:String
    private val cognoms:String
    private val dia:Int
    private val mes:Int
    private val any:Int
    private val adrecaPostal:String
    private val email:String
    private var esVIP: Boolean

    constructor(codi:Int,nom:String,cognoms:String,dia:Int,mes:Int,any:Int,adrecaPostal:String,email:String,esVIP:Boolean) {
        this.codi = codi
        this.nom = nom
        this.cognoms = cognoms
        this.dia = dia
        this.mes = mes
        this.any = any
        this.adrecaPostal = adrecaPostal
        this.email = email
        this.esVIP = esVIP
    }

    fun getCodi(): Int {
        return codi
    }

    fun getNom(): String {
        return nom
    }

    fun getCognoms(): String {
        return cognoms
    }

    fun getDia(): Int {
        return dia
    }

    fun getMes(): Int {
        return mes
    }

    fun getAny(): Int {
        return any
    }

    fun getAdrecaPostal(): String {
        return adrecaPostal
    }

    fun getEmail(): String {
        return email
    }

    fun isEsVIP(): Boolean {
        return esVIP
    }
}
