// Author: Yong Seung Rho
// Date: Jan 15, 2022
// Description: The first assigment for PROG2700

/*
Question1: 
Write a function in JavaScript that will receive a string as a parameter and then perform the following:
•	You don’t have to prompt for a string. Simply assign a string to a variable in your code as your starting point to use as an argument for your function.
•	If the first and last characters of the string are the same (ignoring case), the function will return the string in reverse order. Otherwise, the function will return the string with the first and last letters removed.
•	Example: “Triscuit” returns “tiucsirT” but “Cracker” returns “racke”.
reference: https://www.w3schools.com/js/js_string_methods.asp
*/
function reverseOrder(letters) {
    var len = letters.length;
    var ignoring_case_string = letters.toLowerCase();

    var new_letters = "";

    if (ignoring_case_string[0] === ignoring_case_string[len - 1]) {
        for (let i = 0; i < len; i++) {
            new_letters += letters[len - i - 1];
        }
    } else {
        for (let i = 0; i < len - 2; i++) { 
            new_letters += ignoring_case_string[i + 1];
        }
    }
    return new_letters;
}
console.log(reverseOrder("Triscuit"));
console.log(reverseOrder("Cracker"));
console.log(reverseOrder("Yong Seung"));
console.log(reverseOrder("Notation"));

/*
Question2: 
Write a function in JavaScript that will return the sum of the longest streak of consecutive increasing numbers within an array. 
•	If there are no consecutive numbers in the array, the function will return zero.
•	If there are multiple instances of the same number of consecutive numbers (increasing by 1) in the array, the function will return the largest sum calculated between all instances.
•	Examples:
o	[1, 2, 3, 6, 9, 34, 2, 6] would return 6 (1+2+3)
o	[3, 2, 7, 5, 6, 7, 3, 8, 9, 10, 23, 2, 1, 2, 3] would return 27 (8+9+10)
o	[100, 101, 102, 3, 4, 5, 6, 9] would return 18 (3+4+5+6)
reference: https://www.w3schools.com/js/js_arrays.asp
*/
function sumOfConsecutiveNumber(arr) {
    var sumNums = 0;
    var newArray = [];
    let len = arr.length;
    var i, j;
    var inc = 0;
    newArray[inc] = [];
    newArray[inc].push(arr[0]);

    for (i = 0; i < arr.length - 1; i++) {
        if ((arr[i + 1] != arr[i] + 1)) {
            inc++;
            newArray[inc] = [];
        }
        newArray[inc].push(arr[i + 1]);        
    }

    var tempNums = 0;
    var maxLength = 0;
    for (i = 0; i < newArray.length; i++) {
        lenArray = newArray[i].length;
        if (maxLength <= lenArray) {
            tempNums = 0;
            for (j = 0; j < newArray[i].length; j++) {
                tempNums += newArray[i][j];
            }
 
            if (maxLength < lenArray) {
                sumNums = tempNums
            } else {
                if (sumNums < tempNums) {
                    sumNums = tempNums;
                }
            }
            maxLength = lenArray;
        }
    }
    return sumNums;
}

console.log(sumOfConsecutiveNumber([1, 2, 3, 6, 9, 34, 2, 6]));
console.log(sumOfConsecutiveNumber([3, 2, 7, 5, 6, 7, 3, 8, 9, 10, 23, 2, 1, 2, 3]));
console.log(sumOfConsecutiveNumber([99, 100, 101, 3, 4, 5, 6, 7]));
console.log(sumOfConsecutiveNumber([202, 203, 204, 205, 1, 2, 3, 4]));

/*
Question 3	
Write a JavaScript program to calculate the number of weeks, days, hours, minutes and seconds left until midnight on your birthday.
•	The script does not have to prompt for your birthdate. Simply assign it to a variable and start from there.
o	Ex: var myNextBirthday = …your code here
•	Expected sample output (console.log()):
o	There are 35 weeks, 3 days, 13 hours, 25 minutes, and 12 seconds until my next birthday!
reference: https://www.w3schools.com/js/js_dates.asp
           https://www.w3schools.com/jsref/jsref_floor.asp
*/

function calculateBirthday(birthday) {
    const WEEK = 60 * 60 * 24 * 7;
    const DAY = 60 * 60 * 24;
    const HOUR = 60 * 60;
    const MINUTE = 60;
    var remainder = 0;

    const today = new Date()
    const myNextBirthday = new Date(birthday);
    var diff =(myNextBirthday.getTime() - today.getTime()) / 1000;

    var weeks = Math.floor(diff / WEEK);
    
    remainder = diff % WEEK;
    var days = Math.floor(remainder / DAY); 
    
    remainder = remainder % DAY;
    var hours = Math.floor(remainder / HOUR);

    remainder = remainder % HOUR;
    var minutes = Math.floor(remainder / MINUTE);

    var seconds = Math.floor(remainder % MINUTE);

    var message = "There are "
    if (weeks > 0) {
        message += (weeks + " weeks ");
    }
    if (days > 0) {
        message += (days + " days ");
    }
    if (hours > 0) {
        message += (hours + " hours ");
    }
    if (minutes > 0) {
        message += (minutes + " minutes ");
    }
    if (seconds > 0) {
        message += (seconds + " seconds ");
    }

    message += "until my next birthday!";
    return message;
}
console.log(calculateBirthday("2022/08/01"));

/*
Question 4	
Write a JavaScript program to iterate through an array of ten(10) positive randomly generated numbers. Each number will then be checked to see if it’s a prime number.
•	Sample Expected output (console.log()).

23-yes, 15-no, 22-no, 124-no, 11-yes, 9-no, 2-yes, 13-yes, 5-yes, 1-no

reference: https://www.programiz.com/python-programming/examples/prime-number
*/
function findPrimeNumber(number) {
    if (number == 1) {
        return "no";
    }

    for (let i = 2; i < number; i++) {
        if (number % i == 0) {
            return "no";
        }
    }
    return "yes";
}

function handleNumber(numbers) {
    var message = ""

    for (let i = 0; i < numbers.length; i++) {
        message += (numbers[i] + "-" + findPrimeNumber(numbers[i]));
        if (i < numbers.length - 1) {
            message += ", ";
        }
    }
    return message;
}

var numbers1 = [23, 15, 22, 124, 11, 9, 2, 13, 5, 1];
var numbers2 = [5, 9, 6, 111, 29, 28, 35, 20, 7, 10];
var numbers3 = [17, 18, 19, 31, 42, 53, 55, 63, 57, 11];

console.log(handleNumber(numbers1));
console.log(handleNumber(numbers2));
console.log(handleNumber(numbers3));