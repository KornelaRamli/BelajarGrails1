package belajargrails1

import com.gs.collections.impl.bimap.mutable.HashBiMap
import grails.transaction.Transactional

@Transactional
class ProvinceService {

    def serviceMethod() {

    }

    def validateCreate(params){
        Map result = new HashMap()
        println "Masuk validasi province create"
        if(null==params.nama||"".equals(params.nama)){
            result.hasil="N"
            result.message="Params nama harus diisi"
            return result
        }
        if(null==params.pulau||"".equals(params.pulau)){
            result.hasil="N"
            result.message="Parama pulau harus diisi"
            return result
        }
        if(null==params.deskripsi||"".equals(params.deskripsi)){
            result.hasil="N"
            result.message="Params deskripsi harus diisi"
            return result
        }
        if(params.deskripsi.length()>225){
            result.hasil="N"
            result.message="Deskripsi tidak dapat melebihi 225 huruf"
            return  result
        }
        result.hasil="Y"
        return result
    }

    def createProvince(params){
        Map result = new HashMap()
        Province p = new Province()
        p.nama=params.nama
        p.pulau=params.pulau
        p.deskripsi=params.deskripsi
        p.save(failOnError:true)
        result.hasil="Y"
        result.messsage="Berhasil simpan province"
        return result
    }

    def validateUpdate(params){
        Map result = new HashMap()
        if(null==params.id||"".equals(params.id)){
            result.hasil="N"
            result.message="Params id harus diisi"
            return result
        }else{
            def p = Province.findById(Long.parseLong(params.id))
            if(!p){
                result.hasil="N"
                result.message="Provinsi dengan ID "+params.id+" tidak ditemukan"
                return result
            }
        }
        if(null==params.nama||"".equals((params.nama))){
            result.hasil="N"
            result.message="Param nama harus diisi"
            return result
        }
        if(null==params.pulau||"".equals(params.pulau)){
            result.hasil="N"
            result.message="param pulau harus diisi"
            return result
        }
        if(null==params.deskripsi||"".equals(params.deskripsi)){
            result.hasil="N"
            result.message="params deskripsi harus diisi"
            return result
        }
        if(params.deskripsi.length()>225){
            result.hasil="N"
            result.message ="deskripsi tidak dapat melebihi 225 huruf"
            return result
        }
        result.hasil="Y"
        return result
    }

    def update(params){
        Map result = new HashMap()
        println"masuk province service update"
        Province p = Province.get(params.id)
        p.nama=params.nama
        p.pulau=params.pulau
        p.deskripsi=params.deskripsi
        p.save(failOnError:true)
        result.hasil="Y"
        result.message="berhasil update province"
        return result

    }

    def validateDelete(params){
        Map result = new HashMap()
        println "====================================================="
        println"Mulai province service validasi delete" + params
        if(null==params.id||"".equals(params.id)){
            result.hasil="N"
            result.message="params id harus diisi"
            return result
        }else{
            def p = Province.findById(Long.parseLong(params.id))
            println "ini hasil p "+p
            if(!p){
                result.hasil="N"
                result.message="Tidak ditemukan province dengan id "+params.id
                return result
            }else{
                def pr = Province.get(params.id)
                def c = City.findByProvinsi(pr)
                if(c){
                    result.hasil="N"
                    result.message="Tidak dapat delete province, terdapat kota terdaftar pada provinsi"
                    return result
                }
            }
        }
        result.hasil="Y"
        return result
    }

    def delete(params){
        Map result = new HashMap()
        println "Mulai province service delete"
        Province p = Province.get(params.id)
        p.delete()
        result.hasil="Y"
        result.message="Provice berhasil delete"
        return result
    }

    def listProvince(){
        Map result = new HashMap()
        println "Mulai show list province service"
        Map temp
        ArrayList provi = new ArrayList()
        def p = Province.executeQuery("SELECT new map (p.id as id,p.nama as nama, p.pulau as pulau, p.deskripsi as desk) FROM Province p")
        p.each { pro ->
            temp = new HashMap()
            temp.id=pro.id
            temp.nama = pro.nama
            temp.pulau = pro.pulau
            temp.deskripsi = pro.desk
            provi.add(temp)
        }

        result.hasil="Y"
        result.isi=provi
        result.message="Berhasil ambil list province"
        return result
    }

    def getProvince(params){
        println"Masuk province service get province "+params
        Map result = new HashMap()
        def p =Province.executeQuery("SELECT new map (p.id as id, p.nama as nama, p.deskripsi as deskripsi, p.pulau as pulau) FROM Province p WHERE p.id = ?",[Long.parseLong(params.id)])[0]
        def province = Province.get(params.id)
        def list = City.findAllByProvinsi(province)

        result.hasil="Y"
        result.isi = p
        result.list = list
        result.message="Berhasil tampilkan provinsi"
        return result
    }

    def searchProvince(params){
        Map result = new HashMap()
        Map tampung
        ArrayList arr = new ArrayList()
        def p = Province.findAllByNama(params.nama)
        p.each { po ->
            tampung = new HashMap()
            tampung.id = po.id
            tampung.nama = po.nama
            tampung.deskripsi = po.deskripsi
            tampung.pulau=po.pulau
            arr.add(tampung)
        }
        if (p){
            result.hasil="Y"
            result.isi = arr
            result.message="Berhasil tampilkan list province"
            return result
        }else {
            result.hasil="N"
            result.message="Tidak ditemukan province dengan nama "+params.nama
            return result
        }

    }
}
