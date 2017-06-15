package belajargrails1

import grails.transaction.Transactional

@Transactional
class VillageService {

    def serviceMethod() {

    }

    def validateCreate(params){
        println "Masuk village service validate create " +params
        Map result = new HashMap()
        if(null==params.nama||"".equals(params.nama)){
            result.hasil="N"
            result.message="Nama kelurahan harus diisi"
            return result
        }
        if(null==params.district||"".equals(params.district)){
            result.hasil="N"
            result.message="Kecamatan kelurahan harus diisi"
            return result
        }else{
            def d = District.findById(Long.parseLong(params.district))
            if(!d){
                result.hasil="N"
                result.message="Kecamatan tidak terdapat dengan id "+params.district
                return result
            }
        }
        result.hasil="Y"
        return result
    }

    def createVillage(params){
        println "Masuk village service create"
        Map result = new HashMap()
        Village v = new Village()
        District d = District.get(params.district)
        v.nama = params.nama
        v.deskripsi=params.deskripsi
        v.district=d
        v.save(failOnError:true)

        result.hasil="Y"
        result.message="Berhasil simpan kelurahan"
        return result
    }

    def validateUpdate(params){
        println"Masuk village service validate update"
        Map result = new HashMap()
        if (null==params.id||"".equals(params.id)){
            result.hasil="N"
            result.message="ID kelurahan harus diisi"
            return result
        }else{
            def v = Village.findById(Long.parseLong(params.id))
            if(!v){
                result.hasil="N"
                result.message="Kelurahan tidak terdapat dengan id "+params.id
                return result
            }
        }
        if (null==params.nama||"".equals(params.nama)){
            result.hasil="N"
            result.message="Nama kelurahan harus diisi"
            return result
        }
        if(null==params.district||"".equals(params.district)){
            result.hasil="N"
            result.message="Kecamatan kelurahan harus diisi"
            return result
        }else{
            def d = District.findById(Long.parseLong(params.district))
            if(!d){
                result.hasil="N"
                result.message="Kecamatan tidak terdapat dengan id "+params.district
                return result
            }
        }
        result.hasil="Y"
        return result
    }
    def updateVillage(params){
        println "Masuk village service update"
        Map result = new HashMap()
        Village v = Village.get(params.id)
        District d = District.get(params.district)
        v.nama = params.nama
        v.deskripsi=params.deskripsi
        v.district=d
        v.save(failOnError:true)

        result.hasil="Y"
        result.message="Berhasil update kelurahan "+params.nama
        return result
    }

    def validateDelete(params){
        println"Masuk village service validate delete"
        Map result = new HashMap()
        if (null==params.id||"".equals(params.id)){
            result.hasil="N"
            result.message="ID kelurahan harus diisi"
            return result
        }else{
            def v = Village.findById(Long.parseLong(params.id))
            if(!v){
                result.hasil="N"
                result.message="Kelurahan tidak terdapat dengan id "+params.id
                return result
            }
        }
        result.hasil="Y"
        return result
    }
    def deleteVillage(params){
        println"Masuk village service delete"
        Map result = new HashMap()
        Village v = Village.get(params.id)
        String nama = v.nama
        v.delete()
        result.hasil="Y"
        result.message="Berhasil delete kelurahan "+nama
        return result
    }

    def listVillage(){
        println"Masuk village service listvillage"
        Map result = new HashMap()
        Map tampung
        ArrayList arr = new ArrayList()
        def v = Village.executeQuery("SELECT new map (v.id as id, v.nama as nama, v.deskripsi as desk, v.district as district) FROM Village v")
        v.each { vi ->
            tampung = new HashMap()
            tampung.id = vi.id
            tampung.nama = vi.nama
            tampung.deskripsi = vi.desk
            tampung.district=vi.district
            arr.add(tampung)
        }
        result.hasil="Y"
        result.isi = arr
        result.message="Berhasil tampilkan list kelurahan"
        return result
    }

    def getVillage(params){
        println"Masuk village service get village "+params
        Map result = new HashMap()
        def v =Village.executeQuery("SELECT new map (v.id as id, v.nama as nama, v.deskripsi as deskripsi, v.district as district) FROM Village v WHERE v.id = ?",[Long.parseLong(params.id)])[0]
        result.hasil="Y"
        result.isi = v
        result.message="Berhasil tampilkan kelurahan"
        return result
    }

    def searchVillage(params){
        Map result = new HashMap()
        Map tampung
        ArrayList arr = new ArrayList()
        def vi = Village.findAllByNama(params.nama)
        vi.each { ci ->
            tampung = new HashMap()
            tampung.id = ci.id
            tampung.nama = ci.nama
            tampung.deskripsi = ci.deskripsi
            tampung.district=ci.district
            arr.add(tampung)
        }
        if (vi){
            result.hasil="Y"
            result.isi = arr
            result.message="Berhasil tampilkan list kelurahan"
            return result
        }else {
            result.hasil="N"
            result.message="Tidak ditemukan kelurahan dengan nama "+params.nama
            return result
        }

    }

}
