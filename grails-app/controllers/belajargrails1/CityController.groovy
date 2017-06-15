package belajargrails1
import grails.converters.JSON

class CityController {

    def cityService

    def index() {

    }

    def create(params){
        if(request.method=='GET'){
            return[city: new City(), provincelist: Province.getAll()]
        }

        println "masuk city controller create "+params
        Map result = new HashMap()
        result = cityService.validasiCreate(params)
        if ("Y".equals(result.hasil)){
            result = cityService.createCity(params)
            println result
            redirect (action:"list",params:result)
        }else{
            println result
            return[city: params,provincelist: Province.getAll(),result:result]
        }

        //render(contentType: 'application/json',text:"${result as JSON}")
    }

    def update(params){
        if(request.method=='GET'){
            return[city: params, provincelist: Province.getAll()]
        }
        println "======================================="
        println "masuk city controller update "+params
        Map result = new HashMap()
        result = cityService.validasiUpdate(params)
        if("Y".equals(result.hasil)){
            result = cityService.updateCity(params)
            redirect(action: "list",params: result)
        }else{
            println result
            return[city: params,provincelist: Province.getAll(),result:result]
        }
        render(contentType: 'application/json',text:"${result as JSON}")
    }

    def delete(params){
        println "masuk city controller delete "+params
        Map result = new HashMap()
        result = cityService.validasiDelete(params)
        if("Y".equals(result.hasil)){
            result = cityService.deleteCity(params)
            redirect(action: "list",params: result)
        }else{
            Map batalDelet = new HashMap()
            batalDelet = cityService.getCity(params)
            println "isi "+batalDelet.isi
            println "message 3 : "+result
            render(view: "showlist", model: [city:batalDelet.isi,district:batalDelet.listD,result: result])
        }


        //render(contentType: 'application/json',text:"${result as JSON}")
    }

    def show(){
        println "Masuk city controller show "

        def citys = cityService.listCity()

        render(contentType: 'application/json', text:"${citys as JSON}")
    }

    def list(params){
        println "Masuk city controller list"
        println "ini adalah isi param list "+params
        def citys = cityService.listCity()
        println citys.isicount
        [city:citys.isi, result: citys]
    }

    def search(params){
        println "Masuk city controller search "+params
        Map result = new HashMap()
        result = cityService.searchCity(params)
        if ("Y".equals(result.hasil)) {
            render(view: "list", model: [city: result.isi, result: result])
        }else{
            redirect(action: "list",params: result)
        }

    }

    def showlist(params){
        println "Masuk city controller showlist "+params.nama
        Map result = new HashMap()
        result = cityService.getCity(params)
        println result
        [city:result.isi,district:result.listD]
    }

}
