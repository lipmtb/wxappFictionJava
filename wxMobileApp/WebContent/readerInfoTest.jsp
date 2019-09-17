<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>读者阅读过，收藏过的小说列表，是否点赞过，是否收藏过小说</title>
<style>
*{
 margin:0;
 padding:0;
}
  .testBox{
    display:flex;
    flex-direction:column;
    justify-content:space-around;
    align-items:center;
    width:400px;
    margin:40px auto;
    border:1px solid #666;
    padding:20px 0;
  }
  
   .testBox button,.testBox select{
      border:1px solid #bbb;
      margin:10px 0;
   }
  
  
</style>
</head>
<body>

 <div class="testBox">
   <button class="getReadFListBtn">加载加减乘除阅读过的小说列表</button>
 </div>
 
 <div class="testBox">
   <button class="getCollectFListBtn">加载加减乘除收藏过的小说列表</button>
 </div>
 
 
 <div class="testBox">
   <select class="likeSel">
      <option value="1">白夜行</option>
      <option value="2">小泪痣</option>
      <option value="3">人使之恋</option>
      <option value="4">鸢恋</option>
  </select>
  
   <button class="hasLikeBtn">加减乘除是否赞过某个小说</button>
 </div>
 
 <div class="testBox">
    <select class="collectSel">
      <option value="1">白夜行</option>
      <option value="2">小泪痣</option>
      <option value="3">人使之恋</option>
      <option value="4">鸢恋</option>
    </select>
   <button class="hasCollectBtn">加减乘除是否收藏过某个小说</button>
 </div>
 
 <script
  src="http://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
 </script>
 
 <script>
 
 
 /*1.读者阅读过的小说列表 
  return [{…}, {…}, {…}]
 {
	fAuthor: "小泪痣作者"
	fBrief: "“小泪痣只希望能手牵手在太阳下散步”，这个象征故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、压抑、悲凉的故事片段像纪录片一样一一还原：没有痴痴相思，没有海枯石烂，只剩下一个冰冷绝望的诡计，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的凄苦爱情中悲切动容……"
	fCoverUrl: "http://192.168.30.12:8080/img/小泪痣.jpg"
	fId: 2
	fName: "小泪痣"
 }
 */
   let getReadFListBtn=document.querySelector(".testBox .getReadFListBtn");
   getReadFListBtn.onclick=function(){
	   $.ajax({
			type:'POST',
			url:'http://192.168.30.12:8080/wxMobileApp/readerStorage/readerread',
			data:{
				token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F'
			},
			dataType:'json',
			async:true,
			success:function(res){
				console.log(res.state);
				console.log(res.readfictionlist);
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
				
			}
			
		 });
   }
   
   /*2. 读者收藏过的小说列表 
   
    return [{…}, {…}, {…}]
 {
	fAuthor: "小泪痣作者"
	fBrief: "“小泪痣只希望能手牵手在太阳下散步”，这个象征故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、压抑、悲凉的故事片段像纪录片一样一一还原：没有痴痴相思，没有海枯石烂，只剩下一个冰冷绝望的诡计，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的凄苦爱情中悲切动容……"
	fCoverUrl: "http://192.168.30.12:8080/img/小泪痣.jpg"
	fId: 2
	fName: "小泪痣"
 }
   */
   let getCollectFListBtn=document.querySelector(".testBox .getCollectFListBtn");
   getCollectFListBtn.onclick=function(){
	   $.ajax({
			type:'POST',
			url:'http://192.168.30.12:8080/wxMobileApp/readerStorage/readercollect',
			data:{
				token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F'
			},
			dataType:'json',
			async:true,
			success:function(res){
				console.log(res.state);
				console.log(res.collectfictionlist);
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
				
			}
			
		 });
   }
   
   /*读者 是否点赞过某小说 return 1 代表点赞过*/
   let likeText="1";
   let likeSel=document.querySelector(".testBox .likeSel");
   let hasLikeBtn=document.querySelector(".testBox .hasLikeBtn");
   likeSel.onchange=function(e){
	   let options=this.options;
	   likeText=this[options.selectedIndex].value;
	   console.log(likeText);
   }
   
   hasLikeBtn.onclick=function(){
	   console.log("加减乘除是否点赞过小说: "+likeText);
	   $.ajax({
			type:'POST',
			url:'http://192.168.30.12:8080/wxMobileApp/readerStorage/haslike',
			data:{
				token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
				fid:likeText
			},
			dataType:'json',
			async:true,
			success:function(res){
				console.log("加减乘除是否点赞过小说结果: "+res.haslike);
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
				
			}
			
		 });
   }
   
   /*读者是否收藏过 某小说 return 1 代表收藏过*/
   let collectText="1";
   let collectSel=document.querySelector(".testBox .collectSel");
   let hasCollectBtn=document.querySelector(".testBox .hasCollectBtn");
   collectSel.onchange=function(e){
	   let options=this.options;
	   collectText=this[options.selectedIndex].value;
	   console.log(collectText);
   }
   
   hasCollectBtn.onclick=function(){
	   console.log("加减乘除是否收藏过小说: "+collectText);
	   $.ajax({
			type:'POST',
			url:'http://192.168.30.12:8080/wxMobileApp/readerStorage/hascollect',
			data:{
				token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
				fid:collectText
			},
			dataType:'json',
			async:true,
			success:function(res){
				console.log("加减乘除是否收藏 过小说结果: "+res.hascollect);
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
				
			}
			
		 });
   }
 </script>
</body>
</html>