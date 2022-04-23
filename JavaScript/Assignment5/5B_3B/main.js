// IIFE
window.onload = () => {

	//Choose an array method to implement for each of the incomplete functions.
	//FOR/WHILE LOOPS OF ANY KIND ARE FORBIDDEN! You must use the available array functions to accomplish your goal.

	//Remember, you can chain together array function calls to attain your goals.
	// Ex: array.filter().map()

	//Get data for the TV Show "Friends"
	fetch('http://api.tvmaze.com/shows/431?embed[]=episodes&embed[]=cast')
    .then((response) => response.json())
    .then((json) => {

        console.log(json);

        //DO NOT MODIFY THE CODE IN HERE...check the console for your functions' output

        //1 - Create a function called getGuntherCount() which returns the total number of episodes 
        // where the character Gunther is mentioned in the episode summary.
        console.log('--------------------------------');
        console.log(`Gunther Count: ${getGuntherCount(json)}`);

        //2 - Create a function called getTotalRuntimeMinutes() that totals all runtime minutes for all episodes
        console.log('--------------------------------');
        console.log(`Total Runtime Minutes: ${getTotalRuntimeMinutes(json)}`);

        //3 - Create a function called getDateRangeEpisodeCount() that returns the number of episodes that aired in the year 2000
        console.log('--------------------------------');
        console.log(`Total episodes airing in year 2000: ${getTotalEpisodesInYear(json, "2000")}`);

        //4 - Create a function called getFemaleCastMembers() that returns an array of the names of the female cast members.
        console.log('--------------------------------');
        console.log(`Female Cast Members:`);
        console.log(getFemaleCastMembers(json));

        //5 - Create a function called getEpisodeTitles() which returns a list of episode
        //    where the argument string is found in the episode summary.
        console.log('--------------------------------');
        console.log(`Episodes that mention Ursula:`);
        console.log(getEpisodeTitles(json, 'Ursula'));

        //6 - Create a function called getCastMembersOver55() which returns a list of cast members
        //    who are currently older than 55 years of age.
        console.log('--------------------------------');
        console.log(`Cast Members over 55:`);
        console.log(getCastMembersOver55(json));

        //7 - Create a function called getTotalRuntimeMinutesExcludingSeasonSix that gets the total 
        //    runtime minutes for all episodes excluding episodes in season 6
        console.log('--------------------------------');
        console.log(`Total runtime in minutes excluding Season 6: ${getTotalRuntimeMinutesExcludingSeasonSix(json)}`);
    
        //8 - Create a function called getFirstFourSeasons that gets the episodes for the first four seasons 
        //    but only return an array of JSON objects containing the season number and episode name
        console.log('--------------------------------');
        console.log(`Episode JSON for first four seasons:`)
        console.log(getFirstFourSeasons(json));

        //9 - Create a function called getEpisodeTallyBySeason that returns an object containing the season name and the total episodes as key:value pairs for each season
        console.log('--------------------------------');
        console.log(`Tally of episodes by season:`);
        console.log(getEpisodeTallyBySeason(json));

        //10 - Create a funtion called capitalizeTheFriends that transforms the episode JSON data by capitalizing the words Joey, Chandler, Monica, Rachel, Phoebe, and Ross in both 
        //the name and summary of the episodes.
        console.log('--------------------------------');
        console.log('Capitalized Friends');
        console.log(capitalizeTheFriends(json));

        drawGuntherCount(json, getGuntherCount(json));
        drawEpisodeTallyBySeason(getEpisodeTallyBySeason(json));
        drawCountNameBySeason(json);
        drawRatingBySeason(json);
        drawCountAirByYear(json);
    })

    const drawCountAirByYear = function(json) {
        var years = ["1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004"];
        
        var episode_tally_by_year = [];

        episode_tally_by_year.push("Tally by Year")
        for (let i = 0; i < years.length; i++) {
            console.log(i);
            const count_air = json._embedded.episodes
                .reduce((count, episode) => {
                    var airdate = episode.airdate.split("-");
                    if (airdate[0] === years[i]) {
                        count++;
                    }
                    return count;  
               }, 0);
            episode_tally_by_year.push(count_air);
        }
        console.log(episode_tally_by_year);

        var chart = c3.generate({
            bindto: '#myChart5',
            data: {
                columns: [
                    episode_tally_by_year
                ]
            }, 
            type: 'spline',
            axis: {
                x: {
                    type: 'category',
                    categories: years
                }
            }
       });
        
    }

    const drawRatingBySeason = function(json) {
        const json_data = json._embedded.episodes
                .map((episode) => {
                    return {
                        season: episode.season, 
                        average: episode.rating.average
                    }
                });

        var season_name = [];
        var rating_average = [];

        season_name.push("Season");
        rating_average.push("Average Rating")
        for(var i in json_data) {
            season_name.push(json_data[i].season);
            rating_average.push(json_data[i].average);
        }
        console.log(season_name);
        console.log(rating_average);

        var chart = c3.generate({
            bindto: '#myChart4',
            data: {
               xs: {
                    'Average Rating': 'Season'
                },
                columns: [
                    season_name,
                    rating_average
                ],
                type: 'scatter'
            },
            axis: {
                x: {
                    label: 'Season',
                    tick: {
                        fit: false
                    }
                },
                y: {
                    label: 'Average Rating'
                }
            }
        });
    };

    const drawCountNameBySeason = function(json) {
        var names = ["Joey", "Chandler", "Monica", "Rachel", "Phoebe", "Ross"];
        
        var character_tally_by_season_data = [];
        var character_tally_by_season_count;

        var characters = [];
        for (var i in names) {
            character_tally_by_season_count = [];
            character_tally_by_season_count.push(names[i]);
            characters[i] = json._embedded.episodes
                                .reduce((data, episode) => {
                                    var season = episode.season;
                                    if (!data.hasOwnProperty(season)) {
                                        data[season] = 0;
                                    }
                                    if (episode.summary.includes(names[i])) {    
                                        data[season]++;
                                    }
                                return data;
                            }, {});
            
            const json_data = characters[i]
            for(var j in json_data) {
                character_tally_by_season_count.push(json_data[j]);      
            }
            character_tally_by_season_data[i] = character_tally_by_season_count;
        }

        var chart = c3.generate({
            bindto: '#myChart3',
            data: {
                columns: [
                    character_tally_by_season_data[0],
                    character_tally_by_season_data[1]/*,
                    character_tally_by_season_data[2],
                    character_tally_by_season_data[3],
                    character_tally_by_season_data[4],
                    character_tally_by_season_data[5]*/
                ],
            },
            axis: {
                x: {
                    type: 'category',
                    categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
                }
            }
        });
        setTimeout(function () {
            chart.load({
                columns: [
                    character_tally_by_season_data[2]
                ]
            });
        }, 1000);
        
        setTimeout(function () {
            chart.load({
                columns: [
                    character_tally_by_season_data[3]
                ]
            });
        }, 1500);
        
        setTimeout(function () {
            chart.load({
                columns: [
                    character_tally_by_season_data[4]
                ]
            });
        }, 2000);

        setTimeout(function () {
            chart.load({
                columns: [
                    character_tally_by_season_data[5]
                ]
            });
        }, 2500);
    }  

    const drawEpisodeTallyBySeason = function(json_data) {
        var episode_tally_by_season_name = [];
        var episode_tally_by_season_count = [];

        episode_tally_by_season_count.push("Season");
        for(var i in json_data) {
            episode_tally_by_season_name.push(i);
            episode_tally_by_season_count.push(json_data[i]);
        }

        var chart = c3.generate({
            bindto: '#myChart2',
            data: {
                columns: [
                    episode_tally_by_season_count
                ],
                type: 'bar'
            },
            axis: {
                x: {
                    type: 'category',
                    categories: episode_tally_by_season_name
                }
            }
        });
    };

    const drawGuntherCount = function(json, count_gunther) {
        var gunther_episode = []
        gunther_episode[0] = 'Gunther Episode';
        gunther_episode[1] = count_gunther;

        var non_gunther_episodes = []
        non_gunther_episodes[0] = 'Non Gunther Episodes';
        non_gunther_episodes[1] = json._embedded.episodes.length - count_gunther;

        var chart = c3.generate({
            bindto: '#myChart1',
            data: {
                columns: [
                    gunther_episode,
                    non_gunther_episodes
                ],
                type : 'pie',
                onclick: function (d, i) { console.log("onclick", d, i); },
                onmouseover: function (d, i) { console.log("onmouseover", d, i); },
                onmouseout: function (d, i) { console.log("onmouseout", d, i); }
            }
        });
    };



	// COMPLETE THE FOLLOWING FUNCTIONS BY IMPLEMENTING MAP, REDUCE, OR FILTER 
	// (or a combination) ON THE PROVIDED JSON DATA

	// Define the required ten functions below this line...

    // references: https://stackoverflow.com/questions/44387647/group-and-count-values-in-an-array
    //             https://stackoverflow.com/questions/1230233/how-to-find-the-sum-of-an-array-of-numbers
    //             https://www.w3schools.com/jsref/jsref_getfullyear.asp
    //             https://www.w3schools.com/jsref/jsref_obj_string.asp                    
    //             https://www.w3schools.com/jsref/jsref_obj_array.asp
    
    const getGuntherCount = function(json) {
        return json._embedded.episodes
                .reduce((count, episode) => {
                        if (episode.summary.includes("Gunther")) {
                            count++;
                        }
                        return count;  
                }, 0);
    }
    
    const getTotalRuntimeMinutes = function(json) {
        return json._embedded.episodes
                .reduce((total, episode) => {
                    total += episode.runtime;
                    return total;  
                }, 0);    
    }

    const getTotalEpisodesInYear = function(json, year) {
        return json._embedded.episodes
                .reduce((count, episode) => {
                    var airdate = episode.airdate.split("-");
                    if (airdate[0] === year) {
                        count++;
                    }
                    return count;  
                }, 0);    
    }

    const getFemaleCastMembers = function(json) {
        return json._embedded.cast
                .filter((cast) => cast.person.gender === "Female")
                .map((cast) => {
                    return cast.person.name;
                });
    }


    const getEpisodeTitles = function(json, name) {
        return json._embedded.episodes
                .filter((episode) => episode.summary.includes(name))
                .map((episode) => {
                    return episode.name;
                });
    }

    
    const getCastMembersOver55 = function(json) {   
        var dt = new Date();
        var yearOfOver55 = dt.getFullYear() - 55;

        return json._embedded.cast
                .filter((cast) => cast.person.birthday.substring(0, 4) < yearOfOver55)
                .map((cast) => {
                    return cast.person.name;
                });
    }

    const getTotalRuntimeMinutesExcludingSeasonSix = function(json) {   
        return json._embedded.episodes
                .reduce((total, episode) => {
                    if (episode.season != 6) {
                        total += episode.runtime;
                    }
                    return total;
                }, 0);
    }    

    const getFirstFourSeasons = function(json) {   
        return json._embedded.episodes
                .filter((episode) => episode.season < 5)
                .map((episode) => {
                    return {
                        season: episode.season, 
                        name: episode.name
                    }
                });
    }  

    const getEpisodeTallyBySeason = function(json) {   
        const tally = json._embedded.episodes
            .reduce((data, episode) => {
                var season = episode.season;
                if (!data.hasOwnProperty(season)) {
                    data[season] = 0;
                }
                data[season]++;
                return data;
            }, {});
        return tally;
    }  

    const capitalizeTheFriends = function(json) {
        var names = ["Joey", "Chandler", "Monica", "Rachel", "Phoebe", "Ross"];

        return json._embedded.episodes
                .map((episode) => {
                    var nameString = episode.name;
                    var summaryString = episode.summary;

                    names.some(name => {
                        nameString = nameString.replace(name, name.toUpperCase());
                    });

                    names.some(name => {
                        summaryString = summaryString.replace(name, name.toUpperCase());
                    });

                    return {       
                        name: nameString,
                        summary: summaryString
                    }                 
                });  
    }  
}