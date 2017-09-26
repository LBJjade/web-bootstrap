function f_InitPagination(element){
    element.html('');
    var pageNum = Number(element.attr('pageNum'));
    var pageCount = Number(element.attr('pageCount'));
    var pageExtra = Number(element.attr('pageExtra'));
    var beginPage,endPage;
    if (pageNum-pageExtra>0){
        beginPage=pageNum-pageExtra;
    }else{
        beginPage=1;
    }
    if (pageNum+pageExtra>=pageCount){
        endPage=pageCount;
    }else{
        endPage=pageNum+pageExtra;
    }
    var firstPage,lastPage;
    if (beginPage-pageExtra>1){
        firstPage=beginPage-pageExtra-1;
    }else{
        firstPage=1;
    }
    if (endPage+pageExtra+1<pageCount){
        lastPage=endPage+pageExtra+1
    }else{
        lastPage=pageCount;
    }
    var content = '<ul class="pagination"><li value="'+(pageNum-1==0?1:pageNum-1)+'"><a href="javascript:f_PageClick('+(pageNum-1==0?1:pageNum-1)+');">上一页</a></li>';
    content+='<li value="'+firstPage+'"><a href="javascript:f_PageClick('+firstPage+');">...</a></li>';
    for(var i =beginPage; i<=endPage;i++){
        content +='<li value="'+(-i)+'"><a href="javascript:f_PageClick('+i+');">'+i+'</a></li>'
    }
    content +='<li value="'+lastPage+'"><a href="javascript:f_PageClick('+lastPage+');">...</a></li>';
    content +='<li value="'+(pageNum+1>=pageCount?pageCount:pageNum+1)+'"><a href="javascript:f_PageClick('+(pageNum+1>=pageCount?pageCount:pageNum+1)+');">下一页</a></li></ul>';
    element.append(content);
    element.children('ul').children('li[value='+(-pageNum)+']').attr('class','active');
    element.children('ul').children('li[value='+pageNum+']').attr('class','disabled')
}
//点击事件
function f_PageClick(newPage){
    if (newPage==pageNum){
        return;
    }else{
        //回调函数
        f_PageCallBack(newPage);
    }
}