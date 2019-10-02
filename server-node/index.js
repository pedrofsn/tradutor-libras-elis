require('dotenv').config()
const mongoose = require('mongoose')
const app = require('./server')

const port = process.env.PORT || 8080
const MONGOSERVER = process.env.MONGOSERVER || 'mongodb://localhost:27017/tradutor-libras'

mongoose
    .connect(MONGOSERVER, { useNewUrlParser: true, useUnifiedTopology: true })
    .then(() => app.listen(port, () => console.log('Libra Translate Server Running on ' + port)))
    .catch((err) => console.log(err))