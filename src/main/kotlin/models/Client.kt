package models

class Client {


    private var codi:Int
    private var nom:String
    private var cognoms:String
    private var dia:Int
    private var mes:Int
    private var any:Int
    private var adrecaPostal:String
    private var email:String
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
    fun setCodi(codi:Int) {
        this.codi = codi
    }

    fun getNom(): String {
        return nom
    }
    fun setNom(nom:String) {
        this.nom = nom
    }

    fun getCognoms(): String {
        return cognoms
    }
    fun setCognoms(cognoms:String) {
        this.cognoms = cognoms
    }

    fun getDia(): Int {
        return dia
    }
    fun setDia(dia:Int) {
        this.dia = dia
    }
    fun getMes(): Int {
        return mes
    }
    fun setMes(mes:Int) {
        this.mes = mes
    }
    fun getAny(): Int {
        return any
    }
    fun setAny(any:Int) {
        this.any = any
    }
    fun getAdrecaPostal(): String {
        return adrecaPostal
    }
    fun setAdrecaPostal(adrecaPostal:String) {
        this.adrecaPostal = adrecaPostal
    }
    fun getEmail(): String {
        return email
    }
    fun setEmail(email:String) {
        this.email = email
    }
    fun isEsVIP(): Boolean {
        return esVIP
    }
    fun setEsVIP(esVIP:Boolean) {
        this.esVIP = esVIP
    }

}
