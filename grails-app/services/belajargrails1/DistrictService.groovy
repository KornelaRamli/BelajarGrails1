package belajargrails1

import grails.transaction.Transactional

@Transactional
class DistrictService {

    def serviceMethod() {

    }
    def validateCreate(params){
        println "Masuk distric service validate create " +params
        Map result = new HashMap()
        if(null==params.nama||"".equals(params.nama)){
            result.hasil="N"
            result.message="Nama kecamatan harus diisi"
            return result
        }
        if(null==params.latlng||"".equals(params.latlng)){
            result.hasil="N"
            result.message="Posisi kecamatan harus dipilih"
            return result
        }
        if(null==params.city||"".equals(params.city)){
            result.hasil="N"
            result.message="Kota kecamatan harus diisi"
            return result
        }else{
            def c = City.findById(Long.parseLong(params.city))
            if(!c){
                result.hasil="N"
                result.message="Kota tidak terdapat dengan id "+params.city
                return result
            }
        }
        result.hasil="Y"
        return result
    }
    def createDistrict(params){
        println "Masuk distric service create"
        Map result = new HashMap()
        District d = new District()
        City c = City.get(params.city)
        d.nama = params.nama
        d.deskripsi=params.deskripsi
        d.city=c
        d.latlng=params.latlng
        d.save(failOnError:true)

        result.hasil="Y"
        result.message="Berhasil simpan kecamatan"
        return result
    }

    def validateUpdate(params){
        println"Masuk district service validate update"
        Map result = new HashMap()
        if (null==params.id||"".equals(params.id)){
            result.hasil="N"
            result.message="ID kecamatan harus diisi"
            return result
        }else{
            def d = District.findById(Long.parseLong(params.id))
            if(!d){
                result.hasil="N"
                result.message="District tidak terdapat dengan id "+params.id
                return result
            }
        }
        if(null==params.latlng||"".equals(params.latlng)){
            result.hasil="N"
            result.message="Posisi kecamatan harus dipilih"
            return result
        }
        if (null==params.nama||"".equals(params.nama)){
            result.hasil="N"
            result.message="Nama kecamatan harus diisi"
            return result
        }
        if(null==params.city||"".equals(params.city)){
            result.hasil="N"
            result.message="Kota kecamatan harus diisi"
            return result
        }else{
            def c = City.findById(Long.parseLong(params.city))
            if(!c){
                result.hasil="N"
                result.message="Kota tidak terdapat dengan id "+params.city
                return result
            }
        }
        result.hasil="Y"
        return result
    }

    def updateDistric(params){
        println "Masuk distric service update"
        Map result = new HashMap()
        District d = District.get(params.id)
        City c = City.get(params.city)
        d.nama = params.nama
        d.deskripsi=params.deskripsi
        d.city=c
        d.latlng=params.latlng
        d.save(failOnError:true)

        result.hasil="Y"
        result.message="Berhasil update kecamatan "+params.nama
        return result
    }

    def validateDelete(params){
        println"Masuk district service validate delete"
        Map result = new HashMap()
        if (null==params.id||"".equals(params.id)){
            result.hasil="N"
            result.message="ID kecamatan harus diisi"
            return result
        }else{
            def d = District.findById(Long.parseLong(params.id))
            if(!d){
                result.hasil="N"
                result.message="Kecamatan tidak terdapat dengan id "+params.id
                return result
            }else{
                def district = District.get(params.id)
                def vil = Village.findByDistrict(district)
                if (vil){
                    result.hasil="N"
                    result.message="Tidak dapat delete kecamatan, terdapat kelurahan terdaftar pada kecamatan"
                    return result
                }
            }
        }
        result.hasil="Y"
        return result
    }

    def deleteDistrict(params){
        println"Masuk district service delete"
        Map result = new HashMap()
        District d = District.get(params.id)
        String nama = d.nama
        d.delete()
        result.hasil="Y"
        result.message="Berhasil delete kecamatan "+nama
        return result
    }

    def listDistrict(){
        println"Masuk district service listdistrict"
        Map result = new HashMap()
        Map tampung
        ArrayList arr = new ArrayList()
        def d = District.executeQuery("SELECT new map (d.id as id, d.nama as nama, d.deskripsi as desk, d.city as city, d.latlng as latlng) FROM District d")
        d.each { di ->
            tampung = new HashMap()
            tampung.id = di.id
            tampung.nama = di.nama
            tampung.deskripsi = di.desk
            tampung.city=di.city
            tampung.latlng=di.latlng
            arr.add(tampung)
        }


        result.hasil="Y"
        result.isi = arr
        result.message="Berhasil tampilkan list kecamatan"
        return result
    }

    def getDistrict(params){
        println"Masuk district service get district "+params
        Map result = new HashMap()
        def d =District.executeQuery("SELECT new map (d.id as id, d.nama as nama, d.deskripsi as deskripsi, d.city as city, d.latlng as latlng) FROM District d WHERE d.id = ?",[Long.parseLong(params.id)])[0]
        def district = District.get(params.id)
        def list = Village.findAllByDistrict(district)

        result.hasil="Y"
        result.isi = d
        result.list = list
        result.message="Berhasil tampilkan kecamatan"
        return result
    }

    def searchDistrict(params){
        Map result = new HashMap()
        Map tampung
        ArrayList arr = new ArrayList()
        def di = District.findAllByNama(params.nama)
        di.each { ci ->
            tampung = new HashMap()
            tampung.id = ci.id
            tampung.nama = ci.nama
            tampung.deskripsi = ci.deskripsi
            tampung.city=ci.city
            arr.add(tampung)
        }
        if (di){
            result.hasil="Y"
            result.isi = arr
            result.message="Berhasil tampilkan list district"
            return result
        }else {
            result.hasil="N"
            result.message="Tidak ditemukan district dengan nama "+params.nama
            return result
        }

    }
}
