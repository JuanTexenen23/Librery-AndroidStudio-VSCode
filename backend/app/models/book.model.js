module.exports = (sequelize, Sequelize) => {
  const book = sequelize.define("book", {
    author: {
      type: Sequelize.STRING
    },
    title: {
      type: Sequelize.STRING
    },
    category: {
      type: Sequelize.STRING
    },
    descri: {
      type: Sequelize.STRING
    }
  }, { timestamps: false});

  return book;
};