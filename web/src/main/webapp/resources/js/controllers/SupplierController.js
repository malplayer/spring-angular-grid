var SupplierController = function($scope,$routeParams, $location,$http, Price) {
	
	
    $scope.fetchPriceList = function() {
        $scope.resetError();
        $scope.priceList = Price.query();
    };

    $scope.addNewPrice = function() {

        $scope.resetError();
        
        $scope.price.$save(function (price, headers) {     
           $scope.fetchPriceList();
           $scope.resetPriceListForm();
           toastr.success("Price Added.");
        });

    };
    

    $scope.updatePrice = function(price) {

    	$scope.resetError();

        price.$update(function (price, headers) {     
            $scope.fetchPriceList();
            $scope.resetPriceListForm();
            toastr.success("Price updated.");
         });
    };

    $scope.editPrice = function(price) {
        $scope.resetError();
        $scope.price = price;
        $scope.editMode = true;
    };

    $scope.removePrice = function(price) {
        $scope.resetError();
        
        price.$delete(function (price, headers) {     
            $scope.fetchPriceList();
            toastr.success("Price deleted.");
         });        

    };

    $scope.removeAllPrice = function() {
        $scope.resetError();


        $http['delete']('pricelist/removeAll').success(function() {
            $scope.fetchPriceList();
            toastr.success("Prices removed.");
        }).error(function() {
            $scope.setError('Could not remove all Prices');
        });

    };

    $scope.resetPriceListForm = function() {
        $scope.resetError();
    	$scope.price = new Price();    
    	$scope.price.name  = "";
    	$scope.price.tiers = [{"name":""}];
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
    

    $scope.resetPriceListForm();
    $scope.fetchPriceList();

    

};
