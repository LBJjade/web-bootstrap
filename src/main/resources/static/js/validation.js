// 真实姓名验证 真实姓名，规定2个汉字以上，6个汉字以下
function f_nameValidate(inputName) {
    var v_name = /^[\u4E00-\u9FA5]{2,6}$/;
    if(!v_name.test(inputName)){
        return false;
    }else {
        return true;
    }
}

var v_phone = /^1[3|4|5|7|8][0-9]{9}$/;
//手机号码验证
function f_phoneValidate(inputPhone) {
    if(!v_phone.test(inputPhone)){
        return false;
    }else {
        return true;
    }
}

//身份证验证 现在公民身份证都是18位，15位身份证号码已失效
function f_idcardValidate(inputIdcard) {
    var v_idcard = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(!v_idcard.test(inputIdcard)){
        return false;
    }else {
        return true;
    }
}

//统一社会信用代码代码
function f_codeValidate(inputCode) {
    var v_code = /^[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}$/;
    if(!v_code.test(inputCode)){
        return false;
    }else {
        return true;
    }
}