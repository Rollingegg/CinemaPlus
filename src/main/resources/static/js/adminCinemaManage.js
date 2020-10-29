var halls = [];
var selectedSeats = [];

$(document).ready(function() {

    if (sessionStorage.getItem('role') == 'user') {
        window.location.href = '/user/home'
    } else if (sessionStorage.getItem('role') == 'staff') {
        $('#setting-refund').hide();
        $('#setting-halls').hide();
    }


    var canSeeDate = 0;
    var allRefund = 0;
    var partRefund = 0;

    getCanSeeDayNum();
    getCinemaHalls();
    getAllRefundHour();
    getPartRefundHour();


    function getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            function (res) {
                canSeeDate = res.content;
                $("#can-see-num").text(canSeeDate);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getAllRefundHour() {
        getRequest(
            '/ticket/refund/all',
            function (res) {
                allRefund = res.content;
                $("#all-refund-time").text(allRefund);
                $("#part-refund-start").text(allRefund);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getPartRefundHour() {
        getRequest(
            '/ticket/refund/part',
            function (res) {
                partRefund = res.content;
                $("#part-refund-end").text(partRefund);
                $("#none-refund-time").text(partRefund);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }


    $('#canview-modify-btn').click(function () {
       $("#canview-modify-btn").hide();
       $("#canview-set-input").val(canSeeDate);
       $("#canview-set-input").show();
       $("#canview-confirm-btn").show();
    });

    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day:dayNum},
            function (res) {
                if(res.success){
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    $('#all-refund-modify-btn').click(function () {
        $("#all-refund-modify-btn").hide();
        $("#all-refund-set-input").val(allRefund);
        $("#all-refund-set-input").show();
        $("#all-refund-confirm-btn").show();
    });

    $('#all-refund-confirm-btn').click(function () {
        var setHour = $("#all-refund-set-input").val();
        // 验证一下是否为数字
        if (!isNaN(setHour)){
            postRequest(
                '/ticket/refund/all/set',
                {hour:setHour},
                function (res) {
                    if(res.success){
                        getAllRefundHour();
                        allRefund = setHour;
                        $("#all-refund-modify-btn").show();
                        $("#all-refund-set-input").hide();
                        $("#all-refund-confirm-btn").hide();
                    } else{
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
        } else {
            alert(JSON.stringify("Wrong number format!"));
        }

    });

    $('#part-refund-end-modify-btn').click(function () {
        $("#part-refund-end-modify-btn").hide();
        $("#part-refund-end-set-input").val(partRefund);
        $("#part-refund-end-set-input").show();
        $("#part-refund-end-confirm-btn").show();
    });

    $('#part-refund-end-confirm-btn').click(function () {
        var setHour = $("#part-refund-end-set-input").val();
        // 验证一下是否为数字
        if (!isNaN(setHour)) {
            postRequest(
                '/ticket/refund/part/set',
                {hour:setHour},
                function (res) {
                    if(res.success){
                        getPartRefundHour();
                        partRefund = setHour;
                        $("#part-refund-end-modify-btn").show();
                        $("#part-refund-end-set-input").hide();
                        $("#part-refund-end-confirm-btn").hide();
                    } else{
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
        } else {
            alert(JSON.stringify("Wrong number format!"));
        }
    });


});

//显示页面
function getCinemaHalls() {
    getRequest(
        '/hall/all',
        function (res) {
            halls = res.content;
            renderHall(halls);
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );
}

//渲染出影厅列表
function renderHall(halls){
    $('#hall-card').empty();
    var hallDomStr = "";
    halls.forEach(function (hall) {
        var seat = "";
        var seats = hall.seats;
        for(var i =0;i<seats.length;i++){
            var temp = ""
            for(var j =0;j<seats[i].length;j++){
                if(seats[i][j]==0){
                    //该座位不可视
                    temp+="<div class='cinema-hall-seat invisible'></div>";
                }
                else {
                    temp+="<div class='cinema-hall-seat'></div>";
                }
            }
            seat+= "<div>"+temp+"</div>";
        }
        var hallDom =
            "<div class='cinema-hall'>" +
            "<div class='cinema-hall-header'>"+
            "<div>" +
            "<span class='cinema-hall-name'>"+ hall.name +"</span>" +
            "<span class='cinema-hall-size'>"+ hall.seats.length +'*'+ hall.seats[0].length +"</span>" +
            "</div>" +
            "<div>" +
            "<button type='button' class='btn btn-primary'onclick='modifyHall("+hall.id+")'><span>修 改</span></button>" +
            "<button type='button' class='btn btn-danger' onclick='deleteHall("+hall.id+")'><span>删 除</span></button>" +
            "</div>" +
            "</div>"+
            "<div class='cinema-seat'>" + seat +
            "</div>" +
            "</div>";
        hallDomStr+=hallDom;
    });
    $('#hall-card').append(hallDomStr);
}

// 新增影厅
function getHallModule() {
    selectedSeats = [];
    //在表单上添加一个14*20的空表，其中8*12是已经点好了的
    var hallDomStr = "";
    var seat = "";
    for (var i = 0; i < 14; i++) {
        var temp = ""
        for (var j = 0; j < 20; j++) {
            var id = "seat" + i + "p" + j
            // 未选的座位
            temp += "<button class='cinema-hall-seat-choose' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
        }
        seat += "<div>" + temp + "</div>";
    }
    var hallDom =
        "<div class='cinema-hall'>" +
        "<div>" +
        "<span class='cinema-hall-name'>影厅布局</span>" +
        "</div>" +
        "<div class='cinema-seat'>" + seat +
        "</div>" +
        "</div>";
    hallDomStr += hallDom;

    $('#hall-card-add').html(hallDomStr);

    //手动点选8*12的座位
    for(var i=3;i<=10;i++){
        for(var j=4;j<=15;j++){
            var id = "seat" + i +"p" + j
            seatClick(id, i, j);
        }
    }

    //点击确认发送请求
    $('#hall-form-btn').click(function () {
        var formData = getHallForm();
        if(!validateHallForm(formData)){
            return;
        }
        postRequest(
            '/hall/add',
            formData,
            function (res) {

                $("#hallAddModal").modal('hide');
                window.location.href = '/admin/cinema/manage';
            },
            function (error) {
                alert(error);
            });
    });
}

//影厅表单验证
function validateHallForm(data) {
    var isValidate = true;
    var alertMessage = "";
    if(!data.name) {
        isValidate = false;
        alertMessage +="影厅名称不能为空\n";
    }

    if(isValidate==false){
        alert(alertMessage);
    }

    return isValidate;
}

function seatClick(id, i, j) {
    let seat = $('#' + id);
    if (seat.hasClass("cinema-hall-seat-choose")) {
        seat.removeClass("cinema-hall-seat-choose");
        seat.addClass("cinema-hall-seat");

        selectedSeats[selectedSeats.length] = [i, j]
    } else {
        seat.removeClass("cinema-hall-seat");
        seat.addClass("cinema-hall-seat-choose");

        selectedSeats = selectedSeats.filter(function (value) {
            return value[0] != i || value[1] != j;
        })
    }

    selectedSeats.sort(function (x, y) {
        var res = x[0] - y[0];
        return res === 0 ? x[1] - y[1] : res;
    });

    let seatDetailStr = "";
    if (selectedSeats.length == 0) {
        seatDetailStr += "还未选择座位";
        $('#hall-form-btn').attr("disabled", "disabled")
        $('#hall-edit-form-btn').attr("disabled", "disabled")
    } else {
        seatDetailStr = "<span>已选中" + selectedSeats.length + "个座位</span>";
        $('#hall-form-btn').removeAttr("disabled");
        $('#hall-edit-form-btn').removeAttr("disabled");
    }
    $('#seat-detail').html(seatDetailStr);
    $('#seat-edit-detail').html(seatDetailStr);

}



//获得hallAddModal中的信息
function getHallForm() {
    var seatList = [];
    for(let i=0;i<selectedSeats.length;i++){
        let seat = {
            rowIndex: selectedSeats[i][0],
            columnIndex: selectedSeats[i][1],
        };
        seatList[seatList.length] = seat;
    }
    let hallForm = {
        name: $("#hall-name-input").val(),
        seats: seatList
    };
    return hallForm;
}

function modifyHall(hallId) {
    var hall = getHallById(hallId);
    getEditHallModule(hall);
    $('#hallEditModal').modal('show');

    $('#hall-edit-form-btn').click(function () {
        var formData = getHallEditForm(hallId);
        if(!validateHallForm(formData)){
            return;
        }
        postRequest(
            '/hall/update',
            formData,
            function (res) {

                $("#hallEditModal").modal('hide');
                window.location.href = '/admin/cinema/manage';
            },
            function (error) {
                alert(error);
            });
    });
    $('#edit-cancel-btn').click(function () {
        window.location.href='/admin/cinema/manage';
    })
}

//填充修改模板
function getEditHallModule(hall) {
    $("#hall-edit-name-input").val(hall.name);
    selectedSeats = [];
    var seats = hall.seats;
    var hallDomStr = "";
    var seat = "";
    for (var i = 0; i < 14; i++) {
        var temp = ""
        for (var j = 0; j < 20; j++) {
            var id = "eseat" + i + "p" + j
            // 未选的座位
            temp += "<button class='cinema-hall-seat-choose' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
        }
        seat += "<div>" + temp + "</div>";
    }
    var hallDom =
        "<div class='cinema-hall'>" +
        "<div>" +
        "<span class='cinema-hall-name'>影厅布局</span>" +
        "</div>" +
        "<div class='cinema-seat'>" + seat +
        "</div>" +
        "</div>";
    hallDomStr += hallDom;

    $('#hall-card-edit').html(hallDomStr);

    //手动点选原本的座位
    var rowBias = biasToCentre(seats)[0];
    var columnBias = biasToCentre(seats)[1];
    for (var i = 0; i < seats.length; i++) {
        for (var j = 0; j < seats[i].length; j++) {
            if(seats[i][j]==1){
                var id = "eseat" + (i+rowBias) + "p" + (j+columnBias);
                seatClick(id, i+rowBias, j+columnBias);
            }
        }
    }
}

function biasToCentre(seats){
    var rowBias = Math.floor((14-seats.length)/2);
    var columnBias = Math.floor((20-seats[0].length)/2);
    return [rowBias,columnBias];
}

function getHallEditForm(hallId) {
    var seatList = [];
    for(let i=0;i<selectedSeats.length;i++){
        let seat = {
            rowIndex: selectedSeats[i][0],
            columnIndex: selectedSeats[i][1],
        };
        seatList[seatList.length] = seat;
    }
    let hallForm = {
        hallId: hallId,
        name: $("#hall-edit-name-input").val(),
        seats: seatList
    };
    return hallForm;
}

function deleteHall(hallId) {
    $('#hallDeleteModal').modal('show');
    $('#hall-delete-form-btn').click(function () {
        postRequest(
            '/hall/delete/'+hallId,
            null,
            function (res) {

                $("#hallDeleteModal").modal('hide');

                if(res.success){
                    alert("删除影厅成功成功！");
                    window.location.href = '/admin/cinema/manage';
                }
                else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            });
    })
}


function getHallById(hallId) {
    var hall;
    for(let i=0;i<halls.length;i++){
        if(halls[i].id==hallId){
            hall = halls[i];
            break;
        }
    }
    return hall;
}