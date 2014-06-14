var AdminController = function($scope, $http, $rootScope) {
	
	var currentFilterValues = {
	    id:"",
	    name :"",
	    alliance: "",
	    exitingContract: "",
	    exitingTier: "" ,	    
	    dea: "",
	    license: ""
	};
	
    $scope.externalFilters = {
        id: "",
        name: "",  
        alliance: "",
	    exitingContract: "",
	    exitingTier: "" ,	    
	    dea: "",
	    license: ""
    };
    
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: false
    };
        
    $scope.totalServerItems = 0;
    
    $scope.pagingOptions = {
        pageSizes: [5, 10, 20, 40, 60, 80, 100],
        pageSize: 10,
        currentPage: 1
    };
    
    var filterInputCapturePlugin = {
            init: function(scope, grid) {
                filterInputCapturePlugin.scope = scope;
                filterInputCapturePlugin.grid = grid;
                $scope.$watch(function() {
                    angular.forEach(filterInputCapturePlugin.scope.columns, function(col) {
                    	
                    	var value = "";
                    	if(angular.isDefined(col.filterText)) {
                    		value = col.filterText;
                    	}
                        if (col.visible) {
                            if(col.field === "id") {
                            	$scope.externalFilters.id = value;
                            }
                            if(col.field === "name") {
                            	$scope.externalFilters.name = value;
                            } 
                            if(col.field === "alliance") {
                            	$scope.externalFilters.alliance = value;
                            }     
                            if(col.field === "existingContract") {
                            	$scope.externalFilters.exitingContract = value;
                            }    
                            if(col.field === "existingTier") {
                            	$scope.externalFilters.exitingTier = value;
                            }    
                            if(col.field === "dea") {
                            	$scope.externalFilters.dea = value;
                            }  
                            if(col.field === "license") {
                            	$scope.externalFilters.license = value;
                            }    
                        }
                        
                        if($scope.isFilterChanged()) {
                        	$scope.performNewSearch();
                        }
                    }); 
                });
            },
            scope: undefined,
            grid: undefined,
        };
	
    $scope.gridOptions = {
        data: 'myData',
        enablePaging: true,
        showFooter: true,
        totalServerItems:'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        plugins: [filterInputCapturePlugin],
        headerRowHeight: 60, // give room for filter bar
        
        columnDefs: [{ field: 'id', displayName: 'ID', cellClass: 'facilityGridDataColumn', width: "10%", headerClass: 'facilityGridColumnHeader',  resizable: false, 
        									headerCellTemplate: 'resources/html/admin/filterHeaderTemplate.html'},
                     { field: 'name', displayName: 'Name', cellClass: 'facilityGridDataColumn', width: "28%", headerClass: 'facilityGridColumnHeader',
        									headerCellTemplate: 'resources/html/admin/filterHeaderTemplate.html'},
                     { field: 'alliance', displayName: 'Alliance',  width: "10%", headerClass: 'facilityGridColumnHeader',
        									headerCellTemplate: 'resources/html/admin/filterHeaderTemplate.html'},
                     { field: 'existingContract', displayName: 'Existing Contract', width: "14%", headerClass: 'facilityGridColumnHeader',
        										cellTemplate: '<div><img class="selectFacilityGroupExistingTypeImage" src="resources/media/cross.jpeg"></div>' }, 
                     { field: 'existingTier', displayName: 'Existing Tier', width: "14%", headerClass: 'facilityGridColumnHeader',
        										cellTemplate: '<div><img class="selectFacilityGroupExistingTypeImage" src="resources/media/cross.jpeg"></div>' },
                     { field: 'dea', displayName: 'DEA', width: "14%", headerClass: 'facilityGridColumnHeader',
                    	 					headerCellTemplate: 'resources/html/admin/filterHeaderTemplate.html'},
                     { field: 'license', displayName: 'License', width: "10%", headerClass: 'facilityGridColumnHeader',
                    	 					headerCellTemplate: 'resources/html/admin/filterHeaderTemplate.html'}]
    };   
    
    trim = function(val) {
        return val.replace(/^\s+|\s+$/g, "");
    };

    $scope.isFilterChanged = function() {
    	if(trim($scope.externalFilters.id) == "" && trim($scope.externalFilters.name) == "" && 
    			trim(currentFilterValues.alliance) == "" && trim(currentFilterValues.exitingContract) == "" &&
    			trim(currentFilterValues.exitingTier) == "" && trim(currentFilterValues.dea) == "" &&
    			trim(currentFilterValues.license) == "") {
    		return false;
    	} else {
        	if(angular.equals(trim($scope.externalFilters.id), trim(currentFilterValues.id)) &&
        			angular.equals(trim($scope.externalFilters.name), currentFilterValues.name) &&
        			angular.equals(trim($scope.externalFilters.alliance), currentFilterValues.alliance) &&
        			angular.equals(trim($scope.externalFilters.exitingContract), currentFilterValues.exitingContract) &&
        			angular.equals(trim($scope.externalFilters.exitingTier), currentFilterValues.exitingTier) &&
        			angular.equals(trim($scope.externalFilters.dea), currentFilterValues.dea) &&
        			angular.equals(trim($scope.externalFilters.license), currentFilterValues.license)) {
        		return false;
        	} else {
        		currentFilterValues.id = $scope.externalFilters.id;
        		currentFilterValues.name = $scope.externalFilters.name;
        		currentFilterValues.alliance = $scope.externalFilters.alliance;
        		currentFilterValues.exitingContract = $scope.externalFilters.exitingContract;
        		currentFilterValues.exitingTier = $scope.externalFilters.exitingTier;
        		currentFilterValues.dea = $scope.externalFilters.dea;
        		currentFilterValues.license = $scope.externalFilters.license;
        		
        		return true;
        	}
    	}
    };

    $scope.setPagingData = function(data){	
        $scope.myData = data;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };

    $scope.performNewSearch = function () {
        setTimeout(function () {
    		$scope.pagingOptions.currentPage = 1;
            $http.get('facilities/getNumberOfFacilities', {
            	params: {id: trim($scope.externalFilters.id), name: trim($scope.externalFilters.name)  }
            }).success(function (numberOfFacilities) {
            	$scope.totalServerItems = numberOfFacilities;
            	$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            });
        }, 100);
    };    

    $scope.getPagedDataAsync = function (pageSize, currentPage) {
        setTimeout(function () {
            $http.get('facilities/getFacilities', {
            	params: { pageSize: pageSize, currentPage : currentPage, id: trim($scope.externalFilters.id), name: trim($scope.externalFilters.name)  }
            }).success(function (facilitiesList) {
                $scope.setPagingData(facilitiesList);
            });
        }, 100);
    };

    $scope.performNewSearch();

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
			if(newVal.pageSize !== oldVal.pageSize || $scope.isFilterChanged()) {
				$scope.performNewSearch();
			} else {
				$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
			}
        }
    }, true);    


};