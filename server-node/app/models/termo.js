var mongoose = require('mongoose');

module.exports = mongoose.model('Termo', {
	text : String,
	done : Boolean
});