/*
    Author: Yong Seung Rho
    Date: Feb 14, 2022
    Description: Poker Hands for PROG2700
    Reference: https://www.w3schools.com/js/js_ajax_intro.asp
               https://www.cardplayer.com/rules-of-poker/hand-rankings
               https://www.unibet.com/poker/guides/poker-hand-rankings-with-cheat-sheet-1.784253

    Summary:
        Using the publicly available Deck of Cards API, you will create a small JavaScript application 
        that uses the API to provide data so that you can do the following.

        •	Retrieve a Deck of shuffled cards from the API.
        •	Initially pull 5 cards from the deck and display them in a web page.
        •	Write/research a function that takes the cards and shows the highest poker hand 
            that can be calculated based on the 5 cards.
*/

// Immediately-invoked Function Expression (IIFE)
(function() {
    var urls = ["http://pokerhand-tester.herokuapp.com/royalflush", // Royal Flush
                "http://pokerhand-tester.herokuapp.com/straightflush", // Straight Flush 
                "http://pokerhand-tester.herokuapp.com/fourofakind", // Four of a kind
                "http://pokerhand-tester.herokuapp.com/fullhouse", // Full House
                "http://pokerhand-tester.herokuapp.com/flush", // Flush 
                "http://pokerhand-tester.herokuapp.com/straight", // Straight
                "http://pokerhand-tester.herokuapp.com/threeofakind",// Three of a kind
                "http://pokerhand-tester.herokuapp.com/twopair", // Two Pair
                "http://pokerhand-tester.herokuapp.com/onepair", // One Pair
                "http://pokerhand-tester.herokuapp.com/highcard" // High Card
                ];

    var testcards = 0;
    var testFromApiCount = 0;
    var deck_id;

    window.testRanking = testRanking;
    loadDoc("https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1", shuffleCards);

    function testRanking() {
        if (testcards < 9) {
            loadDoc(urls[testcards], drawCards);
            testcards++;
        } else {
            if (testFromApiCount < 3) {
                loadDoc("https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1", shuffleCards);
                testFromApiCount++;
            } else {
                var rnd = Math.floor(Math.random() * urls.length);
                loadDoc(urls[rnd], drawCards);
            }
        }   
    }

    const xhttp = new XMLHttpRequest();

    /*
        Question 1	RETRIEVE AND PERSIST A DECK OF CARDS FROM THE API
        Using the Deck of Cards API (https://deckofcardsapi.com/), use a demonstrated method of AJAX data retrieval to retrieve a deck of cards that can be used by the application.
    */ 
    function loadDoc(url, callbackFunction) {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {callbackFunction(this);}
        xhttp.open("GET", url);
        xhttp.send();
    }

    /*
        Question 2	REQUEST FIVE CARDS FROM THE DECK
        Using the deck that was retrieved in REQ-001, ask the API for a hand of five cards from the deck.
    */
    function shuffleCards(xhttp) {
        var countCards = 5;
        var responseObjects = JSON.parse(xhttp.responseText);
        deck_id = responseObjects.deck_id;
        console.log("deck_id: " + deck_id);
        var url = "https://deckofcardsapi.com/api/deck/" + deck_id + "/draw/?count=" + countCards;
        loadDoc(url, drawCards); 
    }

    /*
        Question 3	DISPLAY THE HAND IN A WEB PAGE
        Display the cards in some manner that can be seen in the browser. This can be done by either
        1)	Displaying the cards names in some manner using document.write to the web page
        2)	Displaying the images of the cards on the web page by modifying the DOM. (ie Manipulate img tags defined on the page)
    */    
    function drawCards(xhttp) {
        var responseObjects = JSON.parse(xhttp.responseText);        
        var image, value, suit, code;

        var html_output = "";
        html_output = "<h1>Deck of Cards</h1>"
        html_output += "<table>";
        html_output += "<tr>";
        for(var i = 0; i < responseObjects.cards.length; i++) {
            image = responseObjects.cards[i].image;
            value = responseObjects.cards[i].value;
            suit = responseObjects.cards[i].suit;
            code = responseObjects.cards[i].code;
            html_output += "<td><img src=\"" + image + "\" alt=\"" + code + "\" width=\"100\" height=\"150\"></td>";       
        }

        console.log(responseObjects.cards);
        html_output += "</tr>";
        html_output += "</table>";
        document.write(html_output);

        var cards = sortCards(responseObjects.cards);
        var i = 0;
        var rankingFuncs = [isRoyalFlush, isStraightFlush, isFourOf, isFullHouse,isFlush, isStraight,  isThreeOf, isTwoPair, isOnePair];

        for (var i = 0; i < rankingFuncs.length; i++) {
            ret = rankingFuncs[i](cards);
            if (ret == true) {
                break;
            }  
        } 

        var rankingNames = ["Royal Flush", "Straight Flush", "Four Of a kind", 
                            "Full House", "Flush", "Straight",  
                            "Three Of a kind", "Two Pair", "One Pair", 
                            "High Card"];

        document.write("<h1>" + rankingNames[i] + "</h1>");
        if (testcards >= 9 && testFromApiCount < 3) {
            document.write("<h2>deck_id: " + deck_id + "</h2>");
        }
        document.write("<div id=\"demo\"><button type=\"button\" onclick=\"testRanking()\">Show next cards</button></div>");
    }

    function sortCards(cards) {
        var i, j, temp;
        for (i = 0; i < cards.length; i++) {
            switch(cards[i].value) {
                case "KING":
                    cards[i].value = 13;
                    break;
                case "QUEEN":
                    cards[i].value = 12;
                    break;
                case "JACK":
                    cards[i].value = 11;
                    break;
                case "ACE":
                    cards[i].value = 1;
                    break;  
                default:
                    cards[i].value = parseInt(cards[i].value);
                    break;
              }
        }

        var temp;
        var max = 0;

        for (i = 0; i < cards.length; i++) {    
            max = i;
            for (j = i + 1; j < cards.length; j++) {    
                if (cards[max].value < cards[j].value) {
                    max = j;
                }
            }
            temp = cards[i];
            cards[i] = cards[max];
            cards[max] = temp;
        }
        return cards;
    }

    /* A, K, Q, J, 10, all the same suit. */
    function isRoyalFlush(cards) {
        // See if all cards are the same suit
        for (var i = 1; i < cards.length; i++) {
            if (cards[0].suit !== cards[i].suit) {
                return false;
            }
        }

        // See if a card is King, Queen, Jack, 10, and Ace
        for (var i = 0; i < cards.length; i++) {
            if (cards[i].value !== 13 
                && cards[i].value !== 12 
                && cards[i].value !== 11 
                && cards[i].value !== 10 
                && cards[i].value !== 1) {
                return false;    
            }
        }
        return true;
    }
    
    /* Five cards in a sequence, all in the same suit.*/
    function isStraightFlush(cards) {
        for (var i = 1; i < cards.length; i++) {
            if (cards[0].suit !== cards[i].suit) {
                return false;
            }
        }

        for (var i = 0; i < cards.length - 1; i++) {
            x1 = cards[i].value;
            x2 = cards[i + 1].value;
            if (cards[i].value !== cards[i + 1].value + 1) {
                return false;
            }
        }
        return true;
    }
    
    /* All four cards of the same rank. */
    function isFourOf(cards) {
        var count = 1;
        for (var i = 0; i < 2; i++) {
            for (var j = i + 1; j < cards.length; j++) {
                if (cards[i].value === cards[j].value) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                }
            }
            count = 1;
        }
        return false;
    }
    
    /* Three of a kind with a pair. */
    function isFullHouse(cards) {
        var count = 1;
        var triple = false;

        // find three of a kind
        var i = 0;
        for (var j = i + 1; j < cards.length; j++) {
            if (cards[i].value === cards[j].value) {
                count++;
            } else {
                break;
            }
        }
        if (count == 2) {
            pair = true;
        } else if (count == 3) {
            triple = true;
        } else {
            return false;
        }

        i = count;
        count = 1;
        for (var j = i + 1; j < cards.length; j++) {
            if (cards[i].value === cards[j].value) {
                count++;
            } else {
                break;
            }
        }

        if (pair = true && count == 3) {
            return true;
        } else if (triple == true && count == 2) {
            return true;
        } else {
            return false;
        }
    }
    
    /* Any five cards of the same suit, but not in a sequence. */
    function isFlush(cards) {
        var count = 1;
        // See if there is the same suit
        for (var i = 1; i < cards.length; i++) {
            if (cards[0].suit === cards[i].suit) {
                count++;
                if (count == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Five cards in a sequence, but not of the same suit. */
    function isStraight(cards) {     
        for (var i = 0; i < cards.length - 1; i++) {
            if (cards[i].value !== cards[i + 1].value + 1) {
                return false;
            }
        }   
        return true;
    }

    /* Three cards of the same rank. */
    function isThreeOf(cards) {
        var count = 1;
        for (var i = 0; i < cards.length - 2; i++) {
            for (var j = i + 1; j < cards.length; j++) {
                if (cards[i].value === cards[j].value) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            count = 1;
        }
        return false;
    }
    
    /* Three cards of the same rank. */
    function isTwoPair(cards) {
        var count = 1;
        var pair = false;

        for (var i = 0; i < cards.length - 1; i++) {
            for (var j = i + 1; j < cards.length; j++) {
                if (cards[i].value === cards[j].value) {
                    count++;
                    if (count == 2) {
                        if (pair == false) {
                            pair = true;
                        } else {
                            return true;
                        }
                    }
                }
            }
            count = 1;
        }        
        return false;
    }
    
    /* Two different pairs. */
    function isOnePair(cards) {
        var count = 1;
        var pair = false;

        for (var i = 0; i < cards.length - 1; i++) {
            for (var j = i + 1; j < cards.length; j++) {
                if (cards[i].value === cards[j].value) {
                    count++;
                    if (count == 2) {
                        return true;
                    }
                }
            }
            count = 1;
        }        
        return false;    
    }
})(); // anononymous function. end if IIFE