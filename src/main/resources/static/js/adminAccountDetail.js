$(document).ready(function() {

    if (sessionStorage.getItem('role') == 'user') {
        window.location.href = '/user/home'
    } else if (sessionStorage.getItem('role') == 'staff') {
        window.location.href = '/admin/movie/manage'
    }

    var userId = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var userName = '';
    getUserDetail();

    function getUserDetail() {
        var userVO;
        getRequest(
            '/account/detail?userId=' + userId,
            function (res) {
                userVO = res.content;
                userName = userVO.username;
                $('#user-name').text(userVO.username);
                $('#user-level').text(getPrivilegeLevelStr(userVO.privilegeLevel));
                if (userVO.privilegeLevel != 1) {
                    $('#user-consumption-div').hide();
                    $('#user-card-div').hide();
                    $('#coupon-btn').hide();
                    if (userVO.username == 'root' || (userVO.privilegeLevel == 0 && sessionStorage.getItem('username') != 'root') || userVO.username == sessionStorage.getItem('username')) {
                        $('#delete-btn').hide();
                    }
                } else {
                    $('#user-consumption').text(userVO.totalConsumption);
                    if (userVO.validVIP) {
                        $('#user-card').text(userVO.vipBalance);
                        $('#user-level').text('VIP客户');
                    } else {
                        $('#user-card-div').hide();
                    }
                    $('#coupon-btn').click(function () {
                        window.location.href = '/admin/account/coupon/gift?id=' + userId;
                    });
                }
            },
            function (e) {
                alert(JSON.stringify(e));
            }
        );
    }

    function getPrivilegeLevelStr(level) {
        switch (level) {
            case 1:
                return '客户';
            case 2:
                return '员工';
            case 0:
                return '管理员';
            default:
                return '未知'
        }
    }

    $('#user-delete-form-btn').click(function() {
        if (confirmDeletion($('#user-delete-input').val())) {
            postRequest(
                '/account/check/password',
                {
                    'username': sessionStorage.getItem('username'),
                    'password': $('#user-delete-password-input').val(),
                    'privilegeLevel': 0
                },
                function() {
                    getRequest(
                        '/account/delete?userId=' + userId,
                        function () {
                            alert('删除用户成功');
                            window.location.href = '/admin/account/manage';
                        },
                        function(e) {
                            alert('删除时出错');
                        }
                    );
                },
                function() {
                    alert('密码错误');
                }
            );
        } else {
            alert('删除失败');
        }
    });

    function confirmDeletion(str) {
        return str == ('我确认删除' + userName);
    }
});