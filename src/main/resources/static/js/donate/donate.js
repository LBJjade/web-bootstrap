function f_Donate() {
    var projectType = $("#sel_project_type").find("option:selected").text();
    var projectName = $("#sel_project").find("option:selected").text();
    if (projectType == "请选择项目类别") {
        alert("你还没有选择项目");
    } else if (flag == 0) {
        alert("你还没有选择金额");
    } else {
        $("#div_cf_donate").model('show');
        var projectTypeId = $("#sel_project_type").val();
        var projectId = $("#sel_project").val();
        var amount = span_money * 100;
        $("#donateAmount").html(span_money);
        $.ajax({
            url: '/donate/donate',
            type: 'post',
            data: {
                projectTypeId: projectTypeId,
                projectId: projectId,
                amount: amount
            },
            dataType: 'json',
            success:
                function (data) {
                    if (data.code === 200) {
                        $("#qrCodeImageId").attr("src", data.result.qrCodeImageBase64);
                        $("#orderNo").html(data.result.orderNo);
                        checkPayStatusInterval = setInterval("checkPayStatus()", 3000);
                    }
                }
        });
        var timer = 120;

        function Countdown() {
            if (timer >= 1) {
                timer -= 1;
                t = setTimeout(function () {
                    Countdown();
                }, 1000);
                $("#waitingPayTimerText").html(timer);
            } else {
                $("#qrCodeImageId").attr("src", "");
                $(".fresh").css("z-index", "3");
                $(".code_time").html("二维码已经失效，请刷新！");
            }
        }
        Countdown();
    }
}

function checkPayStatus() {
    var orderNo = $("#orderNo").html();
    $.ajax({
        url: '/wxpay/status',
        type: 'post',
        data: {
            orderNo: orderNo
        },
        dataType: 'json',
        success:
            function (data) {
                if (data.code === 200) {
                    var returnCode = data.result.notify_return_code;
                    var resultCode = data.result.notify_result_code;
                    var errCodeDesc = data.result.notify_err_code_desc;
                    if (returnCode != undefined && resultCode != undefined && resultCode === "SUCCESS" && returnCode === "SUCCESS") {
                        $(".code_time").html("支付成功。<span id=\"autoCloseAfterPaidTimerText\" class=\"time\" style=\"color: red\">5</span>秒自动关闭。");
                        stopCount();
                        autoCloseAfterPaidInterval = setInterval("autoCloseAfterPaid()", 1000);
                    } else {
                        $(".code_time").html("<span class=\"notifyErrCodeDesc\" style=\"color: red\">errCodeDesc</span>");
                    }
                }
            }
    });
}

$(".code_img").mouseover(function () {
    $("#qr_logo").fadeIn(2000);
})


$(".close_qr").click(function () {
    $("#div_cf_donate").model('hide');
    $("#qrCodeImageId").attr("src", "");
    stopCount();
})

var autoCloseTimeout = 5;
function autoCloseAfterPaid() {
    if (autoCloseTimeout > 1) {
        autoCloseTimeout -= 1;
        $("#autoCloseAfterPaidTimerText").html(autoCloseTimeout);
        $("#qrCodeImageId").attr("src", "");
    } else {
        clearInterval(autoCloseAfterPaidInterval);
        $(".close_qr").click();
        window.location.reload();
    }
}

//关闭函数
function stopCount() {
    clearTimeout(t);
    clearInterval(checkPayStatusInterval);
}

//刷新二维码
$(".fresh").click(function () {
    $(".code_time").html("距离二维码过期还有<span id=\"waitingPayTimerText\" class=\"time\" style=\"color: red\"></span>秒");
    $(".fresh").css("z-index", "-1");
    $("#qrCodeImageId").removeClass("qrCodeImageId");
    var projectTypeId = $("#sel_project_type").val();
    var projectId = $("#sel_project").val();
    // var amount = $(".money_2").html().replace("￥","").replace("&yen;", "");
    // amount = math.eval(amount * 100);
    var amount = span_money * 100;
    $(".order_fund").html(amount / 100);
    $.ajax({
        url: '/donate/donate',
        type: 'post',
        data: {
            projectTypeId: projectTypeId,
            projectId: projectId,
            amount: amount
        },
        dataType: 'json',
        success:
            function (data) {
                if (data.code === 200) {
                    $("#qrCodeImageId").attr("src", data.result.qrCodeImageBase64);
                }
            }
    });
    var timer = 120;

    function Countdown() {
        if (timer >= 1) {
            timer -= 1;
            t = setTimeout(function () {
                Countdown();
            }, 1000);
            $(".time").html(timer);
        } else {
            $("#qrCodeImageId").attr("src", "");
            $(".fresh").css("z-index", "3");
            $("#qrCodeImageId").attr("class", "qrCodeImageId");
            $(".code_time").html("二维码已经失效，请刷新！");
        }
    }
    Countdown();
})