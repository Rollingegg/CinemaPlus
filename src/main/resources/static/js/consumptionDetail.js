$(document).ready(function () {
    var consumptionId = parseInt(window.location.href.split('?')[1].split('=')[1]);

    getRequest(
        '/consumption/get/' + consumptionId,
        function (res) {
            var consumptionDetail = res.content;
            renderTicketList(consumptionDetail.ticketList);
            renderCoupon(consumptionDetail.couponVO);
        },
        function (error) {
            alert(error);
        });


    function renderTicketList(list) {
        $('tbody').empty();
        console.log(list)
        var ticketDomStr = '';
        list.forEach(function (ticket) {
            var columnStr = ticket.columnIndex+1;
            var rowStr = ticket.rowIndex+1;
            ticketDomStr += "<tr>\n" +
                "                    <td>" + ticket.schedule.movieName + "</td>\n" +
                "                    <td>" + ticket.schedule.hallName + "</td>\n" +
                "                    <td>" + rowStr + "排" + columnStr + "座" + "</td>\n" +
                "                    <td>" + time2str(ticket.schedule.startTime) + "</td>\n" +
                "                    <td>" + time2str(ticket.schedule.endTime) + "</td>\n" +
                "                    <td>" + ticket.state + "</td>\n" +
                "                </tr>";
        });
        $('tbody').append(ticketDomStr);
    }

    function time2str(time) {
        return time.substring(5,7)+"月"+time.substring(8,10)+"日 "+time.substring(11,16);
    }

    function renderCoupon(couponVO) {
        var coupon = couponVO;
        var couponListContent = '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
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
            '</div></div></div></div>';
        $('#coupon-list').html(couponListContent);
    }

    function formatDate(date) {
        return date.substring(5, 10).replace("-", ".");
    }
});