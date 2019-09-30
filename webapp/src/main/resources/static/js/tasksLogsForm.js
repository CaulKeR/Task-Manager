function showTasksLogs() {
    hideAllFormsExcept("tasksLogsForm");
    fetch("/api/tasks_logs", {
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(
            function (response) {
                if (response.status < 200 || response.status >= 400) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);
                }
                response.json().then(function (tasksLogs) {
                    console.log(tasksLogs);
                    document.getElementById("tasksLogsTable").innerHTML = Mustache.to_html(document
                        .getElementById("dynamicTasksLogsTable").innerHTML, tasksLogs);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}