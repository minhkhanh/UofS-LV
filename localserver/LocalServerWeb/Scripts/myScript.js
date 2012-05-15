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

// Make button more beautiful
$(document).ready(function () {
    $('input:submit').button();
    $('input:reset').button();
    $('input:button').button();
});

// Tooltip
$(document).ready(function () {
    $('a.info-tooltip ').tooltip({
        track: true,
        delay: 0,
        fixPNG: true,
        showURL: false,
        showBody: " - ",
        top: -35,
        left: 5
    });
});