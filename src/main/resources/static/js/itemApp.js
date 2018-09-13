'use strict';

angular.module('itemApp', ['ngResource','ngRoute'])
.config(function ($routeProvider) {
	$routeProvider
	.when('/details/:id', {
		templateUrl: 'partials/itemdetails.html',
		controller: 'ItemDetailsController',
		controllerAs: 'detailsCtrl'
	});
})
.constant('ITEM_ENDPOINT', '/api/items/:id')
.factory('Item', function($resource, ITEM_ENDPOINT) {
	return $resource(ITEM_ENDPOINT);
})
.service('Items', function(Item,$http) {
	this.getAll = function() {
		return Item.query();
	}
	this.get = function(index) {
		return Item.get({id: index});
	}
	this.add = function(item) {
		item.$save();
	}
	this.remove = function(index){
		return Item.remove({id:index});
	}
	this.update = function(index) {
		return $http({
	        method : 'PATCH',
	        url : 'api/summary/' + index,
	        data : {
	            id: index
	        }
	    });
	}
})
.controller('ItemDetailsController', function($routeParams, Items) {
	var vm = this;
	var itemIndex = $routeParams.id;
	vm.item = Items.get(itemIndex);
})
.controller('ItemController', function($http,Items) {
	var vm = this;
	vm.items = Items.getAll();
	vm.remove = function (index){
		Items.remove(index);
		vm.items = _.without(vm.items, _.findWhere(vm.items, {
			  id: index
		}));
	}
	vm.update = function (index){
		Items.update(index);
		vm.items = Items.getAll();
	}
	vm.sort = function(x) {
	    vm.sortt = x;
	}
})
.controller('ItemNewController', function(Items, Item) {
	var vm = this;
	vm.item = new Item();
	vm.saveItem = function() {
		Items.add(vm.item);
		vm.item = new Item();
	}
	var today = new Date();
	vm.today = today.toISOString().slice(0,10);
});
