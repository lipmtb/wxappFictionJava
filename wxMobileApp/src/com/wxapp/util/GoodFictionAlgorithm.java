package com.wxapp.util;

import java.util.ArrayList;
import java.util.List;
import com.wxapp.entity.Fiction;
public class GoodFictionAlgorithm {
	
	public static List<Fiction> sortList(List<Fiction>  list){
        
		 int size=list.size();
        List<Fiction> res=new ArrayList<Fiction>();
		 while(list.size()>1){
			
			 int maxAge=list.get(0).getfCollectReaderSet().size();
			 Fiction maxFiction=list.get(0);
			 for(int i=1;i<size;i++){
				 Fiction tempFiction=list.get(i);
				 int tempAge=tempFiction.getfCollectReaderSet().size();
                if(tempAge>maxAge){
				    maxAge=tempAge;
					maxFiction=list.get(i);
				 }
			 }

			 res.add(maxFiction);
			 list.remove(maxFiction);
		     size--;
		 }
		 res.add(list.get(0));

		 return res;

	}
}
