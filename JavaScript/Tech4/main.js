// IIFE
(() => {

    // WRITE YOUR drawTable FUNCTION BELOW
    // |           |             |           |
    // V           V             V           V
    const drawTable = function(data) {
        var modifyDiv = document.getElementById("tableContainer")
        var table = document.createElement("populationTable");
        modifyDiv.appendChild(table);
        
        for (var row = 0; row < data.length; row++) {
            var tr = document.createElement("tr");
            tr.setAttribute("id", "tr" + row);
            table.appendChild(tr);

            var tdName = document.createElement("td");
            var tName = document.createTextNode(data[row].name);
            tdName.appendChild(tName);
            document.getElementById("tr" + row).appendChild(tdName);     

            var tdPopulation = document.createElement("td");
            tdPopulation.setAttribute("class", "populationCell");
            var tPopulation = document.createTextNode(data[row].population);
            tdPopulation.appendChild(tPopulation);
            document.getElementById("tr" + row).appendChild(tdPopulation);   
        }       
    };

    // WRITE YOUR drawChart FUNCTION BELOW
    // |           |             |           |
    // V           V             V           V
    const drawChart = function(data) {
        var countries = [];

        for (var row = 0; row < data.length; row++) {
            var country = [];
            country.push(data[row].name);
            country.push(data[row].population);
            countries.push(country);
        }
        console.log(countries);

        var chart = c3.generate({
            bindto: '#chartContainer',
            data: {
                columns: [
                    countries[0],
                    countries[1],
                    countries[2],
                    countries[3],
                    countries[4],
                    countries[5],
                    countries[6],
                    countries[7],
                    countries[8],
                    countries[9],
                    countries[10],
                    countries[11],
                    countries[12],
                    countries[13],
                    countries[14]
                ],
                type : 'pie',
                onclick: function (d, i) { console.log("onclick", d, i); },
                onmouseover: function (d, i) { console.log("onmouseover", d, i); },
                onmouseout: function (d, i) { console.log("onmouseout", d, i); }
            }
        });
    };

    // FETCH DATA FROM API
    fetch('https://prog2700.netlify.app/json/restcountries/v2/all.json')
    .then(response => response.json())
    .then(json => {

        console.log(json); // TEMP - JUST TO DISPLAY API DATA

        let data = null;
        // FILTER THE JSON DATA FOR ONLY SOUTH AMERICAN COUNTRIES BELOW
        // |           |             |           |
        // V           V             V           V
        data = json       
                .filter((country) => country.subregion != null && country.subregion == 'South America')
                .map((country) => {
                    return {
                        name: country.name, 
                        population: country.population
                    };  
        }, 0);    

        // call the function to draw the table
        drawTable(data);

        // call the function to draw the chart
        drawChart(data);
    });


})();