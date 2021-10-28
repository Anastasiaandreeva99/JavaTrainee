package com.nevexis.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Exercise {
public String label()
{
	return new DestinationParcel()
			{
		private String destination = "Sofia";
		public String readLabel()
		{
			return destination;
		}
			}.readLabel();
			
			
}
public static int sum(int[][] arr,int i,int j)
{
	if(arr.length<=j)
		return 0;
	if(arr[j].length-1<=i)
		return arr[j][i] + sum(arr,0,j+1);
	else return arr[j][i]+sum(arr,i+1,j);
	

}
public static void main(String[] args) {
	  int[][] arr = new int[][] {
	        new int[] { 1, 2, 3 },
	        new int[] { 1, 2, 3, 4},
	    };
	   System.out.println( sum(arr,0,0));
}
}
