package belajargrails1

class City {

    String nama
    String deskripsi
    Province provinsi
    String latlng

    static constraints = {
        nama (blank:true)
        deskripsi(blank: true,maxSize: 255)
        provinsi (blank:true)
        latlng(blank: true)
    }
}
