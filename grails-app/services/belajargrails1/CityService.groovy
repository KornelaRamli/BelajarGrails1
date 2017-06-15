package belajargrails1

import com.gs.collections.impl.bimap.mutable.HashBiMap
import grails.transaction.Transactional

@Transactional
class CityService {

    def serviceMethod() {

    }

    def validasiCreate(params){
        Map result = new HashMap()
        result.args=["kota"]
        if (null==params.nama || "".equals(params.nama)){
            result.hasil="N";
            result.message="nama.empty"

            return result
        }
        if (null==params.deskripsi||"".equals(params.deskripsi)){
            result.hasil="N"
            result.message="desc.empty"
            return result
        }
        if (null==params.latlng||"".equals(params.latlng)){
            result.hasil="N"
            result.message="latlng.empty"
            return result
        }
        if(params.deskripsi.length()>225){
            result.hasil="N"
            result.message="Deskripsi tidak dapat melebihi 225 huruf"
            return result
        }
        if(null==params.provinsi||"".equals(params.provinsi)){
            result.hasil="N"
            result.message="provinsi kota harus diisi"
            return result
        }else{
            def p = Province.findById(Long.parseLong(params.provinsi))
            if(!p){
                result.hasil="N"
                result.message="Provinsi tidak ditemukan dengan id "+params.provinsi
                return result
            }

        }
        result.hasil="Y"
        return  result
    }

    def createCity(params){
        Map result = new HashMap()
        City c = new City()
        Province p = Province.get(params.provinsi)
        c.nama=params.nama
        c.deskripsi=params.deskripsi
        c.provinsi= p
        c.latlng= params.latlng

        c.save(failOnError:true)
        result.hasil="Y"
        result.message="berhasil buat city baru"
        return result

    }

    def validasiUpdate(params){
        Map result = new HashMap()
        result.args=["kota"]
        if(null==params.id||"".equals(params.id)){
            result.hasil="N"
            result.message="Params id tidak boleh kosong"
            return result
        }else{
            def city = City.findById(Long.parseLong(params.id))
            if(!city){
                result.hasil="N"
                result.message="City dengan ID " +params.id+" tidak ditemukan"
                return result
            }
        }
        if(null==params.nama||"".equals(params.nama)){
            result.hasil="N"
            result.message="nama.empty"
            return result
        }
        if(null==params.deskripsi||"".equals(params.deskripsi)){
            result.hasil="N"
            result.message="desc.empty"
            return result
        }
        if (null==params.latlng||"".equals(params.latlng)){
            result.hasil="N"
            result.message="latlng.empty"
            return result
        }
        if(params.deskripsi.length()>225){
            result.hasil="N"
            result.message="Deskripsi tidak dapat melebihi 225 huruf"
            return result
        }
        if(null==params.provinsi||"".equals(params.provinsi)){
            result.hasil="N"
            result.message="provinsi kota harus diisi"
            return result
        }else{
            def p = Province.findById(Long.parseLong(params.provinsi))
            if(!p){
                result.hasil="N"
                result.message="Provinsi tidak ditemukan dengan id "+params.provinsi
                return result
            }
        }
        result.hasil="Y"
        println"data city update valid"
        return result

    }

    def updateCity(params){
        Map result = new HashMap()
        City c = City.get(params.id)
        Province p =Province.get(params.provinsi)
        c.nama=params.nama
        c.deskripsi = params.deskripsi
        c.provinsi = p
        c.latlng = params.latlng
        c.save(failOnError:true)
        result.hasil="Y"
        result.message="Berhasil update city "+c.nama
        return result

    }

    def validasiDelete(params){
        Map result = new HashMap()
        if (null==params.id||"".equals(params.id)){
            result.hasil ="N"
            result.message="Params ID harus diisi"
            return result
        }else{
            def c = City.findAllById(params.id)
            if(!c){
                result.hasil="N"
                result.message="Tidak ditemukan City dengan ID "+params.id
                return result
            }else{
                def city = City.get(params.id)
                def dis = District.findByCity(city)
                if (dis){
                    result.hasil="N"
                    result.message="Tidak dapat delete kota, terdapat kecamatan terdaftar pada kota"
                    return result
                }
            }
        }
        result.hasil="Y"
        return result
    }

    def deleteCity(params){
        Map result = new HashMap()
        City c = City.get(params.id)
        c.delete()
        result.hasil="Y"
        result.message="Berhasil delete city"
        return result
    }

    def listCity(){
        Map result = new HashMap()
        Map tampung
        ArrayList arr = new ArrayList()
        def c = City.executeQuery("SELECT new map (c.id as id, c.nama as nama, c.deskripsi as desk, c.provinsi as provinsi) FROM City c")
        int i =0
        c.each { ci ->
            tampung = new HashMap()
            tampung.id = ci.id
            tampung.nama = ci.nama
            tampung.deskripsi = ci.desk
            tampung.provinsi=ci.provinsi
            i++
            arr.add(tampung)
        }
        result.hasil="Y"
        result.isicount=i
        result.isi = arr
        result.message="Berhasil tampilkan list city"
        return result
    }

    def getCity(params){
        Map result = new HashMap()
        def c =City.executeQuery("SELECT new map (c.id as id, c.nama as nama, c.deskripsi as deskripsi, c.provinsi as provinsi, c.latlng as latlng) FROM City c WHERE c.id = ?",[Long.parseLong(params.id)])[0]
        def city = City.get(params.id)
        def listD = District.findAllByCity(city)

        result.hasil="Y"
        result.isi = c
        result.listD = listD
        result.message="Berhasil tampilkan city"
        return result
    }

    def searchCity(params){
        Map result = new HashMap()
        Map tampung
        ArrayList arr = new ArrayList()
        //def c = City.executeQuery("SELECT new map (c.id as id, c.nama as nama, c.deskripsi as desk, c.provinsi as provinsi) FROM City c WHERE c.nama like '%?%'",[params.nama])
        def c = City.findAllByNama(params.nama)
        int i =0
        c.each { ci ->
            tampung = new HashMap()
            tampung.id = ci.id
            tampung.nama = ci.nama
            tampung.deskripsi = ci.deskripsi
            tampung.provinsi=ci.provinsi
            i++
            arr.add(tampung)
        }
        if (c){
            result.hasil="Y"
            result.isicount = i
            result.isi = arr
            result.message="Berhasil tampilkan list city"
            return result
        }else {
            result.hasil="N"
            result.message="item.not.found"
            result.args=["kota"]
                    //"Tidak ditemukan city dengan nama "+params.nama
            return result
        }

    }

}
