/**
 * Created by user on 14/06/2017.
 */
var fullmap = L.map('fullmap',{
    center: [-0.903794,118.1341778],
    zoom: 4
});


L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(fullmap);

var citys = result.citys;
var districts=result.districts;
console.log("testr ");

var markClus = L.markerClusterGroup();
console.log("testr 1 ");
// var markerCluster = L.markerClusterGroup();
for(i in result.citys) {
    console.log(i+" hello" + citys[i].nama + " koordinat : " + citys[i].latlng);
    if(citys[i].latlng!=null&&citys[i].latlng!=""){
        console.log("masuk ");
        var latlng = citys[i].latlng.split(",");
        console.log("lat "+latlng[0]+" lng "+latlng[1]);
        var a = L.marker(latlng).bindPopup(citys[i].nama).addTo(markClus);

       markClus.addLayer(a);
    }
}

fullmap.addLayer(markClus);

// var layerCity = L.layerGroup(arrMarker).addTo(fullmap);