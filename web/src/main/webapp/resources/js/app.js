'use strict';

var elibilityApp = angular.module('elibilityApp', [ 'ngResource','ngRoute','ngCookies','ngGrid',
		'elibilityApp.filters', 'elibilityApp.directives']);


// All user roles are defined as AccessLevel constant and throughout application roles are referred through this constant.
elibilityApp.constant('AccessLevel', {
    anyUser: 'role_anyUser', // Public part of the web site, that is accessible to any user.
    admin: 'role_admin',
    member: 'role_member',
    supplier: 'role_supplier'
});

elibilityApp.config([ '$routeProvider','AccessLevel', function($routeProvider,AccessLevel) {

	$routeProvider.when('/initialize', {
		templateUrl : 'security/authenticate',
		controller : InitializationController,
		authorization:  {
			accessLevels: [AccessLevel.anyUser]
	    }	
	});	
	
	$routeProvider.when('/member', {
		templateUrl : 'resources/html/member/member-layout.html',
		controller : MemberController,
		authorization:  {
			accessLevels: [AccessLevel.admin,AccessLevel.member]
	    }	
	});

	$routeProvider.when('/supplier', {
		templateUrl : 'resources/html/supplier/supplier-layout.html',
		controller : SupplierController,
		authorization:  {
			accessLevels: [AccessLevel.admin,AccessLevel.supplier]
	    }	
	});

	$routeProvider.when('/admin', {
		templateUrl : 'resources/html/admin/admin-layout.html',
		controller : AdminController,
		authorization:  {
			accessLevels: [AccessLevel.admin]
	    }	
	});

	$routeProvider.otherwise({
		redirectTo : '/unknownroute',
		authorization:  {
			accessLevels: [AccessLevel.anyUser]
	    }			
	});
	
} ]);


elibilityApp.run(['$rootScope', '$location', '$http','$templateCache','Auth','AccessLevel',

     function($rootScope,$location,$http,$templateCache,Auth,AccessLevel) {

		// Set a watch on the $routeChangeStart to make sure urls are secured for the role.
		$rootScope.$on('$routeChangeStart',

			function(evt, newRoute, currRoute) {
				if (!Auth.isAuthorized(newRoute.authorization.accessLevels)) {
					$location.path('/initialize');
				}
			}
		);

	    // Call when the 401 response is returned by the server, Authentication required.
	    $rootScope.$on('event:auth-loginRequired', 
	    	function(rejection) {
    			alert("Authentication required, system will send you to login flow."); // TODO - Add nice UI dialog.	    	
	    		Auth.resetUser();
	    		alert("Please login"); // TODO - Add nice UI dialog.
		        $location.path('/initialize');
	    	}
	    );

	    // Call when the 403 response is returned by the server, User is Forbidden for this request.
	    $rootScope.$on('event:auth-forbidden', 
	    	function(rejection) {
	    		alert("You are not allowed to access this request, system will send you to initial screen."); // TODO - Add nice UI dialog.
		        $location.path('/initialize');
	    	}
	    );	
	   
	    $rootScope.isUserRole = function(role) {
	    	return angular.equals(Auth.getUserRole(),role);
	    };
	    
	    // Assign the constant to root scope to make constant available in html.
	    $rootScope.AccessLevel = AccessLevel;
	    
		$location.path('/initialize');	

	}
]);



