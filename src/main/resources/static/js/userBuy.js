$(document).ready(function () {
    getMovieList();

    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderTicketList(list) {
        $('tbody').empty();
        let hasTicket = false;
        // console.log(list);
        var ticketDomStr = '';
        list.forEach(function (ticket) {
            hasTicket = true;
            let idList = ticket.ticketIds;
            // id = String(idList);
            // alert(JSON.stringify(idList));
            var columnStr = ticket.columnIndex + 1;
            var rowStr = ticket.rowIndex + 1;
            // ticketDomStr += "<tr>\n" +
            //     "                    <td><tr>" + ticket.schedule.movieName + "</tr></td>" +
            //     "                    <tr>" + ticket.schedule.hallName + "</tr>\n" +
            //     "                    <td>" + columnStr + "排" + rowStr + "座" + "</td>\n" +
            //     "                    <td>" + time2str(ticket.schedule.startTime) + "</td>\n" +
            //     "                    <td>" + time2str(ticket.schedule.endTime) + "</td>\n" +
            //     "                    <td>" + ticket.state + "</td>\n" +
            //     "                </tr>";
            var posterUrl = ticket.movie.posterUrl;
            ticketDomStr +=
                "<tr>\n" +
                "    <td rowspan=\"3\" width='290px' align='center'><img width='180px' src='" + posterUrl + "' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
                "    <td><h3>" + ticket.schedule.movieName + "</h3></td>\n" +
                "    <td rowspan=\"3\" align='center' width='290px'>" +
                "<a href='/user/movieDetail?id=" + ticket.movie.id + "'><button type='button' style=\"width: 120px; padding: 10px 20px; border-radius: 5px; background-color: #f2eada; border: 0px  #555 solid; color: #737373; \">电影详情</button></a><br/><br/>" +
                // "<a><button type='button' style='width: 120px; padding: 10px 20px; border-radius: 5px; background-color: #f2eada; border: 0px  #555 solid; color: #737373; ' onclick=\"cancelOrderClick('" + String(idList) + "')\">退  票</button></a>" +
                "<a><button type='button' style='width: 120px; padding: 10px 20px; border-radius: 5px; background-color: #f2eada; border: 0px  #555 solid; color: #737373; ' onclick=\"cancelOrderClick('" + String(idList) + "')\">退  票</button></a>" +
                // /user/ticket/buy/getPayment
                "</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td style='font-size: small'>" + time2str(ticket.schedule.startTime) + "&nbsp;&nbsp;" + ticket.schedule.hallName + "</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td><h4>" + ticket.seatsStr + "</h4></td>\n" +
                "  </tr>"
        });
        if (!hasTicket) {
            var noTicketStr = "<tr>\n" +
                "  <td>\n" +
                "    <h2 align='center'>当前暂无已购买的电影票</h2>\n" +
                "  </td>\n" +
                "</tr>";
            $('tbody').append(noTicketStr);
        } else {
            $('tbody').append(ticketDomStr);
        }
    }

    function time2str(time) {
        return time.substring(5, 7) + "月" + time.substring(8, 10) + "日" + time.substring(11, 16);
    }

});

function cancelOrderClick(idStr) {
    if (confirm("确认要退票吗？")) {
        alert("退票成功！");
        getRequest(
            '/ticket/cancel?idStr=' + idStr,
            function (res) {
                // if (res.success()) alert("退票成功！");
                window.location.reload();
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        )
    }

}