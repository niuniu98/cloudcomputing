function MembersCtrl($scope, $http, Members) {
    // Define a reset function, that clears the prototype newMember object, and
    // consequently, the form
    $scope.reset = function() {
        // clear input fields
        $scope.newMember = {};
    };
    // Define a register function, which adds the member using the REST service,
    // and displays any error messages
    $scope.register = function() {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
        Members.save($scope.newMember, function(data) {

        	$scope.member = data;
            // mark success on the registration form
            $scope.successMessages = [ 'Member Registered' ];
            // Clear the form
            $scope.reset();
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
        });
    };
    // Initialize newMember here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();
}

function MembersListCtrl($scope, $http, Members) {
    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
    	        Members.get({ }, function(data) {
    	        	console.log(data);
    	        	$scope.membersEntity = data.customerEntity;
    	        });
    };
    // Initialize newMember here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.refresh();
}

function OrderCtrl($scope, $http, Books,Products,Members) {
	
	$scope.data = function(){
		Products.get({ }, function(data) {
        	console.log(data);
        	$scope.products = data.productEntity;
        });
		Members.get({ }, function(data) {
        	console.log(data);
        	$scope.members = data.customerEntity;
        });
	};
	
	 $scope.reset = function() {
	        // clear input fields
		 $scope.message="";
	     $scope.newProduct = {};
	    };
	    // Define a register function, which adds the member using the REST service,
	    // and displays any error messages
	    $scope.register = function() {
	        $scope.successMessages = '';
	        $scope.errorMessages = '';
	        Books.save($scope.newProduct, function(data) {

	            // mark success on the registration form
	            $scope.successMessages = [ 'Registered' ];
	            // Clear the form
	            $scope.reset();
	        }, function(result) {
	            if ((result.status == 409) || (result.status == 400)) {
	            } else {
	                $scope.errorMessages = [ 'Unknown  server error' ];
	            }
	            $scope.$apply();
	        });
	    };
	    
	    $scope.price = function(){
	    	$scope.message="";
			 Products.get({product:$scope.newProduct.productId}, function(data) {
				 $scope.message=data.value;
			 });
		 };
	    // Initialize newMember here to prevent Angular from sending a request
	    // without a proper Content-Type.
	    $scope.reset();
	    $scope.data();
}

function OrderListCtrl($scope, $http, Books) {
    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
    	 Books.get({ }, function(data) {
	        	console.log(data);
	        	$scope.books = data.bookEntity;
	        });
    };
    // Call the refresh() function, to populate the list of members
    $scope.refresh();
}

function ReportACtrl($scope,$http,Reports){
	 $scope.report = function(){
		 Reports.get({country:$scope.country}, function(data) {
			 console.log(data);
			 $scope.value = data.value;
	     });
		 
	 };
	 
	 
   
 }
