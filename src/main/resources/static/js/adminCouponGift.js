var userId;
var userName;
var couponList;

$(document).ready(function() {

    if (sessionStorage.getItem('role') == 'user') {
        window.location.href = '/user/home'
    } else if (sessionStorage.getItem('role') == 'staff') {
        window.location.href = '/admin/movie/manage'
    }
    userId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    userName = '';

    getCouponList();


    function getCouponList() {
        getRequest(
            '/coupon/valid',
            function (res) {
                if (res.success) {
                    couponList = res.content;
                    repaintCouponList();
                } else {
                    alert('出错');
                }
            },
            function (e) {
                alert(JSON.stringify(e));
            }
        )
    }
});

function repaintCouponList() {
    if (couponList.length == 0) {
        $('#coupon-none-hint').css("display", "");
    } else {
        $('#coupon-none-hint').css("display", "none");
    }
    var bodyContent = '';
    for (var i = 0; i < couponList.length; ++i) {
        bodyContent += "<tr><td>" + couponList[i].name + "</td>" +
            "<td>" + couponList[i].description + "</td>" +
            "<td>" + couponList[i].targetAmount + "</td>" +
            "<td>" + couponList[i].discountAmount + "</td>" +
            "<td>" + couponList[i].startTime.substring(0, 10) + "</td>" +
            "<td>" + couponList[i].endTime.substring(0, 10) + "</td>" +
            '<td><a class="btn btn-primary" onclick="issueCoupon(\'' + couponList[i].id + '\')" role="button">赠送</a></td></tr>';
    }
    $('#coupon-select-body').html(bodyContent);

}

function issueCoupon(couponId) {
    var conf = confirm("您确定要赠送此礼券吗？");
    if (conf) {
        getRequest(
            '/coupon/issue?couponId=' + couponId + '&userId=' + userId,
            function (res) {
                if (res.success) {
                    alert('赠送成功');
                } else {
                    alert('赠送失败');
                }
            },
            function (e) {
                alert(JSON.stringify(e));

            }
        )
    }

}