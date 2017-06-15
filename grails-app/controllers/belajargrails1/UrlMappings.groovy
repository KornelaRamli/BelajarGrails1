package belajargrails1

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/api/city/create"(controller:"city",action: "create")
        "/api/city/update"(controller:"city",action:"update")
        "/api/city/delete"(controller: "city",action:"delete")
        "/api/city/show"(controller: "city",action:"show")
        "/api/province/create"(controller:"province",action:"create")
        "/api/province/update"(controller: "province",action: "update")
        "/api/province/delete"(controller: "province",action: "delete")
        "/api/province/show"(controller:"province",action:"show")
        "/api/district/create"(controller:"district",action: "create")
        "/api/district/update"(controller:"district",action:"update")
        "/api/district/delete"(controller: "district",action:"delete")
        "/api/district/show"(controller: "district",action:"show")
        "/api/village/create"(controller:"village",action: "create")
        "/api/village/update"(controller:"village",action:"update")
        "/api/village/delete"(controller: "village",action:"delete")
        "/api/village/show"(controller: "village",action:"show")
        "/api/peta"(controller: "peta",action:"index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
