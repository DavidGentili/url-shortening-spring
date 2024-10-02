# Link Shortener

## Hola, ya que pasaste te comento la idea...

Bienvenido a este pequeño proyecto que hice para probar una simple implementación de Kotlin en Spring con MongoDB

Este proyecto es un servicio de acortamiento de links, mediante el almacenamiento del link original en una base de datos, asignando un código de url a cada registro

Permitiendo redireccionar las url que utilicen el código de acortamiento

Todavía no se encuentra desplegado pero dentro de poco voy a tirarlo en el droplet y compartir la url

### Facil de usar

Es mas que claro que es algo relativamente pequeño, por ende es super facil de usar

#### POST
Para crear una url acortada tengo que hacer un post al metodo
##### Request
```
path: /shorten
method: 'POST'
body: {
    "url": "http://example.org" 
}
```
##### Response
```
response: {
    "id": String,
    "url": String,
    "shortCode": String,
    "accessCount": Int,
    "createdAt": LocalDateTime,
    "updatedAt": LocalDateTime
}
```

#### PUT
Para actualizar una url acortada tengo que hacer una request como se muestra a continuacion
##### Request
```
path: /shorten/{code}
method: 'PUT'
body: {
    "url": "http://other-exaple.org" 
}

```
##### Response
```
response: {
    "id": String,
    "url": String,
    "shortCode": String,
    "accessCount": Int,
    "createdAt": LocalDateTime,
    "updatedAt": LocalDateTime
}
```

#### DELETE
Para eliminar una url acortada tengo que hacer una request como se muestra a continuacion
##### Request
```
path: /shorten/{code}
method: 'DELETE'
```

##### Response
```
response: {
    "id": String,
    "url": String,
    "shortCode": String,
    "accessCount": Int,
    "createdAt": LocalDateTime,
    "updatedAt": LocalDateTime
}
```

#### GET (Redireccionamiento)
Para hacer un redireccionamiento con una url acortada tengo que hacer una request como se muestra a continuacion
##### Request
```
path: /shorten/{code}
method: 'GET'
```

#### GET (Estadisticas)
Para hacer un redireccionamiento con una url acortada tengo que hacer una request como se muestra a continuacion
##### Request
```
path: /shorten/{code}/stats
method: 'GET'
```
##### Response
```
response: {
    "id": String,
    "url": String,
    "shortCode": String,
    "accessCount": Int,
    "createdAt": LocalDateTime,
    "updatedAt": LocalDateTime
}
```