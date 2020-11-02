$(document).ready(function() {

    if (sessionStorage.getItem('role') == 'user') {
        window.location.href = '/user/home'
    }

    var movieList;

    getAllMovies();

    getActivities();

    function getActivities() {
        getRequest(
            '/activity/get',
            function (res) {
                var activities = res.content;
                renderActivities(activities);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }
    
    function renderActivities(activities) {
        $(".content-activity").empty();
        var activitiesDomStr = "";

        activities.forEach(function (activity) {
            var movieDomStr = "";
            if(activity.movieList.length){
                activity.movieList.forEach(function (movie) {
                    movieDomStr += "<li class='activity-movie primary-text'>"+movie.name+"</li>";
                });
            }else{
                movieDomStr = "<li class='activity-movie primary-text'>所有热映电影</li>";
            }

            activitiesDomStr+=
                "<div class='activity-container'>" +
                "    <div class='activity-card card'>" +
                "       <div class='activity-line'>" +
                "           <span class='title'>"+activity.name+"</span>" +
                "           <span class='gray-text'>"+activity.description+"</span>" +
                "       </div>" +
                "       <div class='activity-line'>" +
                "           <span>活动时间："+formatDate(new Date(activity.startTime))+"至"+formatDate(new Date(activity.endTime))+"</span>" +
                "       </div>" +
                "       <div class='activity-line'>" +
                "           <span>参与电影：</span>" +
                "               <ul>"+movieDomStr+"</ul>" +
                "       </div>" +
                "    </div>" +
                "    <div class='activity-coupon primary-bg'>" +
                "        <span class='title'>优惠券："+activity.coupon.name+"</span>" +
                "        <span class='title'>满"+activity.coupon.targetAmount+"减<span class='error-text title'>"+activity.coupon.discountAmount+"</span></span>" +
                "        <span class='gray-text'>"+activity.coupon.description+"</span>" +
                "    </div>" +
                "</div>";
        });
        $(".content-activity").append(activitiesDomStr);
    }

    function getAllMovies() {
        getRequest(
            '/movie/all/exclude/off',
            function (res) {
                movieList = res.content;
                $('#activity-movie-input').append("<option value="+ -1 +">无</option>");
                $('#activity-movie-input').append("<option value="+ -2 +">所有电影</option>");
                movieList.forEach(function (movie) {
                    $('#activity-movie-input').append("<option value="+ movie.id +">"+movie.name+"</option>");
                });
            },
            function (error) {
                alert(error);
            }
        );
    }

    $("#activity-form-btn").click(function () {
       var form = {
           name: $("#activity-name-input").val(),
           description: $("#activity-description-input").val(),
           startTime: $("#activity-start-date-input").val(),
           endTime: $("#activity-end-date-input").val(),
           movieList: [...selectedMovieIds],
           couponForm: {
               description: $("#coupon-name-input").val(),
               name: $("#coupon-description-input").val(),
               targetAmount: $("#coupon-target-input").val(),
               discountAmount: $("#coupon-discount-input").val(),
               startTime: $("#activity-start-date-input").val(),
               endTime: $("#activity-end-date-input").val()
           }
       };

       if(!isValidActivityForm(form)){
           return;
       }

        postRequest(
            '/activity/publish',
            form,
            function (res) {
                if(res.success){
                    getActivities();
                    $("#activityModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    //ES6新api 不重复集合 Set
    var selectedMovieIds = new Set();
    var selectedMovieNames = new Set();

    $('#activity-movie-input').change(function () {
        var movieId = $('#activity-movie-input').val();
        var movieName = $('#activity-movie-input').children('option:selected').text();
        if(movieId==-1){
            selectedMovieIds.clear();
            selectedMovieNames.clear();
        }else if(movieId==-2){
            selectedMovieIds.clear();
            selectedMovieNames.clear();
            movieList.forEach(function (movie) {
                selectedMovieIds.add(movie.id);
                selectedMovieNames.add(movie.name);
            });

        }
        else {
            selectedMovieIds.add(movieId);
            selectedMovieNames.add(movieName);
        }
        renderSelectedMovies();
    });

    //渲染选择的参加活动的电影
    function renderSelectedMovies() {
        $('#selected-movies').empty();
        var moviesDomStr = "";
        selectedMovieNames.forEach(function (movieName) {
            moviesDomStr += "<span class='label label-primary'>"+movieName+"</span>";
        });
        $('#selected-movies').append(moviesDomStr);
    }
});

function isValidActivityForm(form) {
    var isValid = true;
    var errorMessgae = "";

    if(!form.name){
        isValid = false;
        errorMessgae+= "名称不能为空\n"
    }

    if(!form.description){
        isValid = false;
        errorMessgae += "描述不能为空\n"
    }

    if(!form.startTime||form.startTime==""){
        isValid = false;
        errorMessgae +="开始时间不能为空\n";
    }

    if(!form.endTime||form.endTime==""){
        isValid = false;
        errorMessgae += "结束时间不能为空\n";
    }

    if(!form.couponForm.name){
        isValid = false;
        errorMessgae += "优惠券名称不能为空\n";
    }

    if(!form.couponForm.description){
        isValid = false;
        errorMessgae += "优惠券描述不能为空\n";
    }

    if(!form.couponForm.targetAmount){
        isValid = false;
        errorMessgae += "优惠券使用门槛不能为空\n";
    }

    if(!form.couponForm.discountAmount){
        isValid = false;
        errorMessgae += "优惠券优惠金额不能为空\n";
    }

    if(isValid == false){
        alert(errorMessgae);
    }
    return isValid;
}