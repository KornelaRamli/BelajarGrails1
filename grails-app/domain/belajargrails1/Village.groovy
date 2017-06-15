package belajargrails1

class Village {

    String nama
    String deskripsi
    District district

    static constraints = {
        nama(blank: false)
        deskripsi(blank: true)
        district (blank:false)
    }
}
