var termo = angular.module('termo', []);

function mainController($scope, $http) {
	$scope.formData = {};
	$http
		.get('/api/termos')
		.success(data => $scope.termos = data)
		.error(data => console.log('Error: ' + data));

	$scope.createTermo = function () {
		$http
			.post('/api/termos', $scope.formData)
			.success(data => {
				$scope.formData = {};
				$scope.termos = data;
			})
			.error(data => console.log('Error: ' + data));
	};

	$scope.deleteTermo = function (id) {
		$http
			.delete('/api/termos/' + id)
			.success(data => $scope.termos = data)
			.error(data => console.log('Error: ' + data));
	};
}
