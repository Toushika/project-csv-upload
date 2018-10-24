fileApp.controller("csvController", function($scope) {
    $scope.uploadCsv=function () {
        var form = $('#fileUploadForm')[0];
        var data = new FormData(form);
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/fileUpload/csvUpload",
            data: data,
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);

            },
            error: function (e) {
                console.log("ERROR : ", e);

            }
        });


    }

});