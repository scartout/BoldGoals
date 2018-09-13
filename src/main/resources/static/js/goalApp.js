'use strict';

angular.module('goalApp', ['ngRoute', 'ngResource'])
.config(function ($routeProvider) {
	$routeProvider
	.when('/details/:id', {
		templateUrl: 'partials/goaldetails.html',
		controller: 'GoalDetailsController',
		controllerAs: 'detailsCtrl'
	});
})
.constant('GOAL_ENDPOINT', '/api/goals/:id')
.factory('Goal', function($resource, GOAL_ENDPOINT) {
	return $resource(GOAL_ENDPOINT);
})
.service('Goals', function(Goal) {
	this.getAll = function() {
		return Goal.query();
	}
	this.get = function(index) {
		return Goal.get({id: index});
	}
	this.add = function(goal) {
		goal.$save();
	}
	this.remove = function(index){
		return Goal.remove({id:index});
	}
})
.controller('GoalDetailsController', function($routeParams, Goals) {
	var vm = this;
	var goalIndex = $routeParams.id;
	vm.goal = Goals.get(goalIndex);
})
.controller('GoalController', function(Goals) {
	var vm = this;
	vm.goals = Goals.getAll();
	vm.remove = function (index){
		Goals.remove(index);
		vm.goals = _.without(vm.goals, _.findWhere(vm.goals, {
			  id: index
		}));
	}
	vm.sort = function(x) {
	    vm.sortt = x;
	}
})
.controller('GoalNewController', function(Goals, Goal) {
	var vm = this;
	vm.goal = new Goal();
	vm.saveGoal = function() {
		Goals.add(vm.goal);
		vm.goal = new Goal();
	}
	var today = new Date();
	vm.today = today.toISOString().slice(0,10);
});
