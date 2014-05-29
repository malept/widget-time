// Compatibility code for IE < 9
// See: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date/toISOString
if (!Date.prototype.toISOString) {
  (function () {
    function pad(number) {
      if (number < 10) {
        return '0' + number;
      }
      return number;
    }

    Date.prototype.toISOString = function() {
      return this.getUTCFullYear() +
        '-' + pad(this.getUTCMonth() + 1) +
        '-' + pad(this.getUTCDate()) +
        'T' + pad(this.getUTCHours()) +
        ':' + pad(this.getUTCMinutes()) +
        ':' + pad(this.getUTCSeconds()) +
        '.' + (this.getUTCMilliseconds() / 1000).toFixed(3).slice(2, 5) +
        'Z';
    };
  }());
}

var WidgetTime = angular.module('WidgetTime', ['ngResource', 'ngRoute']);

WidgetTime.factory('UnixTime', ['$resource', function ($resource) {
    return $resource('/api/v1/time');
}]).controller('TimeController', ['$scope', '$interval', 'UnixTime', function ($scope, $interval, UnixTime) {
    $interval(function () {
        UnixTime.get(null, function (t) {
            var date = new Date(t.timestamp * 1000);
            $scope.datetime = date.toISOString().replace('T', ' ').slice(0, 19);
        });
    }, 1000);
}]);
