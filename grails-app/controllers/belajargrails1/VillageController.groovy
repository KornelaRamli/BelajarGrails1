package belajargrails1

import grails.converters.JSON

class VillageController {

    def villageService
    def index() { }

    def create(params){
        if(request.method=='GET'){
            return[village: new Village(), districtlist: District.getAll()]
        }
        println "Masuk controller village create"
        Map result = new HashMap()
        result = villageService.validateCreate(params)
        if ("Y".equals(result.hasil)){
            result = villageService.createVillage(params)
            println result
            redirect (action:"show",params:result)
        }else{
            println result
            return[village: params,districtlist: District.getAll(),result:result]
        }
        println result

        //render (contentType: 'application/json', text: "${result as JSON}")
    }

    def update(params){
        if(request.method=='GET'){
            return[village: params, districtlist: District.getAll()]
        }
        println "Masuk controller village update"
        Map result = new HashMap()
        result = villageService.validateUpdate(params)
        if ("Y".equals(result.hasil)){
            result = villageService.updateVillage(params)
            redirect(action: "show",params: result)
        }else{
            println result
            return[village: params,districtlist: District.getAll(),result:result]
        }

        render (contentType: 'application/json', text: "${result as JSON}")
    }

    def delete(params){
        println "Masuk controller village update"
        Map result = new HashMap()
        result = villageService.validateDelete(params)
        if ("Y".equals(result.hasil)){
            result = villageService.deleteVillage(params)
            redirect(action: "show",params: result)
        }else{
            Map batalDelet = new HashMap()
            batalDelet = villageService.getVillage(params)
            println "isi "+batalDelet.isi
            println "message 3 : "+result
            render(view: "showDetail", model: [village:batalDelet.isi,result: result])
        }
        //println result

        //render (contentType: 'application/json', text: "${result as JSON}")
    }

    def show(){
        println"Masuk controller village show"
        Map result = new HashMap()
        result = villageService.listVillage()
        [village:result.isi]
        //render(contentType: 'application/json', text:"${result as JSON}")
    }

    def showDetail(params){
        println "Masuk Villege controller showlist "+params
        Map result = new HashMap()
        result = villageService.getVillage(params)
        [village:result.isi]
    }

    def search(params){
        println "Masuk Village controller search "+params
        Map result = new HashMap()
        result = villageService.searchVillage(params)
        if ("Y".equals(result.hasil)) {
            render(view: "show", model: [village: result.isi, result: result])
        }else{
            redirect(action: "show",params: result)
        }

    }

}
