var termo = angular.module('termo', []);

function mainController($scope, $http) {
	$scope.formData = {};

	// when landing on the page, get all termos and show them
	$http.get('/api/termos')
		.success(function(data) {
			$scope.termos = data;
		})
		.error(function(data) {
			console.log('Error: ' + data);
		});

	// when submitting the add form, send the text to the node API
	$scope.createTermo = function() {
		$http.post('/api/termos', $scope.formData)
			.success(function(data) {
				$scope.formData = {}; // clear the form so our user is ready to enter another
				$scope.termos = data;
				console.log(data);
			})
			.error(function(data) {
				console.log('Error: ' + data);
			});
	};

	// delete a termo after checking it
	$scope.deleteTermo = function(id) {
		$http.delete('/api/termos/' + id)
			.success(function(data) {
				$scope.termos = data;
			})
			.error(function(data) {
				console.log('Error: ' + data);
			});
	};

}
