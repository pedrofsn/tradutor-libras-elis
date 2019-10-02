const mongoose = require('mongoose')

const TermSchema = {
	ptbr: String,
	elis: String
}

const Termo = mongoose.model('Termo', TermSchema)

module.exports = Termo