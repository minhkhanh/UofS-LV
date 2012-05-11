// 3 - MESSAGE BOX FADING SCRIPTS ---------------------------------------------------------------------

$(document).ready(function () {
    $(".close-yellow").click(function () {
        $("#message-yellow").fadeOut("slow");
    });
    $(".close-red").click(function () {
        $("#message-red").fadeOut("slow");
    });
    $(".close-blue").click(function () {
        $("#message-blue").fadeOut("slow");
    });
    $(".close-green").click(function () {
        $("#message-green").fadeOut("slow");
    });
});

$(document).ready(function () {
    $(".close-yellow").click(function () {
        $(".message-yellow").fadeOut("slow");
    });
    $(".close-red").click(function () {
        $(".message-red").fadeOut("slow");
    });
    $(".close-blue").click(function () {
        $(".message-blue").fadeOut("slow");
    });
    $(".close-green").click(function () {
        $(".message-green").fadeOut("slow");
    });
});

$.fn.center = function () {
    this.css("position", "absolute");
    this.css("top", ($(window).height() - this.height()) / 2 + $(window).scrollTop() + "px");
    this.css("left", ($(window).width() - this.width()) / 2 + $(window).scrollLeft() + "px");
    return this;
};