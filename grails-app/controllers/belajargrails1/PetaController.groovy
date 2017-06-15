package belajargrails1


class PetaController {

    def petaService
    def index() {
        Map result = new HashMap()
        result = petaService.getMarker()
        println "ini isi result controller ============================================="+result
        render(view:"/peta/index", model:[result:result])
    }
}
