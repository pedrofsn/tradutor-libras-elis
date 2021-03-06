const express = require('express')
const router = express.Router()

const TermoController = require('../controllers/termos')

router
    .get('/', TermoController.findTerm)
    .post('/', TermoController.createTerm)
    .delete('/:termo_id', TermoController.deleteTerm)
    .get('/busca/:caso/:termo', TermoController.searchTerm)

module.exports = router
