// Define the REST resource service, allowing us to interact with it as a high level service
angular.module('membersService', ['ngResource']).
    factory('Members', function($resource){
  return $resource('rest/members/:member', {});
});

angular.module('bookService', ['ngResource']).
	factory('Books', function($resource){
  return $resource('rest/bookings/:book', {});
});

angular.module('productService', ['ngResource']).
	factory('Products', function($resource){
  return $resource('rest/products/:product', {});
});

angular.module('reportService', ['ngResource']).
	factory('Reports', function($resource){
  return $resource('rest/reports/:country', {});
});