'use strict';

/* Services */

// Authorization service to store the Authorization data such as User credentials
// as well provides a bunch of methods to work on authorization.

elibilityApp.factory('Auth',
		
	function($cookieStore, AccessLevel) {
		var _user = $cookieStore.get('user');

		var setUser = function(user) {

			if (!user.role) {
				user.role = AccessLevel.anyUser;
			}
			_user = user;
			$cookieStore.put('user', _user);
		};
		
		var resetUser = function() {
			_user.role = AccessLevel.anyUser;
			_user.userName = "";
			$cookieStore.put('user', _user);
		};		
		
		var getUserRole =  function() {
			if(typeof(_user) != 'undefined' && _user != null) {
				if(typeof(_user.role) != 'undefined' && _user.role != null) {
					return _user.role;
				}
			}
			return '';
		};
		
		return {

			// Returns true if the logged in user's access level matches with any of the argument access level,
			// or if there is a 'anyUser' access level in the argument list. Argument is an array of access levels.
			isAuthorized: function(accessLevelsArg) {
				
				var loggedInUserRole = getUserRole();
				var found = false;
				angular.forEach(accessLevelsArg, function(accessLevelArgElement) {
					if(accessLevelArgElement === AccessLevel.anyUser || accessLevelArgElement === loggedInUserRole) {
						found = true;
					}
				});		

				return found;
			},
			resetUser: resetUser,
			setUser: setUser,
			isLoggedIn: function() {
				return _user && _user.ssoAuthCookie ? true : false;
			},
			getUser: function() {
				return _user;
			},			
			getUserRole:  getUserRole,
			getUserName:  function() {
				return _user ? _user.userName : '';
			},
			logout: function() {
				$cookieStore.remove('user');
				_user = null;
			}
		};
 	}
);

elibilityApp.factory('Price', function($resource) {

	var Price = $resource('pricelist/:priceid', {
		priceid : '@id'
	}, {
		update : {
			method : 'PUT'
		}
	});

	return Price;
});

