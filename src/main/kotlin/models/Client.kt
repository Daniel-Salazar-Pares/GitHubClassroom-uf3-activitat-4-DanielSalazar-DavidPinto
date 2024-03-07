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

    override fun toString():String {
        return "$codi$nom$cognoms$dia$mes$any$adrecaPostal$email$esVIP"
    }
}