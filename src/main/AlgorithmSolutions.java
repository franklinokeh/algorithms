package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class AlgorithmSolutions {

        public static void mergeIntervals(Interval arr[]) {
                if (arr.length <= 0) // Check if the defined set has at least, an interval
                        return;

                Stack<Interval> stack = new Stack<>(); // Create an empty stack

                Arrays.sort(arr, new Comparator<Interval>() {  // sort by increasing order of time of start
                        public int compare(Interval i1, Interval i2) {
                                return i1.start - i2.start;
                        }
                });

                stack.push(arr[0]); // push the first interval to the created stack

                for (int i = 1; i < arr.length; i++)   // Start from the next interval and merge if necessary
                {
                        Interval top = stack.peek(); // get interval from stack top

                        // if current interval does not overlap with stack of top, push it to the stack
                        if (top.end < arr[i].start)
                                stack.push(arr[i]);

                        else if (top.end < arr[i].end) { // If not, update the ending time of top if ending of currentinterval is more
                                top.end = arr[i].end;
                                stack.pop();
                                stack.push(top);
                        }
                }
                System.out.print("Problem statement 1 : "); //Print
                while (!stack.isEmpty()) { //if stack is empty
                        Interval t = stack.pop();
                        System.out.print("[" + t.start + "," + t.end + "] ");
                }
        }

        public static void main(String args[]) {
                Interval arr[] = new Interval[4];
                arr[0] = new Interval(3, 8);
                arr[1] = new Interval(1, 9);
                arr[2] = new Interval(2, 5);
                arr[3] = new Interval(4, 7);
                mergeIntervals(arr);
                ////////////////////
                System.out.println("");
                int array[] = {7, 2, 3, 13};
                int n = array.length;
                System.out.println("Problem statement 2 : " +
                        maxSubarrayXOR(array, n));

        }

        static int maxSubarrayXOR(int arr[], int n)
        {
                int ans = Integer.MIN_VALUE; // Initialize result
                for (int i=0; i<n; i++) // Starting points of subarrays
                {
                        // to store xor of current subarray
                        int curr_xor = 0;

                        // Pick ending points of subarrays starting with i
                        for (int j=i; j<n; j++)
                        {
                                curr_xor = curr_xor ^ arr[j];
                                ans = Math.max(ans, curr_xor);
                        }
                }
                return ans;
        }


}


class Interval {
        int start, end;

        Interval(int start, int end) {
                this.start = start;
                this.end = end;
        }
}
