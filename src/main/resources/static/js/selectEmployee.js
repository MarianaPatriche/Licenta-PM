function sendData() {
    $.ajax({
        method: 'GET',
        url: '/allocate/getEmployees',
        data: {search: $('#employee').val()},
        success: function (data) {
            $('#employeeSelect option').remove();
            $('#employeeSelect').css('display', 'block');
            $('#employeeSelect').append("<option disabled='disabled' selected='selected'>Selecteaza angajatul</option>");
            for(var i = 0 ; i < data.length; i++){
                $('#employeeSelect').append("<option value='" + data[i].id + "'>" + data[i].username + "</option>");
            }
            $('#employeeSelect').change(function () {
               $('#employee').val(($("#employeeSelect option:selected").html()));
                $('#userId').val($("#employeeSelect option:selected").val());
            });
        }
    })
}
