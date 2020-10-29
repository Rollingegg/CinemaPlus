$(document).ready(function () {

    // orderTime = parseInt(window.location.href.split('=')[1]);
    orderTime = window.location.href.split('=')[1];

    getMovieList();

    function getMovieList() {
        // console.log('/ticket/buy/payment/' + sessionStorage.getItem(id) + '/' + orderTime);
        getRequest(
            '/ticket/buy/payment/' + sessionStorage.getItem('id') + '/' + orderTime,
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function renderTicketList(list) {
        $('tbody').empty();
        console.log(list);
        var ticketDomStr = '';
        list.forEach(function (ticket) {
            var columnStr = ticket.columnIndex+1;
            var rowStr = ticket.rowIndex+1;
            ticketDomStr += "<tr>\n" +
                "                    <td>" + ticket.schedule.movieName + "</td>\n" +
                "                    <td>" + ticket.schedule.hallName + "</td>\n" +
                "                    <td>" + rowStr + "排" + columnStr + "座" + "</td>\n" +
                "                    <td>" + time2str(ticket.schedule.startTime) + "</td>\n" +
                "                    <td>" + ticket.state + "</td>\n" +
                "                </tr>";
        });
        $('tbody').append(ticketDomStr);
    }

    function time2str(time) {
        return time.substring(5,7)+"月"+time.substring(8,10)+"日 "+time.substring(11,16);
    }
});