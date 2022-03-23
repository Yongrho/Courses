// Author: Yong Seung Rho
// Date: Mar 8, 2022
// Description: 3-in-a-Row Game for PROG2700

/*
    Simulate the 3-in-a-Row puzzle by writing it in pure JavaScript
    reference: 
        https://www.brainbashers.com/show3inarow.asp
        https://www.w3schools.com/jsref/dom_obj_table.asp
        http://dotnetlearners.com/javascript/find-table-cell-value-on-cell-table-click-using-javascript
        https://www.w3schools.com/howto/howto_js_countdown.asp
        https://www.w3schools.com/jsref/met_loc_reload.asp
*/

window.onload = () => {
    var countDownDate;

    fetch('https://threeinarowpuzzle.herokuapp.com/random')
//    fetch('https://threeinarowpuzzle.herokuapp.com/sample')
    .then((response) => response.json())
    .then((json) => {
        var modifyDiv = document.getElementById("theGame")
        var para = document.createElement("p");
        para.setAttribute("id", "countdown");
        modifyDiv.appendChild(para);

        var table = document.createElement("table");
        table.setAttribute("id", "gameTable");
        modifyDiv.appendChild(table);

        makeTable(json);
        findCell(json, table);

        var buttonCheckPuzzle = document.createElement("input");
        buttonCheckPuzzle.setAttribute("type", "button");
        buttonCheckPuzzle.setAttribute("value", "Check Puzzle");
        buttonCheckPuzzle.onclick = function(){
            checkPuzzle(json);
        };
        modifyDiv.appendChild(buttonCheckPuzzle);

        var checkboxPuzzle = document.createElement("input");
        checkboxPuzzle.setAttribute("type", "checkbox");
        checkboxPuzzle.setAttribute("id", "checkbox_puzzle");
        modifyDiv.appendChild(checkboxPuzzle);

        var checkboxLabel = document.createElement("label");
        checkboxLabel.setAttribute("for", "checkbox_puzzle");
        var txtCheckbox = document.createTextNode("Check any incorrect squares");
        checkboxLabel.appendChild(txtCheckbox);
        checkboxPuzzle.onclick = function(){
            checkboxState(this, json);
        };

        modifyDiv.appendChild(checkboxLabel);
    
        // Set the date we're counting down to
        var now = new Date().getTime();
        countDownDate = now + (1000 * 60 * json.rows.length);

        modifyDiv.appendChild(document.createElement("br"));
        modifyDiv.appendChild(document.createElement("br"));
        
        var buttonReload = document.createElement("input");
        buttonReload.setAttribute("type", "button");
        buttonReload.setAttribute("value", "Reload");
        buttonReload.onclick = function(){
            location.reload();
        };
        modifyDiv.appendChild(buttonReload);
    });

    // Update the count down every 1 second
    var x = setInterval(function() {

        // Get today's date and time
        var now = new Date().getTime();

        // Find the distance between now and the count down date
        var distance = countDownDate - now;

        // Time calculations for minutes and seconds
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Display the result in the element with id="demo"
        document.getElementById("countdown").innerHTML = "Time Limitation: " + minutes + "m " + seconds + "s ";

        // If the count down is finished, write some text
        if (distance < 0) {
            clearInterval(x);
            document.getElementById("countdown").innerHTML = "EXPIRED";
            alert("You did not make it!!");
        }
    }, 1000);

    function checkboxState(checkbox, json) {
        var len = json.rows.length;

        for (let row = 0; row < len; row++) {
            for (let col = 0; col < len; col++) {
                var cellId = row + "_" + col;
                var td = document.getElementById(cellId);

                if (checkbox.checked == true) {
                    if (json.rows[row][col].currentState != 0 &&
                        json.rows[row][col].currentState != json.rows[row][col].correctState) {
                        td.style.border = "3px solid red";
                    }
                } else {
                    td.style.border = "1px solid black";
                }
            }
        }
    }

    const checkPuzzle = function(json) {
        if (checkThreeInRow(json)) {
            alert("Something is wrong");
            return;
        }

        let completed;              
        for (let i = 0; i < json.rows.length; i++) {
            console.log("i: " + i);
            completed = json.rows[i].every((row) => row.currentState === row.correctState);                    
//            console.log("completed: " + completed);
            if (completed === false) {
                break;
            }
        }

        if (completed === true) {
            clearInterval(x);
            alert("You did it!!");
            return;
        }
        alert("So far so good");      
    };

    function findThreeInRow(json, row, col) {
        let currentState = json.rows[row][col].currentState;
        if (currentState === 0) {
            return false;
        }
        
        var len = json.rows.length - 1;

        if (row + 2 > len) {
            start_row = row - 2;
            end_row = len;
        } else {
            start_row = row - 2;
            if (start_row < 0) {
                start_row = 0;
            }    
            end_row = row + 2;
        }

        if (col + 2 > len) {
            start_col = col - 2;
            end_col = len;
        } else {
            start_col = col - 2;
            if (start_col < 0) {
                start_col = 0;
            }    
            end_col = col + 2;
        }

        let found;
        for (let i = start_row; i <= end_row - 2; i++) {
            found = true;
            for (let j = i; j <= i + 2; j++) {
                if (currentState !== json.rows[j][col].currentState) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }

        for (let i = start_col; i <= end_col - 2; i++) {
            found = true;
            for (let j = i; j <= i + 2; j++) {
                if (currentState !== json.rows[row][j].currentState) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }

    function checkThreeInRow(json) {
        var len = json.rows.length - 1;

        for (let row = 0; row < len; row++) {
            for (let col = 0; col < len; col++) {
                if (findThreeInRow(json, row, col)) {
                    return true;
                }               
            }
        }
        return false;
    }

    function getval(cell, json) {
        var cells = json.rows;
        var loc = cell.id.split("_");
        var row = parseInt(loc[0]);
        var col = parseInt(loc[1]);

        if (cells[row][col].canToggle === false) {
            return;
        }

        cells[row][col].currentState += 1;
        if (cells[row][col].currentState >= 3) {
            cells[row][col].currentState = 0;
        }

        var td = document.getElementById(cell.id);
        setStyle(td, cells[row][col].currentState);
        if (findThreeInRow(json, row, col)) {
            return;
        }
    }

    const findCell = function(json, table) {
        for (var i = 0; i < json.rows.length; i++) {
            for (var j = 0; j < json.rows.length; j++)
                table.rows[i].cells[j].onclick = function () {
                    getval(this, json); 
                };
        }
    }

    function setStyle(td, state) {
        var color = "gray";

        switch (state) {
            case 1: 
                color = "blue"; 
                break;
            case 2: 
                color = "white"; 
                break;
            default: 
                break;
        }
        td.style.backgroundColor = color;
        td.style.border = "1px solid black";
        td.style.width = "50px";
        td.style.height = "50px";
    }

    const makeTable = function(json) {
        var cells = json.rows

        for (var row = 0; row < cells.length; row++) {
            var tr = document.createElement("tr");
            tr.setAttribute("id", "tr" + row);
            document.getElementById("gameTable").appendChild(tr);

            for (var col = 0; col < cells.length; col++) {
                var td = document.createElement("td");

                td.id = row + "_" + col;

                // set style
                setStyle(td, cells[row][col].currentState);

                // for debugging
//                var t = document.createTextNode("cell" + row + col);
//                td.appendChild(t);

                document.getElementById("tr" + row).appendChild(td);            
            }    
        }
    };
};