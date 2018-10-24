var fileApp = angular.module('fileApp', ['ui.router']);

fileApp.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

    // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'resource/pages/home/home.html'
        })


        .state('csvUpload', {
            url: '/csvUpload',
            templateUrl: 'resource/pages/csvUpload/csvUpload.html',
            controller: 'csvController'
        })


        .state('viewInfo', {
            url: '/viewInfo',
            templateUrl: 'resource/pages/showInfo/showInfo.html',
            controller: 'viewInfoController'
        });


});