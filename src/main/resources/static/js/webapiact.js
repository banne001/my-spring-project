/**
 * Web Api to get the Restaurants
 * and display to the page
 *
 * @author Blezyl Santos
 * @version 11/24
 */
window.onload = function() {
    //access the API here...
    let uri = "http://localhost:8080/activities";
    let config = {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        },
    };

    //show the data on the page
    fetch(uri, config)
        .then((response) => {
            return response.json();
        })
        .then((json)=> {
            displayDataElement(json);
        });
}

