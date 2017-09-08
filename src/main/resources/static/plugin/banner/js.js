//banner图片淡入淡出,基于jQuery
var totalImg; //图片总数
var currentImg=0; //当前显示的图片的下标
var nextImg=0;//下一张图片下标
var timeOut;//setTimeOut
var shotTime=3000;//图片轮换时间
function f_begin() {
    totalImg=$("#ul_img").children().length;
    if (totalImg==0){
        return;
    }
    for(var i=0;i<totalImg;i++){
        var tempLi=document.createElement("li");
        $(tempLi).attr("onclick","showImg("+i+")");
        $(tempLi).addClass(i==0?"li_focus":"li_out");
        $("#ul_index").append(tempLi);
        $("#p_banner").html($("#ul_img").find("img").eq(0).attr("alt"));
    }
    timeOut=setTimeout(changeImg,shotTime);

    $("#div_prev").click(function () {
        if(currentImg===totalImg-1){
            showImg(0);
        }else if (currentImg===0){
            showImg(totalImg-1);
        }else{
            showImg(currentImg-1);
        }
    });

    $("#div_next").click(function () {
        changeImg();
    });
}

function changeImg() {
    clearTimeout(timeOut);
    $("#ul_img").children().eq(currentImg).fadeOut(1000);
    $("#ul_index").children().eq(currentImg).attr("class","li_out");
    if (currentImg===totalImg-1){
        currentImg=0;
    }else{
        currentImg++;
    }
    if(nextImg!==0){
        currentImg=nextImg;
        nextImg=0;
    }
    $("#ul_img").children().eq(currentImg).fadeIn(1000);
    $("#ul_index").children().eq(currentImg).attr("class","li_focus");
    $("#p_banner").html($("#ul_img").find("img").eq(currentImg).attr("alt"));
    timeOut=setTimeout(changeImg,shotTime);
}

//鼠标点击切换图片
function showImg(img) {
    clearTimeout(timeOut);
    if(currentImg===img){
        //点击当前图片,重置timeout
        timeOut=setTimeout(changeImg,shotTime);
    }else{
        //显示目标图片
        nextImg=img;
        changeImg();
    }
}
