````$xslt
1. Показать весь список дел:
http://127.0.0.1:8080/tasks
method: GET
````
````$xslt
2. Добавить дело
http://127.0.0.1:8080/tasks/?name=<<name>>&description=<<description>>
method: POST
````
```$xslt
3. Удалить дело
http://127.0.0.1:8080/tasks/{id}
method: DELETE
```
````$xslt
4. Очистить весь список
http://127.0.0.1:8080/tasks/all
method: DELETE
````
````$xslt
5. Показать конкретное дело по id
http://127.0.0.1:8080/tasks/{id}
method: GET
````
````$xslt
6. Обновить существующую запись по id
http://127.0.0.1:8080/tasks/{id}/?name=<<name>>&description=<<description>>
method: PUT
params (required): name, description
````