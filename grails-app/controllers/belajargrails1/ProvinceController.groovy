package belajargrails1

import com.gs.collections.impl.bimap.mutable.HashBiMap
import grails.converters.JSON

class ProvinceController {

    def provinceService
    def index() { }


    def create(params){
        if(request.method=='GET'){
            return [province: new Province()]
        }
        println "Masuk province controller create "+params
        Map result = new HashMap()
        println "test masuk validasi"
        result = provinceService.validateCreate(params)
        if("Y".equals(result.hasil)){
            result = provinceService.createProvince(params)
            redirect(action:"show",params:result)
        }else{
            println result
            return[province: params,result:result]
        }
        println result
        render(contentType: 'application/json', text:"${result as JSON}")

    }

    def update(params){
        if(request.method=='GET'){
            return[province: params]
        }
        println "masuk province controller update "+params
        Map result = new HashMap()
        result= provinceService.validateUpdate(params)
        if("Y".equals(result.hasil)){
            result = provinceService.update(params)
            redirect(action: "show",params: result)
        }else{
            println result
            return[province: params,result:result]
        }

        render(contentType: 'application/json', text:"${result as JSON}")
    }

    def delete(params){
        println "Masuk province controller delete"+params
        Map result = new HashMap()
        result = provinceService.validateDelete(params)
        if("Y".equals(result.hasil)){
            result = provinceService.delete(params)
            redirect(action: "show",params: result)
        }else{
            Map batalDelet = new HashMap()
            batalDelet = provinceService.getProvince(params)
            println "isi "+batalDelet.isi
            println "message 3 : "+result
            render(view: "showDetail", model: [province: batalDelet.isi,city:batalDelet.list,result: result])
        }
        //render(contentType:'application/json', text:"${result as JSON}")
    }

    def show(params){
        println "Masuk province controller show"
        Map result = new HashMap()
        result = provinceService.listProvince()
        [province:result.isi, result: params]
        //render(contentType: 'application/json', text:"${result as JSON}")
    }

    def showDetail(params){
        println "Masuk province controller showdetail "+params
        Map result = new HashMap()
        result = provinceService.getProvince(params)
        println result.list
        [province: result.isi,city:result.list]
    }

    def search(params){
        println "Masuk province controller search "+params
        Map result = new HashMap()
        result = provinceService.searchProvince(params)
        if ("Y".equals(result.hasil)) {
            render(view: "show", model: [province: result.isi, result: result])
        }else{
            redirect(action: "show",params: result)
        }

    }


}
