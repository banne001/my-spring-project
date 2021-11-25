/**
 * Web Api to get the Actuator Endpoints
 * Using metrics/http.server.requests, mappings, and env
 * displays to the page
 *
 * @author Blezyl Santos
 * @version 11/24
 */
window.onload = function() {
    //access the API here...
    let config = {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        },
    };

    let uri = "http://localhost:8080/actuator/metrics/http.server.requests";
    //show the data on the page
    fetch(uri, config)
        .then((response) => {
            return response.json();
        })
        .then((json)=> {
            displayMetrics(json);
        });

    let uriMappings = "http://localhost:8080/actuator/mappings";
    fetch(uriMappings, config)
        .then((response) => {
            return response.json();
        })
        .then((json)=> {
            displayMappings(json);
        });

    let uriEnv = "http://localhost:8080/actuator/env";
    fetch(uriEnv, config)
        .then((response) => {
            return response.json();
        })
        .then((json)=> {
            displayEnv(json);
        });
}

/**
 * Displays the http requests to the page
 * @param jsonData the json data from the api
 */
function displayMetrics(jsonData){
    let contentArea = document.getElementById("metric");
    let measurements = jsonData.measurements;
    for (let i = 0; i < measurements.length; i++) {
        contentArea.innerHTML += "<h4>" + measurements[i].statistic + ": " + measurements[i].value + "</h4>";
    }

}

/**
 * Displays the mappings to the page
 * @param jsonData the json data from the api
 */
function displayMappings(jsonData){
    let mappings = jsonData.contexts.application.mappings.dispatcherServlets.dispatcherServlet;
    let contentArea = document.getElementById("maps");

    for (let i = 0; i < mappings.length; i++) {
        let method = mappings[i].details.requestMappingConditions.methods;
        if(method[0] === "GET"){
            contentArea.innerHTML += "<h4><span class='blue'>GET</span>";
        } else if(method[0] === "DELETE"){
            contentArea.innerHTML += "<h4><span class='red'>DELETE</span> ";
        } else if(method[0] === "POST"){
            contentArea.innerHTML += "<h4><span class='green'>POST</span>";
        } else {
            contentArea.innerHTML += "<h4><span class='yellow'>PUT</span>";
        }

        contentArea.innerHTML += " Pattern: " + mappings[i].details.requestMappingConditions.patterns + "</h4>";
    }
}

/**
 * Displays the environment to the page
 * @param jsonData the json data from the api
 */
function displayEnv(jsonData){

    let properties = jsonData.propertySources[4].properties;
    let contentArea = document.getElementById("env");
    console.log(properties);
    contentArea.innerHTML += "<h4>URL: " + properties["spring.datasource.url"].value + "</h4>";
    contentArea.innerHTML += "<h4>Username: " + properties["spring.datasource.username"].value + "</h4>";
    contentArea.innerHTML += "<h4>Driver Class Name: " + properties["spring.datasource.driver-class-name"].value + "</h4>";

}
