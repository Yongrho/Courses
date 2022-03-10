// Immediately-invoked Function Expression (IIFE)
(function(){
    const xhttp = new XMLHttpRequest();

    xhttp.onload = function() {
        var responseObjects = JSON.parse(this.responseText);
        var tableOutput = "";
        tableOutput = "<h1>API from https://mhw-db.com/ailments</h1><br>"
        tableOutput += "<table>";
        tableOutput += "<tr>";
        tableOutput += "<td>Name</td>";    
        tableOutput += "<td>Description</td>";
        tableOutput += "<td>Actions</td>";

        for(var i = 0; i < responseObjects.length; i++)
        {
            console.log(responseObjects[i].recovery.actions[0])
            tableOutput += "<tr>";
            tableOutput += "<td>" + responseObjects[i].name + "</td>";    
            tableOutput += "<td>" + responseObjects[i].description + "</td>";
            if (responseObjects[i].recovery.actions[0] !== undefined) {
                tableOutput += "<td>" + responseObjects[i].recovery.actions[0] + "</td>";
            }
            tableOutput += "</tr>";
        }
        tableOutput += "</table>";
//        document.getElementById("myData").innerHTML = tableOutput;
        document.write(tableOutput);
    }
    xhttp.open("GET", "https://mhw-db.com/ailments", true);
    xhttp.send();
})(); // anononymous function. end if IIFE