// JavaScript-Funktion, die aufgerufen wird, wenn eine Tabellenzeile geklickt wird
function handleTableRowClick(customerId) {
    fetch('/getCustomerById/' + customerId)
        .then(response => response.json())
        .then(data => {
            // Aktualisiere die Formfelder mit den Benutzerdaten
            document.getElementById("firstName").value = data.firstName;
            document.getElementById("lastName").value = data.lastName;
            document.getElementById("emailId").value = data.emailId;
            document.getElementById("zipcode").value = data.zipcode;
            document.getElementById("street").value = data.street;
            document.getElementById("houseNumber").value = data.houseNumber;
        })
        .catch(error => console.error('Fehler beim Laden der Benutzerdaten:', error));
}

// JavaScript-Funktion, um die Daten des aktualisierten Benutzers an den Server zu senden
function updateUser() {
    // Sammle die Daten aus der Form
    var formData = new FormData(document.getElementById("userForm"));

    // Sende die Daten über einen Fetch-POST-Request an den Server
    fetch('/updateCustomer', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        alert('Benutzer erfolgreich aktualisiert!');
    })
    .catch(error => {
        alert('Fehler beim Aktualisieren des Benutzers: ' + error);
    });
}

// JavaScript-Funktion, um die Formularfelder mit den Daten des ausgewählten Benutzers zu aktualisieren
function updateFormFields() {
    // Ausgewählten Benutzer abrufen
    var selectedUserId = document.getElementById("selectedUser").value;

    // Daten des ausgewählten Benutzers aus der customers-Liste finden
    var selectedUser = null;
    for (var i = 0; i < customers.length; i++) {
        if (customers[i].id == selectedUserId) {
            selectedUser = customers[i];
            break;
        }
    }

    // Formularfelder mit den Daten des ausgewählten Benutzers aktualisieren
    if (selectedUser) {
        document.getElementById("firstName").value = selectedUser.firstName;
        document.getElementById("lastName").value = selectedUser.lastName;
        document.getElementById("emailId").value = selectedUser.emailId;
        document.getElementById("zipcode").value = selectedUser.zipcode;
        document.getElementById("street").value = selectedUser.street;
        document.getElementById("houseNumber").value = selectedUser.houseNumber;
    }
}
function populateUserData(userId) {
    fetch('/getCustomerById/' + userId)
        .then(response => response.json())
        .then(data => {
            // Daten des Benutzers in die entsprechenden Formularfelder einfügen
            document.getElementById("firstName").value = data.firstName;
            document.getElementById("lastName").value = data.lastName;
            document.getElementById("emailId").value = data.emailId;
            document.getElementById("zipcode").value = data.zipcode;
            document.getElementById("street").value = data.street;
            document.getElementById("houseNumber").value = data.houseNumber;
        })
        .catch(error => console.error('Fehler beim Laden der Benutzerdaten:', error));
}

// Event-Listener hinzufügen, um die Formularfelder zu aktualisieren, wenn ein Benutzer ausgewählt wird
document.getElementById("selectedUser").addEventListener("change", updateFormFields);
