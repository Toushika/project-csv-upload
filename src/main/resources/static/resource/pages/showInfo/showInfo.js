fileApp.controller("viewInfoController", function ($scope) {
    $scope.IsVisible = false;

    $scope.viewAllInfo = function () {
        $scope.viewAllInfo = [];

        $.ajax({
            url: '/fileUpload/geAllInfo',
            type: 'POST',
            contentType: 'application/json',
            cache: false,
            processData: false,
            async: false,
            success: function (resultData) {
                $scope.viewAllInfo = resultData;
                console.log(resultData);

            },
            error: function (e) {
                console.log(e);
            }
        });
    }

    $scope.deleteInfo = function (rowId) {
        $scope.IsVisible = false;
        alert('Info has been deleted');
        $.ajax({
            url: '/fileUpload/deleteInfo/' + rowId,
            type: 'POST',
            contentType: 'application/json',
            cache: false,
            processData: false,
            async: false,
            success: function (data) {
                console.log(data);
                window.location.reload();

            },
            error: function (e) {
                console.log(e);

            }
        });

    }

    $scope.editInfo = function (viewInfo) {
        var id = viewInfo.rowId;
        $scope.IsVisible = true;
        $scope.editInfoDetails = [];
        $scope.editInfoDetails = viewInfo;

        angular.element(document).ready(function () {
            $("#dialog").dialog({
                autoOpen: true,
                width: 750,

            });
        });
        /*   console.log(editMovieDetails);*/
    }

    $scope.editAllInfo = function (editInfoDetails) {
        console.log("editInfoDetails:: " + editInfoDetails.toString());

        var rowId = editInfoDetails.rowId;
        var name = editInfoDetails.name;
        var email = editInfoDetails.email;
        var phone = editInfoDetails.phone;
        console.log(name + " " + email + " " + phone)
        alert('Info has been updated');

        $.ajax({
            url: '/fileUpload/editInfo/' + rowId,
            type: 'POST',
            data: JSON.stringify({"name": name, "email": email, "phone": phone}),
            contentType: 'application/json',
            cache: false,
            processData: false,
            async: false,
            success: function (data) {
                console.log(data);
                window.location.reload();

            },
            error: function (e) {
                console.log(e);

            }
        });

    }


});