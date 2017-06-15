package belajargrails1
import grails.converters.JSON

class DistrictController {

    def districtService
    def index() { }

    def create(params){
        if(request.method=='GET'){
            return[district: new District(), citylist: City.getAll()]
        }
        println "Masuk controller distric create"
        Map result = new HashMap()
        result = districtService.validateCreate(params)
        if ("Y".equals(result.hasil)){
            result = districtService.createDistrict(params)
            println result
            redirect (action:"show",params:result)
        }else{
            println result
            return[district: params,citylist: City.getAll(),result:result]
        }
        println result

        //render (contentType: 'application/json', text: "${result as JSON}")
    }

    def update(params){
        if(request.method=='GET'){
            return[district: params, citylist: City.getAll()]
        }
        println "Masuk controller distric update"
        Map result = new HashMap()
        result = districtService.validateUpdate(params)
        if ("Y".equals(result.hasil)){
            result = districtService.updateDistric(params)
            redirect(action: "show",params: result)
        }else{
            println result
            return[district: params,citylist: City.getAll(),result:result]
        }
        println result

        //render (contentType: 'application/json', text: "${result as JSON}")
    }

    def delete(params){
        println "Masuk controller distric delete"
        Map result = new HashMap()
        result = districtService.validateDelete(params)
        if ("Y".equals(result.hasil)){
            result = districtService.deleteDistrict(params)
            redirect(action: "show",params: result)
        }else{
            Map batalDelet = new HashMap()
            batalDelet = districtService.getDistrict(params)
            println "isi "+batalDelet.isi
            println "message 3 : "+result
            render(view: "showDetail", model: [district:batalDelet.isi,village:batalDelet.list,result: result])
        }
        println result

        //render (contentType: 'application/json', text: "${result as JSON}")
    }

    def show(){
        println"Masuk controller distric show"
        Map result = new HashMap()
        result = districtService.listDistrict()
        [district:result.isi,result:result]

        //render(contentType: 'application/json', text:"${result as JSON}")
    }

    def showDetail(params){
        println "Masuk district controller showlist "+params
        Map result = new HashMap()
        result = districtService.getDistrict(params)
        println result.list
        [district:result.isi,village:result.list]
    }

    def search(params){
        println "Masuk district controller search "+params
        Map result = new HashMap()
        result = districtService.searchDistrict(params)
        if ("Y".equals(result.hasil)) {
            render(view: "show", model: [district: result.isi, result: result])
        }else{
            redirect(action: "show",params: result)
        }

    }
}
