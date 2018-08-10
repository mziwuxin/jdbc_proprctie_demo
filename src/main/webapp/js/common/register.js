$(function () {
  //获取username的value值
  var username=$("#username").val();
  //光标丢失的事件
  $("#username").blur(function () {
        if(username===""){
          $("#username").html("用户名不允许为空！！！")
        }else{
          //不为空就可以ajax提交到后台
            $.ajax({
                url:"/login?methodName=validateName",
                data:{
                  "username":username
                },
                type:"POST",
                dataType:"json",

                success:function (data) {
                    //alert(data.status);
                    if(data.status==1){
                      $("#errorname").html(data.message);

                    }else {
                        $("#username").html("可以使用");
                    }
                }
            });
        }
  });

});