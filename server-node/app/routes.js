var Termo = require('./models/termo');

module.exports = function(app) {

	// api ---------------------------------------------------------------------
	// get all termos
	app.get('/api/termos', function(req, res) {

		// use mongoose to get all termos in the database
		Termo.find(function(err, termos) {

			// if there is an error retrieving, send the error. nothing after res.send(err) will execute
			if (err)
				res.send(err)

			res.json(termos); // return all termos in JSON format
		});
	});

	// create termo and send back all termos after creation
	app.post('/api/termos', function(req, res) {

		// create a termo, information comes from AJAX request from Angular
		Termo.create({
			ptbr : req.body.ptbr,
			elis : req.body.elis
		}, function(err, termo) {
			if (err)
				res.send(err);

			// get and return all the termos after you create another
			Termo.find(function(err, termos) {
				if (err)
					res.send(err)
				res.json(termos);
			});
		});

	});

	// delete a termo
	app.delete('/api/termos/:termo_id', function(req, res) {
		Termo.remove({
			_id : req.params.termo_id
		}, function(err, termo) {
			if (err)
				res.send(err);

			// get and return all the termos after you create another
			Termo.find(function(err, termos) {
				if (err)
					res.send(err)
				res.json(termos);
			});
		});
	});

	// application -------------------------------------------------------------
	app.get('*', function(req, res) {
		res.sendfile('./public/index.html'); // load the single view file (angular will handle the page changes on the front-end)
	});
};