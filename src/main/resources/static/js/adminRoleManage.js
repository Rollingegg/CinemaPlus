$(document).ready(function() {
    if (sessionStorage.getItem('role') == 'staff') {
        $('#nav-member-manage').hide();
        $('#nav-account-manage').hide();
    }
});