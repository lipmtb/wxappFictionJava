<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test fiction</title>
 <style>
    *{
	 margin:0;
	 padding:0; 
	}

	.box{
	  display:flex;
	  flex-direction:column;
	  justify-content:space-around;
	  align-items:center;
	  width:400px;
	  height:100px;
	  margin:10px auto;
	  border:1px solid #ccc;
	}
  </style>
</head>

<body>
     <div class="box">
	   <textarea class="userTokenBox" cols="40" rows="4"></textarea>
	   <button class="getUserToken">获取token</button>
	</div>

    <div class="box">
	    <textarea class="fictionTypeBox" cols="40" rows="4"></textarea>
	   <button class="getFictionByType">用小说类型编号获取小说</button>
	</div>

    <div class="box">
	    <textarea class="singleFictionBox" cols="40" rows="4"></textarea>
	   <button class="getFiction">获取一项小说</button>
	</div>
   
   <div class="box">
	    <textarea class="fictionChapterContentBox" cols="40" rows="4"></textarea>
	   <button class="getFictionChapter">获取小说章节内容</button>
	</div>
	
	
	<div class="box">
	    <ul class="commentListBox">
	    </ul>
	   <button class="getFictionCommentBtn">获取小说评论列表</button>
	</div>
	
	<div class="box">
	   <button class="getGoodFictionBtn">获取精品小说</button>
	</div>
	
	
	<div class="box">
	    <input type="text" name="keystr" value="" id="search-input"/>
	   <button class="searchFictionBtn">search fiction</button>
	</div>
	
	
	<div class="box">
	     <h2>搜索提示</h2>
	    <input type="text" name="key" value="" id="search-text-tip"/>
	    <ul id="hidList" style="display:none;width:200px;
	               height:400px;list-style:none;border:1px solid #ccc;background-color:#fff;">
	    </ul>
	</div>
   <script>
    
	 /*登录注册接口:返回token  */
	  let getUserTokenBtn=document.querySelector(".box .getUserToken");
	 let userTokenBox=document.querySelector(".box .userTokenBox");
	 getUserTokenBtn.addEventListener("click",function(){
		 console.log(this);
		 $.ajax({
			type:'POST',
			url:'http://192.168.30.12:8080/wxMobileApp/readerLogin/login',
			data:{
				 code:'043WiiLm02p2Xk16wbIm0CAoLm0WiiL7',
				 nickName:'俊伟',
				 avatarUrl:'https://wx.qlogo.cn/mmopen/vi_32/4e6uNOxwJZFYUPw85aCCUpGVAa6fSvYJMMMc5W0jYuvxCYriaGT4PJ8CWf85VWph0gOf5h61w4nbgB63ePmzj1w/132',
				 readerGender:'男'
			},
			dataType:'json',
			async:true,
			success:function(res){
				  console.log("success");
				  console.log(res);
				  userTokenBox.value=res.token;
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
			}
			
		 });
		    
	 },false);

	 
	  /*根据类型id号获取小说列表
	  return :
		fAuthor: "白夜行作者"
		 fBrief: "“白夜行只希望能手牵手在太阳下散步”，这个象征故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、压抑、悲凉的故事片段像纪录片一样一一还原：没有痴痴相思，没有海枯石烂，只剩下一个冰冷绝望的诡计，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的凄苦爱情中悲切动容……"
		 fCoverUrl: "http://192.168.30.12:8080/img/白夜行.png"
		 fId: 1
		 fName: "白夜行"
	  */
	  let ftypeBox=document.querySelector(".box .fictionTypeBox");
	 let getFictionByTypeBtn=document.querySelector(".box .getFictionByType");
	 getFictionByTypeBtn.addEventListener("click",function(){
		 console.log(this);
		 $.ajax({
			type:'POST',
			url:'http://192.168.30.12:8080/wxMobileApp/loadingfiction/fictiontype',
			data:{
				token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
				ftypeid:'2'
			},
			dataType:'json',
			async:true,
			success:function(res){
				  console.log(res);
				  console.log("success");
				  console.log(typeof(res.fictionlist));
			},
			error:function(err){
				console.log("catch an error");
				console.log(err);
				
			}
			
		 });
		 
	 },false);
	 
	 
	 
	 /*根据小说fid获取单个小说
	  return :
		  chapterNum: 2
		  collectNum: 0
		  fAuthor: "白夜行作者"
		  fBrief: "“白夜行只希望能手牵手在太阳下散步”，这个象征故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、压抑、悲凉的故事片段像纪录片一样一一还原：没有痴痴相思，没有海枯石烂，只剩下一个冰冷绝望的诡计，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的凄苦爱情中悲切动容……"
		  fCoverUrl: "http://192.168.30.12:8080/img/白夜行.png"
		  fName: "白夜行"
		  likeNum: 0
		  readNum: 0
	 */
	 let singleFictionBox=document.querySelector(".box .singleFictionBox");
	 let getFictionBtn=document.querySelector(".box .getFiction");
	 getFictionBtn.addEventListener("click",function(){
		 $.ajax({
				type:'POST',
				url:'http://192.168.30.12:8080/wxMobileApp/loadingfiction/fiction',
				data:{
					token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
					fid:1
				},
				dataType:'json',
				async:true,
				success:function(res){
					  console.log(res);
					  console.log(res.fiction);
					  console.log("success");
					  console.log(typeof(res.fiction));
					  singleFictionBox.value=res.fiction.fName;
				},
				error:function(err){
					console.log("catch an error");
					console.log(err);
					
				}
				
			 });
		 
	 },false);
	 
	 /*根据fid和chapterNum获取小说章节内容
	  return
	   {state:0/1,
		 chapterContent:str
	    }
	 */
	     let fictionContentBox=document.querySelector(".box .fictionChapterContentBox");
		 let getFictionContentBtn=document.querySelector(".box .getFictionChapter");
		 getFictionContentBtn.addEventListener("click",function(){
			 $.ajax({
					type:'POST',
					url:'http://192.168.30.12:8080/wxMobileApp/loadingfiction/fictionchapter',
					data:{
						token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
						fid:'2',
						fchapterNum:'1'
					},
					dataType:'json',
					async:true,
					success:function(res){
						  console.log(res.chapterContent);
						  console.log("success");
						  console.log(typeof(res.chapterContent));
						  fictionContentBox.value=res.chapterContent;
					},
					error:function(err){
						console.log("catch an error");
						console.log(err);
						
					}
					
				 });
		 },false);
		 
		 
		 /*加载读者评论列表
		  return [{
			  
			      avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoYunX9kJqGVsV7nQicb9kEwib9Ta5L0LRjE2nIiceiatv1tOOaflu53Rr7NO3libMcb53B89aLTvSzNKQ/132"
				  commentContent: "评论小说1111111撒发生法"
				  commentTime: "2019/6/14 下午10:42:44"
				  nickName: "加减乘除"
		  },{
			  avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoYunX9kJqGVsV7nQicb9kEwib9Ta5L0LRjE2nIiceiatv1tOOaflu53Rr7NO3libMcb53B89aLTvSzNKQ/132"
				  commentContent: "222222222222说法法国大使馆是的"
				  commentTime: "2019/6/14 下午10:42:54"
				  nickName: "加减乘除"
		  }
		  ]
		 */
		 var getCommentBtn=document.querySelector(".box .getFictionCommentBtn");
		 getCommentBtn.onclick=function(){
			 $.ajax({
				 type:'POST',
				 url:'http://192.168.30.12:8080/wxMobileApp/loadingfiction/fictioncommentlist',
				 data:{
					 token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
					 fid:'3'
				 },
				 async:true,
				 dataType:'json',
				 success:function(res){
					 console.log(res.state);
					 let data=res.commentList;
					 console.log(data);
					 let arr=Object.assign([],data);
					 arr.forEach(function(item,index,that){
						 let liBox=document.createElement("li");
						 liBox.innerText=item.commentContent;
						 document.querySelector(".box .commentListBox").appendChild(liBox);
					 });
				 },
				 error:function(err){
					 console.log("catch an error");
				     console.log(err);
				 }
				 
			 });
		 }
		 
		 /*获取精品小说 
		  return 
		  [ {
			   fId: 2, fName: "小泪痣", 
			  fBrief: "“小泪痣只希望能手牵手在太阳下散步”，这个象征故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、…只剩下一个冰冷绝望的诡计，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的凄苦爱情中悲切动容……",
			  fCoverUrl: "http://192.168.30.12:8080/img/小泪痣.jpg", 
			  fAuthor: "小泪痣作者" 
			  
		    },
		    {
				      Author: "鸢恋作者"
					  fBrief: "“鸢恋只希望能手牵手在太阳下散步”，这个象征故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、压抑、悲凉的故事片段像纪录片一样一一还原：没有痴痴相思，没有海枯石烂，只剩下一个冰冷绝望的诡计，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的凄苦爱情中悲切动容……"
					  fCoverUrl: "http://192.168.30.12:8080/img/鸢恋.jpg"
					  fId: 4
					  fName: "鸢恋"
		    }
		   ]
		 */
		 let getGoodFictionBtn=document.querySelector(".box .getGoodFictionBtn");
		 getGoodFictionBtn.onclick=function(){
			 $.ajax({
				 type:'POST',
				 url:'http://192.168.30.12:8080/wxMobileApp/loadingfiction/goodfiction',
				 data:{
					 token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F'
				 },
				 async:true,
				 dataType:'json',
				 success:function(res){
					 console.log(res.state);
					console.log(res.fictionlist);
				 },
				 error:function(err){
					 console.log("catch an error");
				     console.log(err);
				 }
				 
			 });
		 }
		 
		 /*读者搜索小说
		 return 
		  [ {
			   fId: 2, fName: "小泪痣", 
			  fBrief: "“小泪痣只希望能手牵手在太阳下散步”，这个象征故事内核的绝望念想，有如一个美丽的幌子，随着无数凌乱、…只剩下一个冰冷绝望的诡计，最后一丝温情也被完全抛弃，万千读者在一曲救赎罪恶的凄苦爱情中悲切动容……",
			  fCoverUrl: "http://192.168.30.12:8080/img/小泪痣.jpg", 
			  fAuthor: "小泪痣作者" 
			  
		    }
		 */
		 let searchBtn=document.querySelector(".box .searchFictionBtn");
		 let searchInputBox=document.getElementById("search-input");
		 let searchKeyText="";
		 searchInputBox.onchange=function(e){
			 searchKeyText=e.currentTarget.value;
			 console.log(searchKeyText);
		 }
		 searchBtn.onclick=function(){
			 $.ajax({
				 type:'POST',
				 url:'http://192.168.30.12:8080/wxMobileApp/loadingfiction/searchfiction',
				 data:{
					 token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
					 keystr:searchKeyText
				 },
				 async:true,
				 dataType:'json',
				 success:function(res){
					 console.log(res.state);
					 let flist=res.fictionlist;
					console.log(flist);
					for(let idx in flist){
						console.log(flist[idx].fBrief);
					}
				 },
				 error:function(err){
					 console.log("catch an error");
				     console.log(err);
				 }
				 
			 });
		 }
		 
		 
		 /*读者搜索提示
		  return res.textlist
		  [
			  fName1,fName2,...
		  ]
		 */
		 let searchTipInput=document.getElementById("search-text-tip");
		 let hidList=document.getElementById("hidList");
		 searchTipInput.onfocus=function(){
			  
			 hidList.style.display="block";
		 }
		 searchTipInput.onblur=function(){
			 hidList.style.display="none";
		 }
		
		 searchTipInput.onkeyup=function(e){
			 let searchtiptext=e.currentTarget.value;
			 hidList.innerHTML="";
			 if(searchtiptext.length>0){
				 $.ajax({
					 type:'POST',
					 url:'http://192.168.30.12:8080/wxMobileApp/loadingfiction/searchtip',
					 data:{
						 token:'oZdY65EHvOiPXPjPawZ79Au19x0Y069CD7BD4A5856C4729C6D8BCD6F3D8F',
						 keystr:searchtiptext
					 },
					 async:true,
					 dataType:'json',
					 success:function(res){
						
						 let textlist=res.textlist;
						console.log(textlist);
						for(let idx in textlist){
						  let liItem=document.createElement("li");
						  liItem.innerText=textlist[idx];
						  hidList.appendChild(liItem);
						}
					 },
					 error:function(err){
						 console.log("catch an error");
					     console.log(err);
					 }
					 
				 });
			 }
			 
		 }
		 
		 
   </script>
   
 <script
  src="http://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>
</body>
</html>