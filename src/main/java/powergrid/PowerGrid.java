package powergrid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PowerGrid {

    private static ArrayList<Integer[]> triplets= new ArrayList<>();

	/**
	 * Given loads a, b, and c, at three power stations, is it possible to
	 * balance the load using the permitted operation?
	 * 
	 * @param a
	 *            the load at Power Station A, a >= 0
	 * @param b
	 *            the load at Power Station B, b >= 0
	 * @param c
	 *            the load at Power Station C, c >= 0
	 * @return true if load balancing is possible and false otherwise
	 */
public static boolean canLoadBalance(int a, int b, int c) {
//	boolean flag=false;
//	try{
//		flag= recursiveCheck(a, b, c);
//	}catch (StackOverflowError e){
//		System.out.println("Error caught! ");
//	}
//	triplets.clear();
//	return flag;

	return recursiveCheck(a,b,c);

}
//		int temp_a, temp_b, temp_c;
//		while (!areEqual(a, b, c)) {
//			if (a < b) {
//				temp_a = a;
//				temp_b = b;
//				temp_c = c;
//				b = b - a;
//				a = 2 * a;
//				if(temp_b!=2*temp_a){if (endCondition(temp_a, temp_b, temp_c, a, b, c)) break; }
//			}
//
//			if (b < a) {
//				temp_a = a;
//				temp_b = b;
//				temp_c = c;
//				a = a - b;
//				b = 2 * b;
//				if(temp_a!=2*temp_b){if (endCondition(temp_a, temp_b, temp_c, a, b, c)) break;}
//			}
//			if (a < c) {
//				temp_a = a;
//				temp_b = b;
//				temp_c = c;
//				c = c - a;
//				a = 2 * a;
//				if(temp_c!=2*temp_a){ if (endCondition(temp_a, temp_b, temp_c, a, b, c)) break;}
//			}
//			if (c < a) {
//				temp_a = a;
//				temp_b = b;
//				temp_c = c;
//				a = a - c;
//				c = 2 * c;
//				if(temp_a!=2*temp_c){if (endCondition(temp_a, temp_b, temp_c, a, b, c)) break; }
//			}
//			if (b < c) {
//				temp_a = a;
//				temp_b = b;
//				temp_c = c;
//				c = c - b;
//				b = 2 * b;
//				if(temp_c!=2*temp_b){ if (endCondition(temp_a, temp_b, temp_c, a, b, c)) break; }
//			}
//			if (c < b) {
//				temp_a = a;
//				temp_b = b;
//				temp_c = c;
//				b = b - c;
//				c = 2 * c;
//				if(temp_b!=2*temp_c){if (endCondition(temp_a, temp_b, temp_c, a, b, c)) break;}
//			}
//		}
//		if (areEqual(a, b, c)) return true;
//		return false;
//	}
//	private static boolean areEqual(int a, int b, int c){
//		return a==b && b==c;
//	}
//	private static boolean endCondition(int temp_a,int temp_b,int temp_c, int a,int b, int c){
//		if(a==temp_b && temp_a==b) return true;
//		if(b==temp_c && c== temp_b) return true;
//		if(c==temp_a && a==temp_c) return true;
//		return false;
//	}

	private static boolean recursiveCheck(int a, int b, int c) throws StackOverflowError{
		//stop condition is if we see the exact set again at some point in the recursion with the new set of elements
		int max=findMax(a,b,c);
		int min=findMin(a,b,c);
		int mid=findMid(a,b,c);
		Integer[] currentTriplet= {max,mid,min};
		/* The logic of recursion: if you can get the "n"th triplet(most people think of this a,b,c as the first triplet) to work, or to fail,
		it will the same for all the remaining (domino blocks)*/
		if(max==mid && mid==min) return true;
		if(hasTriplet(currentTriplet))return false;
		else{
			max=max-min;
			min=min*2;
			//initiate new a,b,c
			triplets.add(currentTriplet);
			return recursiveCheck(max,mid,min);
		}
	}

	private static boolean hasTriplet(Integer[] current){
        for(int i=0; i<triplets.size(); i++){
            boolean flag=true;
            for(int j=0; j<3; j++){
                if(!triplets.get(i)[j].equals(current[j])){
                  flag=false;
                }
            }
            if(flag) return true;
        }
        return false;
    }


	private static int findMin(int a, int b, int c){
		return Math.min(Math.min(a,b),c);
	}
	private static int findMax(int a, int b, int c){
		return Math.max(Math.max(a,b),c);
	}
	private static int findMid(int a, int b, int c){
		if(a== findMax(a,b,c) && b==findMin(a,b,c)) return c;
		else if(b== findMax(a,b,c) && a==findMin(a,b,c)) return c;
		else if(a== findMax(a,b,c) && c==findMin(a,b,c)) return b;
		else if(c== findMax(a,b,c) && a==findMin(a,b,c)) return b;
		else if(b== findMax(a,b,c) && c==findMin(a,b,c)) return a;
		else return a;
	}


}
