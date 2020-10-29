var roles;

$(document).ready(function(){

    if (sessionStorage.getItem('role') == 'user') {
        window.location.href = '/user/home'
    } else if (sessionStorage.getItem('role') == 'staff') {
        window.location.href = '/admin/movie/manage'
    }


    getUserList();

    if (sessionStorage.getItem('username') != 'root') {
        $('#level-admin').hide();
    }


    $('#user-form-btn').click(function() {
        var formData = getUserForm();
        if(!validateAccountForm(formData)) {
            return;
        }

        postRequest(
            '/account/create',
            formData,
            function (res) {
                alert("注册成功");
                $("#userModal").modal('hide');
                getUserList();
            },
            function(error) {
                alert(error);
            }
        )
    });

    function getUserForm() {
        var level = parseInt($('input[name="level"]:checked').val());
        return {
            username: $('#user-name-input').val(),
            password: $('#user-password-input').val(),
            privilegeLevel: level
        }
    }

    function getUserList() {
        getRequest(
            '/account/list',
            function (res) {
                if (res.success) {
                    $('#user-list').css("display", "");
                    roles = res.content;
                    repaintRolesRow(0);
                }
            },
            function (e) {
                alert(JSON.stringify(e));
            }
        )
    }
});

function repaintRolesRow(currentRoleLoc) {
    var roleContent = "";
    for (var i = 0; i < roles.length; ++i) {
        var role = getPrivilegeLevelStr(roles[i].privilegeLevel);
        roleContent += '<li role="presentation" id="user-role' + i + '"><a href="#"  onclick="repaintRolesRow(\'' + i + '\')">' + role + '</a></li>';
    }
    $('#user-role').html(roleContent);

    $('#user-role' + currentRoleLoc).addClass("active");
    repaintUserList(currentRoleLoc)
}

function repaintUserList(level) {
    var userItem = roles[level].userList;
    if (userItem.length == 0) {
        $('#user-none-hint').css("display", "");
    } else {
        $('#user-none-hint').css("display", "none");
    }
    var bodyContent = "";
    for (var i = 0; i < userItem.length; ++i) {
        bodyContent += "<tr><td>" + userItem[i].username + "</td>" +
            "<td>" + userItem[i].totalConsumption + "</td>" +
            "<td>" + getVIPStr(userItem[i]) + "</td>" +
            "<td><a class='btn btn-primary' href='/admin/account/detail?id="+userItem[i].userId+"' role='button'>用户资料</a></td></tr>";
    }
    $('#user-list-body').html(bodyContent);
}

function getVIPStr(user) {
    var validVIP = user.validVIP;
    if (validVIP) {
        return user.vipBalance;
    } else {
        return '-';
    }
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

//做新增用户的表单验证
function validateAccountForm(data) {
    var isValidate = true;
    var alertMessage = "";
    if(!data.username) {
        isValidate = false;
        alertMessage +="用户名不能为空\n";
    }
    if(!data.password) {
        isValidate = false;
        alertMessage +="密码不能为空\n";
    }
    if(!data.privilegeLevel) {
        isValidate = false;
        alertMessage +="权限不能为空\n";
    }

    if(isValidate==false){
        alert(alertMessage);
    }

    return isValidate;
}