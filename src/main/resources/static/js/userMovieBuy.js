var selectedSeats = [];
var scheduleId;
var order = {ticketId: [], couponId: 0, total: 0.0};
var coupons = [];
var isVIP = false;
var useVIP = true;
var currentScheduleFare;
var totalTotal;

$(document).ready(function () {
    scheduleId = parseInt(window.location.href.split('?')[1].split('&')[1].split('=')[1]);

    getInfo();

    function getInfo() {
        getRequest(
            '/ticket/get/occupiedSeats?scheduleId=' + scheduleId  + '&userId=' + sessionStorage.getItem('id'),
            function (res) {
                if (res.success) {
                    renderSchedule(res.content.scheduleItem, res.content.seats);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }
});

function renderSchedule(schedule, seats) {
    $('#schedule-hall-name').text(schedule.hallName);
    $('#order-schedule-hall-name').text(schedule.hallName);
    $('#schedule-fare').text(schedule.fare.toFixed(2));
    currentScheduleFare = schedule.fare.toFixed(2);
    $('#order-schedule-fare').text(schedule.fare.toFixed(2));
    $('#schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");
    $('#order-schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");

    var hallDomStr = "";
    var seat = "";
    for (var i = 0; i < seats.length; i++) {
        var temp = "";
        for (var j = 0; j < seats[i].length; j++) {
            var id = "seat" + i + j;

            if (seats[i][j] == 1||seats[i][j]==3) {
                // 未选
                temp += "<button class='cinema-hall-seat-choose' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
            } else if(seats[i][j]==2){
                // 已选中
                temp += "<button class='cinema-hall-seat-lock'></button>";
            }
            else {
                temp += "<div class='cinema-hall-seat invisible'></div>";
            }
        }
        seat += "<div>" + temp + "</div>";
    }
    var hallDom =
        "<div class='cinema-hall'>" +
        "<div>" +
        "<span class='cinema-hall-name'>" + schedule.hallName + "</span>" +
        "<span class='cinema-hall-size'>" + seats.length + '*' + seats[0].length + "</span>" +
        "</div>" +
        "<div class='cinema-seat'>" + seat +
        "</div>" +
        "</div>";
    hallDomStr += hallDom;

    $('#hall-card').html(hallDomStr);

    for(var i=0; i<seats.length; i++){
        for (var j = 0; j < seats[i].length; j++) {
            if(seats[i][j]==3){
                var id = "seat" + i + j;
                seatClick(id, i, j);
            }
        }
    }
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
        $('#order-confirm-btn').attr("disabled", "disabled")
    } else {
        for (let seatLoc of selectedSeats) {
            seatDetailStr += "<span>" + (seatLoc[0] + 1) + "排" + (seatLoc[1] + 1) + "座</span>";
        }
        $('#order-confirm-btn').removeAttr("disabled");
    }
    $('#seat-detail').html(seatDetailStr);
}

function orderConfirmClick() {
    $('#seat-state').css("display", "none");
    $('#order-state').css("display", "");

    var ticketData = getTicketForm();

    postRequest(
        "/ticket/lockSeat",
        ticketData,
        function (res) {
            var orderInfo = res.content;
            renderOrder(orderInfo);
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    );

    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            isVIP = res.success;
            useVIP = res.success;
            if (isVIP) {
                $('#member-balance').html("<div><b>会员卡余额：</b>" + res.content.balance.toFixed(2) + "元</div>");
            } else {
                $("#member-pay").css("display", "none");
                $("#nonmember-pay").addClass("active");

                $("#modal-body-member").css("display", "none");
                $("#modal-body-nonmember").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });
}

function switchPay(type) {
    useVIP = (type == 0);
    if (type == 0) {
        $("#member-pay").addClass("active");
        $("#nonmember-pay").removeClass("active");

        $("#modal-body-member").css("display", "");
        $("#modal-body-nonmember").css("display", "none");
    } else {
        $("#member-pay").removeClass("active");
        $("#nonmember-pay").addClass("active");

        $("#modal-body-member").css("display", "none");
        $("#modal-body-nonmember").css("display", "");
    }
}

function renderOrder(orderInfo) {
    var ticketStr = "<div>" + selectedSeats.length + "张</div>";
    for (let ticketInfo of orderInfo.ticketVOList) {
        ticketStr += "<div>" + (ticketInfo.rowIndex + 1) + "排" + (ticketInfo.columnIndex + 1) + "座</div>";
        order.ticketId.push(ticketInfo.id);
    }
    $('#order-tickets').html(ticketStr);

    var total = orderInfo.total.toFixed(2);
    order.total = total;
    totalTotal = total;
    var originalPrice = (selectedSeats.length * currentScheduleFare).toFixed(2);
    $('#order-total').text(originalPrice);
    var delta = (originalPrice - total).toFixed(2);
    $('#order-footer-total').text("总金额： ¥" + originalPrice);
    $('#order-member-discount').text("会员折扣： ¥" + delta);


    var couponTicketStr = "";
    if (orderInfo.coupons.length == 0) {
        $('#order-discount').text("优惠券折扣：无");
        $('#order-actual-total').text(" ¥" + total);
        $('#pay-amount').html("<div><b>金额：</b>" + total + "元</div>");
        $('#order-coupons').text("无可用的优惠券");
    } else {
        coupons = orderInfo.coupons;
        for (let coupon of coupons) {
            couponTicketStr += "<option>满" + coupon.targetAmount + "减" + coupon.discountAmount + "</option>"
        }
        $('#order-coupons').html(couponTicketStr);
        changeCoupon(0);
    }
}

function changeCoupon(couponIndex) {
    order.couponId = coupons[couponIndex].id;
    $('#order-discount').text("优惠券折扣： ¥" + coupons[couponIndex].discountAmount.toFixed(2));
    var actualTotal = (parseFloat(totalTotal) - parseFloat(coupons[couponIndex].discountAmount)).toFixed(2);
    $('#order-actual-total').text(" ¥" + actualTotal);
    $('#pay-amount').html("<div><b>金额：</b>" + actualTotal + "元</div>");
}

function payConfirmClick() {
    if (useVIP) {
        postPayRequest();
    } else {
        if (validateForm()) {
            if ($('#userBuy-cardNum').val() === "123123123" && $('#userBuy-cardPwd').val() === "123123") {
                postPayRequest();
            } else {
                alert("银行卡号或密码错误");
            }
        }
    }
}

function postPayRequest() {
    if (useVIP) {
        postRequest(
            '/ticket/vip/buy',
            {"id": order.ticketId, "couponId": order.couponId, "total": order.total, "userId": sessionStorage.getItem("id")},
            function (res) {
                if (res.success) {
                    $('#order-state').css("display", "none");
                    $('#success-state').css("display", "");
                    $('#buyModal').modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        )
    } else {
        postRequest(
            '/ticket/buy',
            {"id": order.ticketId, "couponId": order.couponId, "total": order.total, "userId": sessionStorage.getItem("id")},
            function (res) {
                if (res.success) {
                    $('#order-state').css("display", "none");
                    $('#success-state').css("display", "");
                    $('#buyModal').modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        )
    }
}

function validateForm() {
    var isValidate = true;
    if (!$('#userBuy-cardNum').val()) {
        isValidate = false;
        $('#userBuy-cardNum').parent('.form-group').addClass('has-error');
        $('#userBuy-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userBuy-cardPwd').val()) {
        isValidate = false;
        $('#userBuy-cardPwd').parent('.form-group').addClass('has-error');
        $('#userBuy-cardPwd-error').css("visibility", "visible");
    }
    return isValidate;
}

function getTicketForm() {
    var selectedSeatFormList = [];
    for (let seat of selectedSeats) {
        selectedSeatFormList[selectedSeatFormList.length] = {columnIndex: seat[1], rowIndex: seat[0]};
    }
    return {
        "userId": sessionStorage.getItem('id'),
        "scheduleId": scheduleId,
        "seats": selectedSeatFormList,
    };
}
