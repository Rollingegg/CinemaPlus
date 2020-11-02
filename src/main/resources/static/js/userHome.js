$(document).ready(function(){

    getMovieList();

    function getMovieList() {
        getRequest(
            '/movie/all',
            function (res) {
                renderMovieList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    function renderMovieList(list) {
        // alert("in");
        $('#poster-show-list').empty();
        var movieDomStr = '';
        list.forEach(function (movie) {
            movieDomStr +=
                "<li class=\"grid-item\" data-jkit=\"[show:delay=3000;speed=500;animation=fade]\">" +
                "            <img src=\"" + movie.posterUrl +"\">" +
                "            <a class=\"ajax-link\" href=\"/user/movieDetail?id=" + movie.id + "\">" +
                "                <div class=\"grid-hover\">" +
                "                    <h1>" + movie.name.split(' ', 1) + "</h1>" +
                "                    <p>6月12日上映</p>" +
                "                </div>" +
                "            </a>" +
                "        </li>";
        });
        $('#poster-show-list').append(movieDomStr);
    }

    $('#search-btn').click(function () {
        getMovieList($('#search-input').val());
    })

});