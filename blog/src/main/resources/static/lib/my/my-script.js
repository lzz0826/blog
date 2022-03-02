


$(function () {
    $("#newblog-container").load("/footer/newblog")
});

$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide');
});

$('#payButton')
    .popup({
        popup : $('.payQR.popup'),
        on    : 'click',
        position :'bottom center',
    })
;

tocbot.init({
    // Where to render the table of contents.
    tocSelector: '.js-toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '.js-toc-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2',
    // For headings inside relative or absolute positioned containers within content.
    hasInnerContainers: true,
});

$('#functionMenu')
    .popup({
        popup : $('.toc-container.popup'),
        on    : 'click',
        position :'left center',
    })
;
$('#QR')
    .popup({
        popup : $('.fb.popup'),

        position :'left center',
    })
;

let qrcode = new QRCode("qrcode", {
    text: "http://jindo.dev.naver.com/collie",
    width: 110,
    height: 110,
    colorDark : "#000000",
    colorLight : "#ffffff",
    correctLevel : QRCode.CorrectLevel.H
});

$('#scrollTo-button').click(function (){
    $(window).scrollTo(0.,2000);
})
let waypoint = new Waypoint({
    element: document.getElementById('waypoint'),
    handler: function(direction) {
        if (direction=='down'){
            $('#toolbar').show(500);
        }else {
            $('#toolbar').hide(500);
        }
        console.log('Scrolled to waypoint!'+direction);
    }
})
$('#comment-text').form({
    fields: {
        content: {
            identifier: 'content',
            rules: [{
                type: 'empty',
                prompt: '請輸入內容',
            }]
        },

        nickname: {
            identifier: 'nickname',
            rules: [{
                type: 'empty',
                prompt: '請輸入姓名'
            }]
        },
        mail: {
            identifier: 'mail',
            rules: [{
                type: 'empty',
                prompt: '請輸入信箱'
            }]
        },
    }
})



$(function () {
    $("#comment-container").load("/comments/{id}(id=${blog.id})")
});


$('#comment-button').click(function (){
    var boo = $('#comment-text').form('validate form');
    if (boo){
        console.log("較驗成功")
        postData()
    }else {
        console.log("較驗失敗")
    }
});

function postData() {
    $("#comment-container").load("/comments", {
        "parentcomment.id": $("[name='parentcomment.id']").val(),
        "blog.id": $("[name='blog.id']").val(),
        "nickname": $("[name='nickname']").val(),
        "email": $("[name='email']").val(),
        "content": $("[name='content']").val()
    },function (responseTxt, statusTxt, xhr){
        $(window).scrollTo($('#comment-container'),500);
        clearContent();
    });
}
function clearContent() {
    $("[name='content']").val("");
    $("[name='parentcomment.id']").val("")
    $("[name='content']").attr("placeholder","請輸入評論訊息...");
}

function reply(obj) {
    var commentId = $(obj).data('commentid');
    var commentNickname = $(obj).data('commentnickname');
    $("[name='content']").attr("placeholder","@"+commentNickname).focus();
    $("[name='parentcomment.id']").val(commentId);
    $(window).scrollTo($('#comment-text'),500);


}
