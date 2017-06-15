package belajargrails1

class District {

    String nama
    String deskripsi
    City city
    String latlng
    static constraints = {
        nama(blank:false)
        deskripsi(blank: true)
        city(blank:false)
        latlng (blank: true)
    }
}
