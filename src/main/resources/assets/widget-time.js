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

function timestamp_to_dtstring(timestamp) {
    var date = new Date(timestamp * 1000);
    return date.toISOString().replace('T', ' ').slice(0, 19);
}

var WidgetTime = angular.module('WidgetTime', ['ngResource', 'ngRoute']);

WidgetTime.factory('LoadingHTTPInterceptor', ['$q', function ($q) {
    var loading_widget_visible = function (visible) {
        var loading_widget = document.getElementById('loading');
        loading_widget.style.display = visible ? 'block' : 'none';
    };
    var enable_submit_buttons = function (enabled) {
        var submit_buttons = document.querySelectorAll('form button[type="submit"], form input[type="submit"]');
        for (var i = 0; i < submit_buttons.length; i++) {
            submit_buttons[i].disabled = !enabled;
        }
    };
    return {
        request: function (config) {
            loading_widget_visible(true);
            enable_submit_buttons(false);
            return config;
        },

        requestError: function (rejection) {
            loading_widget_visible(false);
            enable_submit_buttons(true);
            return $q.reject(rejection);
        },

        response: function (response) {
            loading_widget_visible(false);
            enable_submit_buttons(true);
            return response;
        },

        responseError: function (rejection) {
            loading_widget_visible(false);
            enable_submit_buttons(true);
            return $q.reject(rejection);
        }
    };
}]).config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('LoadingHTTPInterceptor');
}]).factory('UnixTime', ['$resource', function ($resource) {
    return $resource('/api/v1/time');
}]).controller('TimeController', ['$scope', '$interval', 'UnixTime', '$http', function ($scope, $interval, UnixTime, $http) {
    $interval(function () {
        UnixTime.get(null, function (t) {
            $scope.datetime = timestamp_to_dtstring(t.timestamp);
        });
    }, 1000);
}]).filter('human_readable_timestamp', [function (input) {
    return timestamp_to_dtstring;
}]).factory('Widget', ['$resource', function ($resource) {
    return $resource('/api/v1/widget', null, {
        get: {
            method: 'GET',
            isArray: true
        }
    });
}]).controller('WidgetController', ['$scope', 'Widget', '$http', function ($scope, Widget, $http) {
    Widget.get(null, function (widgets) {
        $scope.widgets = widgets;
    });
    $scope.add = function (form_data) {
        var widget = new Widget(form_data);
        widget.$save().then(function () {
            $scope.widgets.push(widget);
        });
        return false;
    };
}]).config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/time', {
        controller: 'TimeController',
        templateUrl: 'partial-time.html'
    }).when('/widgets', {
        controller: 'WidgetController',
        templateUrl: 'partial-widget.html'
    }).otherwise({redirectTo: '/time'});
}]);
