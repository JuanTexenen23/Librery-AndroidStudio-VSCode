module.exports = app => {
  const book = require("../controllers/book.controller.js");

  var router = require("express").Router();

  // Create a new book
  router.post("/", book.create);

  // Retrieve all book
  router.get("/", book.findAll);

  // Retrieve a single book with id
  router.get("/:id", book.findOne);

  // Update a book with id
  router.put("/:id", book.update);

  // Delete a book with id
  router.delete("/:id", book.delete);

  // Delete all book
  router.delete("/", book.deleteAll);

  app.use('/api/book', router);
};