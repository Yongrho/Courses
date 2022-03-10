// references: https://gis.stackexchange.com/questions/41928/adding-removing-geojson-layers-using-leaflet
//             https://leafletjs.com/SlavaUkraini/examples/custom-icons/
//             https://github.com/bbecquet/Leaflet.RotatedMarker            

// IIFE
(() => {
    //create map in leaflet and tie it to the div called 'theMap'
    let map = L.map('theMap').setView([44.650627, -63.597140], 14);
    
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);
/*
    L.marker([44.650690, -63.596537]).addTo(map)
        .bindPopup('This is a sample popup. You can put any html structure in this including extra bus data. You can also swap this icon out for a custom icon. A png file has been provided for you to use if you wish.')
        .openPopup();
*/    
    var busIcon = new L.Icon({
        iconSize: [27, 27],
        iconAnchor: [13, 27],
        popupAnchor:  [1, -1],
        iconUrl: 'bus.png'
    });

    setInterval(function () {
        removeMarkers();
        updatedJson();
    }, 10000);

    const updatedJson = function() {
        fetch('https://hrmbusapi.herokuapp.com/')
        .then((response) => response.json())
        .then((json) => {
            var geoFeaturesByRouterId = getBusByRouterId(json);
            setMarkerForBus(geoFeaturesByRouterId);
        })
    }

    updatedJson();

    var removeMarkers = function() {
        map.eachLayer(function(layer) {
            if (layer.myTag &&  layer.myTag === "myGeoJSON") {
                map.removeLayer(layer)
            }
        });
    }

    function onEachFeature(feature, layer) {
        // does this feature have a property named popupContent?
        if (feature.properties && feature.properties.popupContent) {
            layer.bindPopup(feature.properties.popupContent);
        }

        if (feature.properties && feature.properties.directionId) {
            layer.setRotationAngle(180);
        }
        layer.myTag = "myGeoJSON";
    }

    const setMarkerForBus = function(geoJSON) {
        geoJSON.map((feature) => {
            L.geoJSON(feature, {
                pointToLayer: function (feature, latlng) {
                    return L.marker(latlng, {icon: busIcon});
                },
                onEachFeature: onEachFeature
            }).addTo(map);
        });
    }

    const getBusByRouterId = function(json) {
        var routeIds = ["1","2","3","4","5","6A","6B","7A","7B","8","9A","9B","10"];

        return json.entity
                .filter((entity) => routeIds.some((routeId) => entity.vehicle.trip.routeId === routeId)) 
                .map((entity) => {
                    var popupContentString = "<p>Route Id: " + entity.vehicle.trip.routeId + "<br>Id: " + entity.id;
                    if (entity.vehicle.occupancyStatus) {
                        popupContentString += ("<br>Occupancy Status: " + entity.vehicle.occupancyStatus);
                    };

                    var direction = 0;
                    if (entity.vehicle.trip.directionId) {
                        direction = entity.vehicle.trip.directionId;
                    };

                    return {
                        type: "Feature",
                            geometry: {
                                type: "Point",
                                coordinates: [entity.vehicle.position.longitude, entity.vehicle.position.latitude]
                            },
                            properties: {
                                name: entity.vehicle.trip.routeId,
                                directionId: direction,
                                popupContent: popupContentString
                            }
                        }
                });
    }    
})()