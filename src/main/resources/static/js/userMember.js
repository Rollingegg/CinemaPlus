$(document).ready(function () {
    getVIP();
    getCoupon();
});

var isBuyState = true;
var vipCardId;// 普通卡
var vipType;

function getVIP() {
    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                // 是会员
                $("#member-card").css("visibility", "visible");
                $("#member-card").css("display", "");
                // $("#nonmember-card").css("display", "none");
                // $("#nonmember-card").css("visibility","hidden");
                $('#card-on-list').empty();

                vipCardId = res.content.id;
                $("#member-id").text(res.content.id);
                $("#member-balance").text("¥" + res.content.balance.toFixed(2));
                $("#member-joinDate").text(res.content.joinDate.substring(0, 10));
                // 获取该会员卡的基本信息
                getRequest(
                    '/vip/'+sessionStorage.getItem('id')+'/getVIPInfo',
                    function (res) {
                        if (res.success) {
                            // $("#member-buy-price").text(res.content.price);
                            $("#card-type").text(res.content.type);
                            // $("#member-buy-description").text("充值优惠：" + res.content.description + "。永久有效");
                            // $("#member-buy-discount").text("购票折扣：" + formDiscount(res.content.discount) + "。永久有效");
                            $("#member-description").text(res.content.description);
                            $("#member-discount").text(formDiscount(res.content.discount));
                        } else {
                            alert(res.content);
                        }

                    },
                    function (error) {
                        alert(error);
                    });
            } else {
                // 非会员
                $("#member-card").css("display", "none");
                $("#nonmember-card").css("display", "");
                getRequest(
                    '/vip/getAllVIPInfo',
                    function (res) {
                        renderCardList(res.content);
                    },
                    function (error) {
                        alert(error);
                    }
                );
            }
        },
        function (error) {
            alert(error);
        });

}
// 获得所有会员卡的list
function renderCardList(list) {
    $('#card-on-list').empty();
    var cardDomStr='';
    list.forEach(function (card) {
        var discount = formDiscount(card.discount);
        cardDomStr+=
            '<li><div class="card col-md-8 col-md-offset-2" id="nonmember-card" style="display: ;">\n' +
            '        <div class="header">\n' +
            '            <div class="card-title">'+card.type+'</div>\n' +
            '            <div class="state">非会员</div>\n' +
            '        </div>\n' +
            '        <div class="line">\n' +
            '            <div>成为VIP会员</div>\n' +
            '            <hr/>\n' +
            '        </div>\n' +
            '        <div class="info">\n' +
            '            <div class="price"><b id="member-buy-price">'+card.price+'</b>元/张</div>\n' +
            '            <div id="member-buy-description" class="description">'+'充值优惠：' + card.description + '。永久有效'+'</div>\n' +
            '            <div class="discount" id="member-buy-discount">'+'购票折扣：' + discount + '。永久有效'+'</div>\n' +
            '            <button onclick="buyClick(event)">立 即 购 买</button>\n' +
            '        </div>\n' +
            '    </div></li>'
    });
    $('#card-on-list').append(cardDomStr);
}

// 购买会员卡，需传递会员卡种类
function buyClick(event) {
    clearForm();
    $('#buyModal').modal('show');
    $("#userMember-amount-group").css("display", "none");
    isBuyState = true;
    vipType = event.srcElement.parentNode.parentNode.childNodes[1].childNodes[1].innerText;
    console.log(vipType);
}

function chargeClick() {
    clearForm();
    $('#buyModal').modal('show');
    $("#userMember-amount-group").css("display", "");
    isBuyState = false;
    vipType = $("#card-type").text;
}

function clearForm() {
    $("#myModalLabel").text("充值会员卡");
    $('#userMember-form input').val("");
    $('#userMember-form .form-group').removeClass("has-error");
    $('#userMember-form p').css("visibility", "hidden");
}

function confirmCommit() {
    if (validateForm()) {
        if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {
            if (isBuyState) {
                postRequest(
                    '/vip/add?userId=' + sessionStorage.getItem('id') + '&type=' + vipType,
                    null,
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("购买会员卡成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            } else {
                postRequest(
                    '/vip/charge',
                    {vipId:vipCardId, userId: sessionStorage.getItem('id'), amount: parseInt($('#userMember-amount').val())},
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("充值成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            }
        } else {
            alert("银行卡号或密码错误");
        }
    }
}

function validateForm() {
    var isValidate = true;
    if (!$('#userMember-cardNum').val()) {
        isValidate = false;
        $('#userMember-cardNum').parent('.form-group').addClass('has-error');
        $('#userMember-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userMember-cardPwd').val()) {
        isValidate = false;
        $('#userMember-cardPwd').parent('.form-group').addClass('has-error');
        $('#userMember-cardPwd-error').css("visibility", "visible");
    }
    if (!isBuyState && (!$('#userMember-amount').val() || parseInt($('#userMember-amount').val()) <= 0)) {
        isValidate = false;
        $('#userMember-amount').parent('.form-group').addClass('has-error');
        $('#userMember-amount-error').css("visibility", "visible");
    }
    return isValidate;
}

function getCoupon() {
    getRequest(
        '/coupon/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                var couponList = res.content;
                var couponListContent = '';
                for (let coupon of couponList) {
                    couponListContent += '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
                        '<div class="col-md-8 left">' +
                        '<div class="name">' +
                        coupon.name +
                        '</div>' +
                        '<div class="description">' +
                        coupon.description +
                        '</div>' +
                        '<div class="price">' +
                        '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-4 right">' +
                        '<div>有效日期：</div>' +
                        '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                        '</div></div></div></div>'
                }
                $('#coupon-list').html(couponListContent);

            }
        },
        function (error) {
            alert(error);
        });
}

function formatDate(date) {
    return date.substring(5, 10).replace("-", ".");
}

function formDiscount(discount) {
    var vaDiscount = discount.toFixed(2).toString().substring(2);
    if(vaDiscount==0){
        return "不打折";
    }else{
        return vaDiscount+"折";
    }
}