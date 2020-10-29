var cardList;
var modifyState;
$(document).ready(function () {


    if (sessionStorage.getItem('role') == 'user') {
        window.location.href = '/user/home'
    } else if (sessionStorage.getItem('role') == 'staff') {
        window.location.href = '/admin/movie/manage'
    }


    recoverForm();

    getVIPCardList();

    function getVIPCardList() {
        getRequest(
            '/vip/getAllVIPInfo',
            function (res) {
                cardList = res.content;
                renderCardList(cardList);
            },
            function (error) {
                alert(error);
            }
        );
    }

// 展示所有会员卡
    function renderCardList(list) {
        $('.content-member_card').empty();
        var cardDomStr = '';
        for (var i=0;i<list.length;i++){
            let card = list[i];
            let discount = formDiscount(card.discount);
            cardDomStr +=
                '<div class="card col-md-8 col-md-offset-2"\ ;">\n' +
                '        <div class="header">\n' +
                '            <div class="card-title">' + card.type + '</div>\n' +
                '        </div>\n' +
                '        <div class="line">\n' +
                '            <hr/>\n' +
                '        </div>\n' +
                '        <div class="info">\n' +
                '            <div class="price"><b id="member-buy-price">' + card.price + '</b>元/张</div>\n' +
                '            <div id="member-buy-description" class="description">' + '充值优惠：' + card.description + '。永久有效' + '</div>\n' +
                '            <div class="discount" id="member-buy-discount">' + '购票折扣：' + discount + '。永久有效' + '</div>\n' +
                '            <button class="" onclick="modifyCardInfo(this)" id="modify-btn-'+i+'" style="width: 180px; height: 50px; margin-left: auto; margin-right: auto; margin-bottom:10px; border-color: #f2eada; background-color: #464547; border-radius: 10px; text-align: center; color: #f2eada">修 改 信 息</button>' +
                '        </div>\n' +
                '    </div>'
        }
        // list.forEach(function (card) {
        //     var discount = formDiscount(card.discount);
        //     cardDomStr +=
        //         '<div class="card col-md-8 col-md-offset-2"\ ;">\n' +
        //         '        <div class="header">\n' +
        //         '            <div class="card-title">' + card.type + '</div>\n' +
        //         '        </div>\n' +
        //         '        <div class="line">\n' +
        //         '            <hr/>\n' +
        //         '        </div>\n' +
        //         '        <div class="info">\n' +
        //         '            <div class="price"><b id="member-buy-price">' + card.price + '</b>元/张</div>\n' +
        //         '            <div id="member-buy-description" class="description">' + '充值优惠：' + card.description + '。永久有效' + '</div>\n' +
        //         '            <div class="discount" id="member-buy-discount">' + '购票折扣：' + discount + '。永久有效' + '</div>\n' +
        //         '            <button onclick="modifyCardInfo()">修改信息</button>\n' +
        //         '        </div>\n' +
        //         '    </div>'
        // });
        $('.content-member_card').append(cardDomStr);
    }

    function getCardInfoForm(){
        return {
            type: $("#card-type-input").val(),
            targetAmount: $("#card-target-amount-input").val(),
            bonusAmount:$("#card-bonus-amount-input").val(),
            price:$("#card-price-input").val(),
            discount:$("#card-discount-input").val()
        };
    }

    $("#member-form-btn").click(function () {

        let form = getCardInfoForm();

        if (!validateCardInfoForm(form)){
            return;
        }
        if (modifyState){
            postRequest(
                '/vip/modify',
                form,
                function (res) {
                    if (res.success) {
                        getVIPCardList();
                        $("#memberCardModal").modal('hide');
                        recoverForm();
                        alert("修改成功");
                    } else {
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
            modifyState = false;
            return;
        }
        postRequest(
            '/vip/publish',
            form,
            function (res) {
                if (res.success) {
                    getVIPCardList();
                    $("#memberCardModal").modal('hide');
                    alert("发布成功");
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    // $(".modify-btn").on('click',function () {
    //     let i = $(this).attr('id').split('-')[2];
    //     console.log(i);
    //     $("#memberCardModal").modal('show');
    //     completeForm(i);
    //     modifyState = true;
    // });

});


function formDiscount(discount) {
    var vaDiscount = discount.toFixed(2).toString().substring(2);
    if (vaDiscount == 0) {
        return "不打折";
    } else {
        return vaDiscount + "折";
    }
}
function validateCardInfoForm(formData){
    let isValidate = true;
    let alertMesg="";
    if (!formData.type) {
        isValidate = false;
        $("#card-type-input").parent(".form-group").addClass("has-error");
        alertMesg += "会员卡类名不能为空\n";
    }
    // else if (formData.type.length!=3){
    //     isValidate = false;
    //     $("#card-type-input").parent(".form-group").addClass("has-error");
    //     alertMesg+="会员卡类名长度必须为3\n";
    // }
    if(formData.targetAmount<=0||formData.targetAmount/1!=formData.targetAmount){
        isValidate=false;
        $("#card-target-amount-input").parent(".form-group").addClass("has-error");
        alertMesg+="需满金额必须为大于0的整数\n";
    }
    if(formData.bonusAmount<=0||formData.bonusAmount/1!=formData.bonusAmount){
        isValidate=false;
        $("#card-bonus-amount-input").parent(".form-group").addClass("has-error");
        alertMesg+="赠送金额必须为大于0的整数\n";
    }
    if (formData.discount<=0||formData.discount>1){
        isValidate=false;
        $("#card-discount-input").parent(".form-group").addClass("has-error");
        alertMesg+="折扣必须为小于1的正小数\n";
    }
    if(formData.price<=0){
        isValidate=false;
        $("#card-price-input").parent(".form-group").addClass("has-error");
        alertMesg+="会员卡售价必须为正数\n";
    }
    if (!isValidate){
        alert(alertMesg);
    }

    return isValidate;
}
function completeForm(i) {
    $("#cardModalLabel").text("修改会员卡");
    $("#card-type-input").val(cardList[i].type);
    $("#card-type-input").attr("readOnly","readOnly");
    $("#card-description-input").val(cardList[i].description);
    $("#card-target-amount-input").val("");
    $("#card-bonus-amount-input").val("");
    $("#card-price-input").val(cardList[i].price);
    $("#card-discount-input").val(cardList[i].discount);
}

function recoverForm() {
    $("#cardModalLabel").text("发布会员卡");
    $("#card-type-input").val("");
    $("#card-type-input").removeAttr("readOnly");
    $("#card-description-input").val("");
    $("#card-target-amount-input").val("");
    $("#card-bonus-amount-input").val("");
    $("#card-price-input").val("");
    $("#card-discount-input").val("");
}
function modifyCardInfo(data) {
    let i = data.id.split('-')[2];
    console.log(i);
    $("#memberCardModal").modal('show');
    completeForm(i);
    modifyState = true;
}