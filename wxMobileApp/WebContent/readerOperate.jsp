<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试读者点赞阅读收藏和评论</title>
<style>
  *{
   margin:0;
   padding:0;
  }
  .box{
    margin:10px auto;
    border:2px dashed red;
  }
  
  .commentBox{
	  display:flex;
	  flex-direction:column;
	  justify-content:space-around;
	  align-items:center;
     margin:20px auto;
     width:460px;
     height:200px;
     border:2px solid #ccc;
  }
  
  .commentBox #inputarea{
    height:40px;
    box-shadow:1px 2px 10px 2px #ddd;
  }
</style>
</head>
<body>
    <div class="box">
      <button class="readerLikeBtn">读者点赞</button>
    </div>
    
    <div class="box">
      <button class="readerReadBtn">读者阅读</button>
    </div>
    
     <div class="box">
      <button class="readerCollectBtn">读者收藏</button>
    </div>
    
    
    <div class="commentBox">
      <div class="showCommentBox"></div>
      <input type="text" name="readerInput" maxlength="40" size="22" id="inputarea"/>
      <button class="readerCommentBtn">读者评论</button>
    </div>
    
    <script>
    
    /*读者点赞测试 input:
    token:
     fid:
      etype:'read/like/unlike/collect/uncollect'
    */
      var readerLikeBtn=document.querySelector(".box .readerLikeBtn");
      readerLikeBtn.onclick=function(){
    	  console.log(this);
    	  $.ajax({
    		  type:'POST',
    		 url:'http://192.168.30.12:8080/wxMobileApp/readerdo/readcollectlike',
    		 data:{
    			 token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
    			 fid:'1',
    			 etype:'uncollect'
    			 //etype:'like'
    		 },
    		 async:true,
    		 dataType:'json',
    		 success:function(res){
				  console.log(res);
				  console.log(res.state);
				
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
				
			}
    	  });
      }
      
      
      /*测试读者评论*/
      var inputbox=document.getElementById("inputarea");
      var inputtext="";
      inputbox.onchange=function(e){
    	  inputtext=e.currentTarget.value;
    	  console.log(inputtext);
      }
      
      var commentBtn=document.querySelector(".commentBox .readerCommentBtn");
      commentBtn.onclick=function(){
    	  $.ajax({
    		 type:'POST',
    		 url:'http://192.168.30.12:8080/wxMobileApp/readerdo/readercomment',
    		 data:{
    			 token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
    			 fid:'3',
    			 commentContent:inputtext,
    			 commentTime:new Date().toLocaleString()
    		 },
    		 async:true,
    		 dataType:'json',
    		 success:function(res){
				  console.log(res);
				  console.log(res.state);
				  if(res.state==1){
					  console.log("评论成功");
					  inputbox.value='';
				  }
				
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
				
			}
    	  });
       }
      
      
    </script>
    
  <script
  src="http://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>

</body>
</html>