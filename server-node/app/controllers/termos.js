const Termo = require('../models/termo')

const findTerm = async (req, res) => {
    try {
        const termos = await Termo.find({})
        res.json(termos)
    } catch (err) {
        res.send(err)
    }
}

const createTerm = async (req, res) => {
    const { ptbr, elis } = req.body
    const newItem = { ptbr: ptbr.toLowerCase(), elis }
    try {
        await Termo.create(newItem)
        const termos = await Termo.find({})
        res.json(termos)
    } catch (err) {
        res.send(err)
    }
}

const deleteTerm = async (req, res) => {
    try {
        await Termo.deleteOne({ _id: req.params.termo_id })
        const termos = await Termo.find({})
        res.json(termos)
    } catch (err) {
        res.send(err)
    }
}

const searchTerm = async (req, res) => {
    // 0 - ELiS | 1 - PTBR
    const { caso, termo } = req.params.caso

    try {
        const findItem = (caso == 1)
            ? { ptbr: termo.toLowerCase() }
            : { elis: termo }

        const termos = await Termo.find(findItem)
        res.json(termos)
    } catch (err) {
        res.send(err)
    }
}

module.exports = {
    findTerm,
    createTerm,
    deleteTerm,
    searchTerm
}