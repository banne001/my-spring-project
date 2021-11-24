window.onload = function() {
    //access the API here...
    let uri = "http://localhost:8080/restaurants";
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

function displayDataElement(jsonData){
    console.log(jsonData);
    let contentArea = document.getElementById("tableBody");

    for(let i = 0; i < jsonData.length; i++){
        addData(contentArea, jsonData[i]);
    }
}

function addData(contentArea, data){
    let row = contentArea.insertRow(-1);

    let name = row.insertCell(0);
    let address = row.insertCell(1);
    let city = row.insertCell(2);
    let rating = row.insertCell(3);

    name.innerHTML = data.name;
    address.innerHTML = data.address;
    city.innerHTML = data.city;
    rating.innerHTML = data.stars;
}
