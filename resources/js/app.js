var app = angular.module('app', ['ui.grid']);

app.controller('StatsController', ['$scope', '$http', function ($scope, $http) {

    $scope.gridOptions = {
        enableSorting: true,
        columnDefs: [
            { name: 'League', field: 'leagueSeasonDivision.leagueSeason.league.name' },
            { name: 'Season', field: 'leagueSeasonDivision.leagueSeason.season.timeOfYear.name'},
            { name: 'Year', field: 'leagueSeasonDivision.leagueSeason.season.year'},
            { name: 'Division', field: 'leagueSeasonDivision.division.name' },
            { name: 'Team', field: 'team.name'}
        ]
    };

    $http.get('http://statscraper.elasticbeanstalk.com/player/teams?id=10').then(
        function(response) {
            $scope.gridOptions.data = response.data;
        },
        function(response) {

        }
    );

    $scope.search = function(id) {
        $http.get('http://statscraper.elasticbeanstalk.com/player/teams?id=' + id).then(
            function(response) {
                $scope.gridOptions.data = response.data;
            },
            function(response) {

            }
        );
    }
}]);



