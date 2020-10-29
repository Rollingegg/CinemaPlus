$(document).ready(function(){

    getMovieList('');

    function getMovieList(keyword) {
        getRequest(
            '/movie/search?keyword='+keyword,
            function (res) {
                renderMovieList(res.content);
            },
             function (error) {
            alert(error);
        });
    }

    function renderMovieList(list) {
        $('.movie-on-list').empty();
        var movieDomStr = '';
        list.forEach(function (movie) {
            movie.description = movie.description || '';
            movieDomStr +=
                "<li class='movie-item card' href='/user/movieDetail?id="+ movie.id +"'>" +
                "<img class='movie-img' src='" + (movie.posterUrl || "../images/defaultAvatar.jpg") + "'/>" +
                "<div class='movie-info'>" +
                "<div class='movie-title'>" +
                "<span class='head-text' style='font-size: x-large'>" + movie.name + "</span>" +
                "<span class='label "+(!movie.status ? 'error-bg' : 'primary-bg')+"'>" + (movie.status ? '已下架' : (new Date(movie.startDate)>=new Date()?'未上映':'热映中')) + "</span>" +
                "<span class='movie-want'><i class='icon-heart error-text'></i>" + (movie.likeCount || 0) + "人想看</span>" +
                "</div>" +
                "<div class='movie-description dark-text'><span>\n\n" + movie.description + "</span></div>" +
                "<div><b>\n类型：</b>" + movie.type + "</div>" +
                "<div style='display: flex'><span><b>\n导演：</b>" + movie.director + "</span><span style='margin-left: 30px;'><b>主演：</b>" + movie.starring + "</span></div>" +
                "<div align='right'><a href='/user/movieDetail?id="+ movie.id +"' style='color: #000000'><button class='movie-operation' style='font-size: medium; alignment: right; border-radius: 5px; background-color: #f2eada; border-color: #f2eada; height: 40px; width: 100px'>  详 情  </button></a></div>" +
                "</div>"+
                "</li>";
        });
        $('.movie-on-list').append(movieDomStr);
    }

    $('#search-btn').click(function () {
        getMovieList($('#search-input').val());
    })

});