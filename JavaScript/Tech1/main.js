/*
* PHONEWORDS
* Write a function that will take a phone word (vanity number) and return the correct telephone number.
* The number pad looks like the following:
* https://en.wikipedia.org/wiki/Telephone_keypad#/media/File:Telephone-keypad2.svg
*
* RULES
* Given a phoneword:
* 1. Ignore any non-alphanumeric characters (), -, etc.
* 2. Keep any existing digits
* 3. Resolve a letter to a number according to the keypad image
* 4. All your code must be contained in the section outlined below
*/


var buttons = ["abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"] //DO NOT MODIFY


// YOU CAN ADD TO AND MODIFY THE CODE BELOW THIS LINE
//   |
//   V
 
function convertPhoneWord(phoneWord) {

    //Enter your code in this function body
    if (phoneWord === null || phoneWord === undefined) {
        console.log("null");
        return "";
    }

    var new_phoneword = "";
    var len = phoneWord.length;

    for (let i = 0; i < len; i++) {
        ch = phoneWord[i];
        if (isDigit(ch)) {
            new_phoneword += ch;
        } else if (isLetter(ch)) {
            ch = ch.toLowerCase(); 
            for (let j = 0; j < buttons.length; j++) {
                if (buttons[j].indexOf(ch) !== -1) {
                    new_phoneword += (j + 2);
                    break;
                }
            }
        }        
    }
    console.log(new_phoneword);
    return new_phoneword;
}

//    ^
//    |
//YOU CAN ADD TO OR MODIFY THE CODE ABOVE THIS LINE




// DO NOT CHANGE ANY CODE AFTER THIS LINE.
//     |
//     |
//     V

//helper functions...do not modify, but you can use them in your code

function isDigit(character) {
    return "0123456789".indexOf(character) !== -1;
}

function isLetter(character) {
    character = character.toUpperCase();    
    return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(character) !== -1;
}

testCode();