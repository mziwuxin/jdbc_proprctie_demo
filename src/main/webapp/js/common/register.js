$(function () {
  //��ȡusername��valueֵ
  var username=$("#username").val();
  //��궪ʧ���¼�
  $("#username").blur(function () {
        if(username===""){
          $("#username").html("�û���������Ϊ�գ�����")
        }else{
          //��Ϊ�վͿ���ajax�ύ����̨
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
                        $("#username").html("����ʹ��");
                    }
                }
            });
        }
  });

});