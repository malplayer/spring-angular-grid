'use strict';

/**
 * MemberController
 * @constructor
 */
var MemberController = function($scope, $http, $rootScope) {
    $scope.rs = {};
    $scope.editMode = false;


    $scope.fetchMembersList = function() {
        $http.get('members/memberlist.json').success(function(rsList){
            $scope.members = rsList;
        });
    };

    $scope.addNewMember = function(rs) {

        $scope.resetError();

        $http.post('members/add', rs).success(function() {
            $scope.fetchMembersList();
            $scope.rs.name = '';
            $scope.rs.facility.name = '';
            $scope.rs.facility.identification = '';
            $scope.rs.facility.apexus = false;
        }).error(function() {
            $scope.setError('Could not add a new station');
        });
    };

    $scope.updateMember = function(rs) {
        $scope.resetError();

        $http.put('members/update', rs).success(function() {
            $scope.fetchMembersList();
            $scope.rs.name = '';
            $scope.rs.facility.name = '';
            $scope.rs.facility.identification = '';
            $scope.rs.facility.apexus = false;
            $scope.editMode = false;
        }).error(function() {
            $scope.setError('Could not update the facility');
        });
    };

    $scope.editMember = function(rs) {
        $scope.resetError();
        $scope.rs = rs;
        $scope.editMode = true;
    };

    $scope.removeMember = function(id) {
        $scope.resetError();

        $http['delete']('members/remove/' + id).success(function() {
            $scope.fetchMembersList();
        }).error(function() {
            $scope.setError('Could not remove facility');
        });
        
        $scope.rs = '';
    };

    $scope.removeAllMembers = function() {
        $scope.resetError();

        $http['delete']('members/removeAll').success(function() {
            $scope.fetchMembersList();
        }).error(function() {
            $scope.setError('Could not remove all Members');
        });

    };

    $scope.resetMemberForm = function() {
        $scope.resetError();
        $scope.rs = {};
        $scope.editMode = false;
    };

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };
    
    $scope.fetchMembersList();

};