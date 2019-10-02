const mongoose = require('mongoose')

const TermSchema = {
	item: String,
	elis: String
}

const Termo = mongoose.model('Termo', TermSchema)

module.exports = Termo