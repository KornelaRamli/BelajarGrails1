package belajargrails1

import com.sun.xml.internal.ws.api.pipe.ContentType

class Province {

    String nama
    String pulau
    String deskripsi

    static constraints = {
        nama (blank:true)
        pulau (blank:true)
        deskripsi(blank: true,maxSize: 255)
    }
}
