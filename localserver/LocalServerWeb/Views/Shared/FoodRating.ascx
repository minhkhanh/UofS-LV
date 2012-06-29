<%@ Control Language="C#" Inherits="System.Web.Mvc.ViewUserControl<FoodDetailViewModel>" %>
<%@ Import Namespace="LocalServerWeb.ViewModels" %>
<%@ Import Namespace="LocalServerWeb.Resources.Views.FoodCategory" %>
<%@ Import Namespace="LocalServerWeb.Controllers" %>

<div id="food_rating">
    <script type="text/javascript">
        $(function () {
            $('.star').rating('readOnly', true);
            $('#rater').hide();
            $('#rated').mouseover(function () {
                $('#rated').hide();
                $('#rater').show();
            });

            $('#rater').mouseout(function () {
                $('#rater').hide();
                $('#rated').show();
            });

            $('.auto-submit-star').rating({
                callback: function (value, link) {
                    $.ajax({
                        type: "POST",
                        url: "/FoodCategory/RateFood",
                        data: $("#rate").serialize(),
                        dataType: "html",
                        success: function (response) {
                            if (response != 'false') {
                                var data = eval('(' + response + ')');
                                alert('Your rating has been recorded');
                                $('#currentlyrated').html('Currently rated ' + data.DiemTrungBinh.toFixed(2) +
                ' by ' + data.SoLuotDanhGia + ' people');
                            }
                            else {
                                alert('You have already rated this article');
                            }
                            $('#rater').hide();
                            $('#rated').show();
                        },
                        error: function (response) {
                            alert('There was an error.');
                        }
                    });
                }
            });
        });
    </script>
    <div id="rated">
        <div style="float: left">
            <form id="Form1" method="post" action="">
            <input class="star {split:4}" type="radio" value="1" name="rating" <%= FoodCategoryController.Check(0,0.25, Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="2" name="rating" <%= FoodCategoryController.Check(0.25,0.5,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="3" name="rating" <%= FoodCategoryController.Check(0.5,0.75,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="4" name="rating" <%= FoodCategoryController.Check(0.75,1,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="5" name="rating" <%= FoodCategoryController.Check(1,1.25,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="6" name="rating" <%= FoodCategoryController.Check(1.25,1.5,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="7" name="rating" <%= FoodCategoryController.Check(1.5,1.75,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="8" name="rating" <%= FoodCategoryController.Check(1.75,2,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="9" name="rating" <%= FoodCategoryController.Check(2,2.25,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="10" name="rating" <%= FoodCategoryController.Check(2.25,2.5,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="11" name="rating" <%= FoodCategoryController.Check(2.5,2.75,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="12" name="rating" <%= FoodCategoryController.Check(2.75,3,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="13" name="rating" <%= FoodCategoryController.Check(3,3.25,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="14" name="rating" <%= FoodCategoryController.Check(3.25,3.5,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="15" name="rating" <%= FoodCategoryController.Check(3.5,3.75,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="16" name="rating" <%= FoodCategoryController.Check(3.75,4,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="17" name="rating" <%= FoodCategoryController.Check(4,4.25,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="18" name="rating" <%= FoodCategoryController.Check(4.25,4.5,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="19" name="rating" <%= FoodCategoryController.Check(4.5,4.75,Model.DiemTrungBinh) %> />
            <input class="star {split:4}" type="radio" value="20" name="rating" <%= FoodCategoryController.Check(4.75,5,Model.DiemTrungBinh) %> />
            </form>
        </div>
        <p id="currentlyrated" style="float: left; padding-left: 20px;">
            <%= Model.DiemTrungBinh > 0 ? "Currently rated " + Model.DiemTrungBinh.ToString("f") + " by " + Model.SoLuotDanhGia + " people" 
       : "<span style=\"color:red\">Not yet rated.  Be the first to rate this food!</span>"%>
        </p>
    </div>
    <div style="clear: both">
    </div>
    <div id="rater">
        <div style="float: left;">
            <form id="rate" method="post" action="">
            <input class="auto-submit-star" type="radio" name="score" value="1" />
            <input class="auto-submit-star" type="radio" name="score" value="2" />
            <input class="auto-submit-star" type="radio" name="score" value="3" />
            <input class="auto-submit-star" type="radio" name="score" value="4" />
            <input class="auto-submit-star" type="radio" name="score" value="5" />
            <input type="hidden" name="maMonAn" value="<%=Model.MaMonAn %>" />
            </form>
        </div>
        <p style="float: left; padding-left: 20px;">
            Rate Now!
        </p>
    </div>
    <div style="clear: both">
    </div>
</div>
