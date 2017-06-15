// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require bootstrap
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

var map = L.map('map',{
    center: [-0.903794,118.1341778],
    zoom: 4
});


L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);



$("#divCreate").each(function () {
    $(document).ready(function(){

        var popup = L.popup();

        function onMapClick(e){
            popup
                .setLatLng(e.latlng)
                .setContent("Posisi kota akan berada di "+e.latlng.toString()).openOn(map);

            $("#txtPosisi").val(e.latlng.lat+","+e.latlng.lng)
            $("#showTxt").text(e.latlng.lat+","+e.latlng.lng)
        }

        map.on('click',onMapClick);

    });
});


function onMapClick(e){
    // popup
    //     .setLatLng(e.latlng)
    //     .setContent("Posisi kota akan berada di "+e.latlng.toString()).openOn(map);

    //document.getElementById("txtPosisi").value=e.latlng.lat+","+e.latlng.lng
    $("#txtPosisi").val(e.latlng.lat+","+e.latlng.lng)
    $("#showTxt").text(e.latlng.lat+","+e.latlng.lng)
    $("#txtLatlng").val(e.latlng.lat+","+e.latlng.lng)
}

map.on('click',onMapClick);


//last straw


$("#divShow").each(function () {
   $(document).ready(function(){

       map.scrollWheelZoom.disable();
       var arr = $("#txtLatlng").val().split(',');
       // alert("lat "+arr[0]+" lng "+arr[1]);
       map.setView(arr,14);

       L.marker(arr).addTo(map);
   });
});

$("#divUpdate").each(function () {
    $(document).ready(function(){
        var popup = L.popup();

        function onMapClick(e){
            popup
                .setLatLng(e.latlng)
                .setContent("Posisi kota akan berada di "+e.latlng.toString()).openOn(map);

            $("#txtPosisi").val(e.latlng.lat+","+e.latlng.lng)
            $("#showTxt").text(e.latlng.lat+","+e.latlng.lng)
        }

        map.on('click',onMapClick);


        var arr = $("#txtLatlng").val().split(',');
        // alert("lat "+arr[0]+" lng "+arr[1]);
        map.setView(arr,14);
    });
});