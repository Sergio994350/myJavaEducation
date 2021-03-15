$(function () {

    const appendTask = function (data) {
        var taskCode = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name + '</a>' +
            '</span><a href="#" class="task-del" data-id="' + data.id + '">X</a><br>';
        $('#task-list').append('<div>' + taskCode + '</div>');

    };

    //Show adding task form
    $('#show-add-task-form').click(function () {
        $('#add-task-form').css('display', 'flex');
    });

    //Closing adding task form
    $('#add-task-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click', '.task-link', function () {
        var link = $(this);
        var taskId = link.data('id');
        $(this).css('pointer-events', 'none');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function (response) {
                var code = '<span>' + response.description + '</span><br>';
                link.parent().append(code);
            },
            error: function (response) {
                if (response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    // Deleting task
    $(document).on('click', '.task-del', function () {
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "DELETE",
            url: '/tasks/' + taskId,
            success: function (response) {
                alert("Задача № " + taskId + " удалена!");
                link.parent().remove();
            },
            error: function (response) {
                if (response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Adding task
    $('#save-task').click(function () {
        var data = $('#add-task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function (response) {
                $('#add-task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#add-task-form form').serializeArray();
                for (i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });

    //Clear list
    $('#clear-list').click(function () {
        $.ajax({
            method: "DELETE",
            url: '/tasks/all',
            caches: false,
            success: function (response) {
                alert("Лист очищен, обновите страницу!");
            },
            error: function (response) {
                if (response.status !== 200) {
                    alert('Внутренняя ошибка!');
                }
            }
        });
        return false;
    });
});