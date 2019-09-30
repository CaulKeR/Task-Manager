function hideAllFormsExcept(form) {
    let forms = ["personsForm", "tasksForm", "tasksLogsForm", "personPageForm", "createTaskForm", "createPersonForm",
        "taskPageForm"];
    if (document.getElementById(form).style.display === 'block') {
        return;
    }
    for (let i = 0; i < forms.length; i++) {
        if (forms[i] === form) {
            document.getElementById(form).style.display = 'block';
        } else {
            document.getElementById(forms[i]).style.display = 'none';
        }
    }
}

window.onload = function () {
    showTasksLogs();
};