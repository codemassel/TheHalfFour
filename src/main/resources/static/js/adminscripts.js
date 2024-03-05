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

function populateShopitemData(userId) {
        var selectedShopitem = document.getElementById('userRow_' + shopitemId);

        var customerId = selectedShopitem.cells[0].innerText;
        var itemName = selectedShopitem.cells[1].innerText;
        var itemPrice = selectedShopitem.cells[2].innerText;
        var description = selectedShopitem.cells[3].innerText;
        document.getElementById('itemName').value = itemName;
        document.getElementById('itemPrice').value = itemPrice;
        document.getElementById('description').value = description;
}

function populateOrderData(userId) {
        var selectedUser = document.getElementById('orderRow_' + orderId);

        var orderId = selectedUser.cells[0].innerText;
        var customer = selectedUser.cells[1].innerText;
        var priority = selectedUser.cells[2].innerText;
        var status = selectedUser.cells[3].innerText;
        var discount = selectedUser.cells[4].innerText;
        var creationDate = selectedUser.cells[5].innerText;

        document.getElementById('customer').value = customer;
        document.getElementById('priority').value = priority;
        document.getElementById('status').value = status;
        document.getElementById('discount').value = discount;
        document.getElementById('creationDate').value = creationDate;
}

