// JavaScript-Funktion, die aufgerufen wird, wenn eine Tabellenzeile geklickt wird

function populateUserData(userId) {
        var selectedUser = document.getElementById('userRow_' + userId);

        var customerId = selectedUser.cells[0].innerText;
        var firstName = selectedUser.cells[1].innerText;
        var lastName = selectedUser.cells[2].innerText;
        var emailId = selectedUser.cells[3].innerText;
        var zipcode = selectedUser.cells[4].innerText;
        var city = selectedUser.cells[5].innerText;
        var street = selectedUser.cells[6].innerText;
        var houseNumber = selectedUser.cells[7].innerText;

        document.getElementById('firstName').value = firstName;
        document.getElementById('lastName').value = lastName;
        document.getElementById('emailId').value = emailId;
        document.getElementById('zipcode').value = zipcode;
        document.getElementById('city').value = city;
        document.getElementById('street').value = street;
        document.getElementById('houseNumber').value = houseNumber;
        document.getElementById('customerId').value = customerId;
    }

