function showTasks() {
    hideAllFormsExcept("tasksForm");
    fetch("/api/tasks", {
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
                    console.log(tasks);
                    document.getElementById("tasksTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicTasksTable").innerHTML, tasks);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function showCreateTaskForm() {
    hideAllFormsExcept("createTaskForm");
}

function createTaskForPerson() {
    let newTask = {
        title: document.getElementById("createTaskTitle").value,
        person: {
            id: document.getElementById('hiddenPersonId').innerText
        },
        status: document.getElementById("createTaskStatus").value,
        deleteDate: null
    };
    console.log(newTask.title);
    console.log(newTask.person.id);
    console.log(newTask.status);
    fetch("/api/tasks", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(newTask)
    })
        .then(function (res) {
            alert("Task created!");
            document.getElementById("createTaskTitle").value = '';
            document.getElementById('hiddenPersonId').innerText = '';
            document.getElementById("createTaskStatus").value = '';
            showPersonPage(newTask.person.id);
            return res.statusText;
        });
}

function showTaskPage(id) {
    hideAllFormsExcept("taskPageForm");
    document.getElementById("hiddenTaskId").innerText = id;
    fetch("/api/tasks/" + id, {
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (task) {
                    let statusIndex;
                    if (task.status === 'not started') {
                        statusIndex = 0;
                    } else {
                        if (task.status === 'in progress') {
                            statusIndex = 1;
                        } else {
                            statusIndex = 2;
                        }
                    }
                    document.getElementById("taskInfoPage").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicTaskInfoPage").innerHTML, task);
                    document.getElementById("editTaskStatus").selectedIndex = statusIndex;
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function editTaskStatus(id) {
    let taskLog = {
        comment: document.getElementById("editTaskStatusComment").value,
        task: {
            status: document.getElementById("editTaskStatus").options[document
                .getElementById("editTaskStatus").selectedIndex].value
        },
        spentTime: document.getElementById("editTaskStatusSpentTime").value
    };
    console.log(taskLog);
    fetch("/api/tasks/" + id + "/status", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(taskLog)
    })
        .then(function (res) {
            alert("Status edited!");
            document.getElementById("editTaskStatusComment").value = '';
            document.getElementById("editTaskStatusSpentTime").value = '';
            showTasks();
            return res.statusText;
        });
}