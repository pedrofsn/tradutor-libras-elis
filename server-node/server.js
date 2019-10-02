const express = require('express')
const app = express()
const morgan = require('morgan')
const bodyParser = require('body-parser')
const methodOverride = require('method-override')

const TermosRouter = require('./app/routes')

const translatePtBr = require('./language/ptbr')
const translateEn = require('./language/en')

const SET_MORGAN = process.env.SET_MORGAN || 'dev'

app.set('view engine', 'ejs')
app.use(express.static(__dirname + '/public'))
app.use(morgan(SET_MORGAN))
app.use(bodyParser.urlencoded({ extended: 'true' }))
app.use(bodyParser.json())
app.use(bodyParser.json({ type: 'application/vnd.api+json' })) // parse application/vnd.api+json as json
app.use(methodOverride())

app
    .use('/api/termos', TermosRouter)
    .get('/en', (req, res) => res.render('home', { translate: translateEn }))
    .get('*', (req, res) => res.render('home', { translate: translatePtBr }))

module.exports = app
