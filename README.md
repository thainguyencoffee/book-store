 # Bookstore
 
## Endpoints

| endpoint          | method   | req body         | resp body       | description                                                        |
|-------------------|----------|------------------|-----------------|--------------------------------------------------------------------|
| `/api/books`      | `GET`    |                  | `Page<BookDTO>` | Get all books                                                      |
| `/api/books`      | `POST`   | `BookRequestDTO` | `Long`          | Add a new book to the catalog, req body have to contain valid isbn |
| `/api/books/{id}` | `DELETE` |                  |                 | Delete a book                                                      |


### How to run

1. Clone the repository

```bash
git clone https://github.com/thainguyencoffee/bookstore.git
cd bookstore
```

2. Run docker compose (detect mode)
  
```bash
docker-compose up -d
```

3.Run spring boot with `local` profile
  
```bash
./gradlew bootRun -Dspring.profiles.active=local
```

4. Run angular

```bash
ng serve
```
