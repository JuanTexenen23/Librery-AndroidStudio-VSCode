module.exports = {
  HOST: "localhost",
  USER: "root",
  PASSWORD: "As123456",
  DB: "librery",
  dialect: "mysql",
  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000
  }
};