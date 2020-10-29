$(document).ready(function () {
    getConsumptionList();

    function getConsumptionList() {
        getRequest(
            '/consumption/' + sessionStorage.getItem('id'),
            function (res) {
                renderConsumptionList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderConsumptionList(list) {
        $('tbody').empty();
        console.log(list)
        var ticketConsumptionDomStr = '';
        var chargeConsumptionDomStr = '';
        list.forEach(function (consumption) {
            var movieName = consumption.movieName;
            if(movieName=="购买会员卡"||movieName=="会员卡充值"){
                var pay = consumption.total- consumption.discountAmount;
                pay = "-" + pay;
                chargeConsumptionDomStr += "<tr>\n" +
                    "                    <td>" + movieName + "</td>\n" +
                    "                    <td>" + consumption.total + "</td>\n" +
                    "                    <td>" + consumption.discountAmount + "</td>\n" +
                    "                    <td><b style='color: #464547'>" + pay + "</b></td>\n" +
                    "                    <td>" + consumption.payMethod + "</td>\n" +
                    "                    <td>" + time2str(consumption.time) + "</td>\n" +
                    "                </tr>";
            }
            else {
                var pay = consumption.total- consumption.discountAmount;
                if(pay>0){
                    pay = "-" + pay;
                }
                else{
                    pay = "+" + pay;
                }
                ticketConsumptionDomStr += "<tr>\n" +
                    "                    <td>" + consumption.movieName + "</td>\n" +
                    "                    <td>" + consumption.ticketNum + "</td>\n" +
                    "                    <td>" + consumption.total + "</td>\n" +
                    "                    <td>" + consumption.discountAmount + "</td>\n" +
                    "                    <td><b style='color: #464547'>" + pay + "</b></td>\n" +
                    "                    <td>" + consumption.payMethod + "</td>\n" +
                    "                    <td>" + time2str(consumption.time) + "</td>\n" +
                    "                    <td><a href='/user/consumptionDetail?id="+ consumption.id +"'>详情</a></td>\n" +
                    "                </tr>";
            }
        });
        $('#ticketConsumptionTable').append(ticketConsumptionDomStr);
        $('#chargeConsumptionTable').append(chargeConsumptionDomStr);
    }



    function time2str(time) {
        return time.substring(5,7)+"月"+time.substring(8,10)+"日 "+time.substring(11,16);
    }

    $("#mySelect").change(function(){
        var opt=$("#mySelect").val();
        var id;
        if(opt=="ticket"){
            $('#ticketConsumptionTable').removeClass('hidden');
            $('#chargeConsumptionTable').addClass('hidden');
        }
        else {
            $('#ticketConsumptionTable').addClass('hidden');
            $('#chargeConsumptionTable').removeClass('hidden');
        }

    })
});