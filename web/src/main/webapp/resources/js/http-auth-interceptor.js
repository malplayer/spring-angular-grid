'use strict';

/*
 * @author: ajmal
 * 
 * All security interceptor related code, This code intercepts all http requests and if authentication 
 * or authorization errors come back propagates appropriate error events. Also adds Sso cookie to each http request.
 * 
 * https://github.com/philipsorst/angular-rest-springsecurity/blob/master/src/main/webapp/js/app.js
 * https://github.com/jhipster/jhipster-sample-app.git 
 **/


// service to intercepts http calls.
elibilityApp.factory('http-auth-interceptor', function($q, $rootScope, Auth) {
	
	// Append the SSO cookie to the request header.
	var extendHeaders = function(config) {
		config.headers = config.headers || {}; 
		config.headers['SsoAuthorizationCookie'] =  $rootScope.ssoAuthCookie;
	};
	
  return {
	      
    'request': function(config) {
      // Add the SSO cookie header.
    	extendHeaders(config);
    	return config || $q.when(config);
    },

   'requestError': function(rejection) {
      return $q.reject(rejection);
    },

    'response': function(response) {
      return response || $q.when(response);
    },

   'responseError': function(rejection) {
	   
	   // Handle errors
	   switch(rejection.status) {
	   	case 401:  // Unauthenticated request
   			$rootScope.$broadcast('event:auth-loginRequired');
    		break;
	   	case 403:  // Forbidden request, authorization error
	   		$rootScope.$broadcast('event:auth-forbidden');
	   		break;
	   
	   	case 404:  // Page not found
	   		$rootScope.$broadcast('event:page-notFound');
	   		break;
	   
	   	case 500:  // Server error
	   		$rootScope.$broadcast('event:server-error');
	   		break;
	   	}	   
	   return $q.reject(rejection);
    }
  };
});

// Add security interception to all http calls.
elibilityApp.config(['$httpProvider', 
    function (httpProvider) {
    	httpProvider.interceptors.push('http-auth-interceptor');    
	}
]);
