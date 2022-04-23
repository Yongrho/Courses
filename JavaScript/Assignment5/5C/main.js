// reference: https://c3js.org/gettingstarted.html

// IIFE
window.onload = () => {

	//Choose an array method to implement for each of the incomplete functions.
	//FOR/WHILE LOOPS OF ANY KIND ARE FORBIDDEN! You must use the available array functions to accomplish your goal.

	//Remember, you can chain together array function calls to attain your goals.
	// Ex: array.filter().map()

	//API from https://mhw-db.com/ailments
	fetch('https://mhw-db.com/ailments')
    .then((response) => response.json())
    .then((json) => {

        console.log(json);

        drawDodgeCount(json);

        drawRecoveryItemCount(json);
        
        drawRecoveryItemLevel(json);
    })

    const drawDodgeCount = function(json) {
        const count_dodge = json
                .reduce((count, ailment) => {
                        if (ailment.recovery.actions[0] == "dodge") {
                            count++;
                        }
                        return count;  
                }, 0);

        var action_dodge = []
        action_dodge[0] = 'Action dodge';
        action_dodge[1] = count_dodge;

        var non_action_dodge = []
        non_action_dodge[0] = 'Non Action dodge';
        non_action_dodge[1] = json.length - count_dodge;

        var chart = c3.generate({
            bindto: '#myChart1',
            data: {
                columns: [
                    action_dodge,
                    non_action_dodge
                ],
                type : 'pie',
                onclick: function (d, i) { console.log("onclick", d, i); },
                onmouseover: function (d, i) { console.log("onmouseover", d, i); },
                onmouseout: function (d, i) { console.log("onmouseout", d, i); }
            }
        });  
    };

    const drawRecoveryItemCount = function(json) {
        const count_items = json
            .reduce((data, ailment) => {
                var name = ailment.name;
                if (!data.hasOwnProperty(name)) {
                    data[name] = 0;
                }
                data[name] = ailment.recovery.items.length;
                return data;
            }, {});
      
        var recovery_items_by_name = [];
        var recovery_items_count_by_name = [];

        recovery_items_count_by_name.push("Name");
        for(var i in count_items) {
            recovery_items_by_name.push(i);
            recovery_items_count_by_name.push(count_items[i]);
        }

        var chart = c3.generate({
            bindto: '#myChart2',
            data: {
                columns: [
                    recovery_items_count_by_name
                ],
                type: 'bar'
            },
            axis: {
                x: {
                    type: 'category',
                    categories: recovery_items_by_name
                }
            }
        });
    };

    const drawRecoveryItemLevel = function(json) {
        const data = json
            .map((ailment) => {
                return {       
                    name: ailment.name,
                    items: ailment.recovery.items
                }  
            });

        var names = [];

        var varities = [];
        varities.push("varity");

        var values = [];
        values.push("value");
        
        var carryLimits = [];
        carryLimits.push("carryLimit");
        
        for (var i = 0; i < data.length; i++) {
            names.push(data[i].name);

            var item = data[i].items;
            var varity = 0;
            var value = 0;
            var carryLimit = 0;

            for (var j = 0; j < item.length; j++) {
                varity += item[j].rarity;
                value += item[j].value;
                carryLimit += item[j].carryLimit;
            }

            varities.push(varity);
            values.push(value);
            carryLimits.push(carryLimit);
        } 

        var chart = c3.generate({
            bindto: '#myChart3',
            data: {
                columns: [
                    varities
                ],
                type: 'bar'
            },
            axis: {
                x: {
                    type: 'category',
                    categories: names
                }
            }
        });
        
        setTimeout(function () {
            chart.load({
                columns: [
                    values
                ]
            });
        }, 1000);
       
        setTimeout(function () {
            chart.load({
                columns: [
                    carryLimits
                ]
            });
        }, 2000);
    }    
}