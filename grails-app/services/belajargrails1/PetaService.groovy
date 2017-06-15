package belajargrails1

import grails.transaction.Transactional

@Transactional
class PetaService {

    def serviceMethod() {

    }

    def getMarker(){
        Map result = new HashMap()
        Map tampung

        def citys = City.executeQuery("SELECT new map (c.id as id, c.nama as nama, c.deskripsi as desk, c.latlng as latlng) FROM City c")
        def districts = District.executeQuery("SELECT new map (d.id as id, d.nama as nama, d.deskripsi as desk, d.latlng as latlng) FROM District d")

        result.citys = citys
        result.districts = districts
//        println "berhasil ambil data"+result
        return result
    }
}
