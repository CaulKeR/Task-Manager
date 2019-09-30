function showPersons() {
    hideAllFormsExcept("personsForm");
    fetch("/api/persons", {
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (persons) {
                    document.getElementById("personsTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicPersonsTable").innerHTML, persons);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function showPersonPage(id) {
    hideAllFormsExcept("personPageForm");
    document.getElementById("hiddenPersonId").innerText = id;
    fetch("/api/persons/" + id + "/tasks", {
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (tasks) {
                    document.getElementById("personPageTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicPersonPageTable").innerHTML, tasks);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
    fetch("/api/persons/" + id, {
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (person) {
                    document.getElementById("personInfoPage").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicPersonInfoPage").innerHTML, person);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function showCreatePersonForm() {
    hideAllFormsExcept("createPersonForm");
}

function createPerson() {
    let person = {
        firstName: document.getElementById("createPersonFirstName").value,
        lastName: document.getElementById("createPersonLastName").value,
        patronymic: document.getElementById("createPersonPatronymic").value,
        login: document.getElementById("createPersonLogin").value,
        password: document.getElementById("createPersonPassword").value,
        deleteDate: null
    };
    fetch("/api/persons", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(person)
    })
        .then(function (res) {
            alert("Person created!");
            document.getElementById("createPersonFirstName").value = '';
            document.getElementById('createPersonLastName').innerText = '';
            document.getElementById("createPersonPatronymic").value = '';
            document.getElementById("createPersonLogin").value = '';
            document.getElementById("createPersonPassword").value = '';
            showPersons();
            return res.statusText;
        });
}

function editPersonInfo(id) {
    let person = {
        firstName: document.getElementById("editPersonFirstName").value,
        lastName: document.getElementById("editPersonLastName").value,
        patronymic: document.getElementById("editPersonPatronymic").value,
        login: document.getElementById("editPersonLogin").value,
        password: document.getElementById("editPersonPassword").value,
        deleteDate: null
    };
    fetch("/api/persons/" + id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(person)
    })
        .then(function (res) {
            alert("Person edited!");
            showPersonPage(id);
            return res.statusText;
        });
}