package Test;
import java.util.Scanner;
public class SumIsClosestToZero {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		  System.out.println("Enter the size of the array:");
		     int size,i,j;
		     size=sc.nextInt();
		     int arr[ ]=new int[size];
		  System.out.println("Enter the Element of the array:");
		      for(i=0;i<size;i++)
		     {
		         arr[i]=sc.nextInt();
		     }
  int First_element=arr[0], Second_element=arr[1],sum, Closest_Zero_Sum=First_element+Second_element;

		      for(i=0;i<size-1;i++)
		      {
		          for(j=i+1;j<size;j++)
		          {
		               sum=arr[i]+arr[j];
		               if(Math.abs(sum)<=Math.abs(Closest_Zero_Sum))
		               {
		                   Closest_Zero_Sum=sum;
		                   First_element=arr[i];
		                   Second_element=arr[j];
		               }
		          }
		      }
 System.out.println("Closest to Zero Pair is ("+First_element+","+Second_element+")");
	      sc.close();
	}
}
