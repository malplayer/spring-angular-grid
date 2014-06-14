'use strict';

/**
 * @author: Ajmal
 * 
 * Authenticates the user and access user role, based on the role appropriate
 * screen is displayed.
 */
var InitializationController = function($scope, $http, $rootScope, $location, Auth, AccessLevel) {

	$rootScope.cxrfCookie = "";
	$scope.rs = {};

	$scope.initializeUser = function() {
		
		$http.get('security/fetchuser').success(function(user) {
			
			// clean-up leading and trailing special characters.
			user.role = user.role.replace(/\s+$/, '');
			user.userName = user.userName.replace(/\s+$/, '');
			Auth.setUser(user);

			if(user.role === AccessLevel.supplier) {
				$location.path("supplier");	
				$rootScope.welcomeMessage = "I am a Supplier, going to approve few requests.";
			} else if(user.role === AccessLevel.member) {
				$location.path("member");	
				$rootScope.welcomeMessage = "I am a Member, ESM please work correctly :-).";
			} else if(user.role === AccessLevel.admin) {
				$location.path("admin");	
				$rootScope.welcomeMessage = "I am an Admin, may I help you.";
			}
				
			$http.get('security/fetchSsoAuthCookie').success(function(ssoAuthCookieResponse){
	            $rootScope.ssoAuthCookie = ssoAuthCookieResponse;
			});
			
			$http.get('security/fetchCxrfCookie').success(function(cxrfCookie) {
				$rootScope.cxrfCookie = cxrfCookie;
			});
		});
	};

	$scope.initializeUser();

};