/*global angular */
'use strict';

/**
 * The main app module
 * @name app
 * @type {angular.Module}
 */
var app = angular.module('app', ['flow'])
    .config(['flowFactoryProvider', function (flowFactoryProvider) {
        flowFactoryProvider.defaults = {
            /*target: 'player/excel/upload',*/
            permanentErrors: [404, 500, 501],
            maxChunkRetries: 1,
            chunkRetryInterval: 5000,
            simultaneousUploads: 1,
            testChunks: false
        };
        flowFactoryProvider.on('catchAll', function (event) {
            console.log('catchAll', arguments);
        });
        // Can be used with different implementations of Flow.js
        // flowFactoryProvider.factory = fustyFlowFactory;
    }]);

app.service('AppService', ['$http', function($http){
	return {
		listPlayers: function (success){
			$http.get('player/listAll').success(success);
		},
		downloadPlayersExcel: function(){
			$.fileDownload('player/excel/download',{
				httpMethod: "POST"
			});
		}
	};
}]);

app.controller('AppController', ['$scope', 'AppService', function($scope, AppService) {
		$scope.errors = [];
		$scope.status = '';
		$scope.players = [];
    $scope.upload_success = function ($flow, $file, $message) {
	    $message = JSON.parse($message);
		    $scope.status = $message.status;
				if($scope.status === 'ERROR'){
					$scope.errors = $message.object;
				}else{
					$scope.errors = [];
				}
        $scope.listPlayers();
    };
	$scope.listPlayers = function(){
		AppService.listPlayers($scope.playersListed);
	};
	$scope.getDate = function(msDate){
		if(msDate == null) return "";
		return new Date(msDate);
	}
	$scope.playersListed = function(data){
		console.log(data);
		$scope.players = data;
	};
	$scope.listPlayers();

	$scope.exportExcel = function(){
		//console.log('exporting');
		AppService.downloadPlayersExcel();
	};

}]);
