var express = require('express');
var app = express();
var fs = require("fs");

var bodyParser = require('body-parser')
app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({
    extended: true
}));

//Arbitrary isbn manager since we don't use a database
var index = 5;

// Initializing books Array.. It will behave like a dummy database 
var books = [{
    "isbn": 1,
    "name": "100 años de soledad",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan quis justo quis hendrerit. Curabitur a ante neque. Fusce nec mauris sodales, auctor sem at, luctus eros. Praesent aliquam nibh neque. Duis ut suscipit justo, isbn consectetur orci. Curabitur ultricies nunc eu enim dignissim, sed laoreet odio blandit.",
    "editorial" : "Planeta"
}, {
    "isbn": 2,
    "name": "Android Cookbook",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan quis justo quis hendrerit. Curabitur a ante neque. Fusce nec mauris sodales, auctor sem at, luctus eros. Praesent aliquam nibh neque. Duis ut suscipit justo, isbn consectetur orci. Curabitur ultricies nunc eu enim dignissim, sed laoreet odio blandit.",
    "editorial" : "O Reilly"
}, {
    "isbn": 3,
    "name": "Node js. Javascript in server sisbne",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan quis justo quis hendrerit. Curabitur a ante neque. Fusce nec mauris sodales, auctor sem at, luctus eros. Praesent aliquam nibh neque. Duis ut suscipit justo, isbn consectetur orci. Curabitur ultricies nunc eu enim dignissim, sed laoreet odio blandit.",
    "editorial" : "O Reilly"
}, {
    "isbn": 4,
    "name": "Androisbn with Kotlin",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan quis justo quis hendrerit. Curabitur a ante neque. Fusce nec mauris sodales, auctor sem at, luctus eros. Praesent aliquam nibh neque. Duis ut suscipit justo, isbn consectetur orci. Curabitur ultricies nunc eu enim dignissim, sed laoreet odio blandit.",
    "editorial" : "Practical"
}, {
    "isbn": 5,
    "name": "Rich Dad, Poor Dad",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan quis justo quis hendrerit. Curabitur a ante neque. Fusce nec mauris sodales, auctor sem at, luctus eros. Praesent aliquam nibh neque. Duis ut suscipit justo, isbn consectetur orci. Curabitur ultricies nunc eu enim dignissim, sed laoreet odio blandit.",
    "editorial" : "Practical"
}]

var message = "Welcome to Bookland";

app.get('/messages', function (req, result) {
    result.end(JSON.stringify(message));
})

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*"); // update to match the domain you will make the request from
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

// Get the list of books, convert it to JSON and send it back to client 
app.get('/book', function (req, result) {
    var count = req.query.count != undefined ? req.query.count : req.query.count = 100;
    if(req.query.editorial){
        var bookSpots = books.filter(function(book) {
            return book.editorial == req.query.editorial
        });
        result.end(JSON.stringify(bookSpots.slice(0, count)));
    }
    
    result.end(JSON.stringify(books.slice(0, count)));
})

// Get one particular book using isbn 
app.get('/book/:isbn', function (req, result) {
    for (var i = 0; i < books.length; i++) {
        if(books[i].isbn == req.params.isbn){
            result.end(JSON.stringify(books[i]));
        }
    }
})

// Create a new book and add it to existing books list 
app.post('/book', function (req, result) {
    var newbook = {
        "name": req.body.name,
        "description": req.body.description,
        "editorial" : req.body.editorial,
        "isbn": index + 1
    }

    index++;

    books.push(newbook);
    result.status(201).end(JSON.stringify(newbook));
})

// Update a book 
app.put('/book/:isbn', function (req, result) {
    var book;
    for (var i = 0; i < books.length; i++) {
        if(books[i].isbn == req.params.isbn){
            books[i].name = req.body.name;
            books[i].editorial = req.body.editorial;
            books[i].description = req.body.description;
            book = books[i];
        }
    }

    result.end(JSON.stringify(book));
})

// Delete a book 
app.delete('/book/:isbn', function (req, result) {
    for (var i = 0; i < books.length; i++) {
        if(books[i].isbn == req.params.isbn){
            books.splice(i, 1);
            result.status(204).end(JSON.stringify(books[i]));
        }
    }
});

// Home page 
app.get('/', (req, result) => result.send('It works!! Enjoy your server'))


var server = app.listen(9000, '192.168.100.51', function (req, result) {

    var host = server.address().address
    var port = server.address().port

    console.log(`Server running at http://${host}:${port}/`);
})

