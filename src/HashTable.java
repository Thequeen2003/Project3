import java.util.ArrayList;
import java.util.Hashtable;

public class HashTable {
//Goal this will find and print a pair of numbers in a array that will add up the desired target

        public static void findSumPair(Integer target, ArrayList<Integer> array) {
            Hashtable<Integer, Boolean> htable = new Hashtable<>(); // HashTable to store numbers
            for (int num : array) {
                int complement = target - num;
                // Check if the complement of the current number exisits in the table
                if (htable.containsKey(complement)) {
                    System.out.println("Pair found: " + num + ", " + complement);
                    return;
                }
                htable.put(num, true);
            }
            /// Store the current number in the HashTable
            System.out.println("No pair found.");
        }

// Goal: this will calculate the number of distinct values in the input array

        public static int distinctValues(ArrayList<Integer> array) {
            Hashtable<Integer, Boolean> htable = new Hashtable<>();
            for (int num : array) {
                //add the absolute value of the number to the Hashtable
                htable.put(Math.abs(num), true);
            }
            //returning the size of this hashtable
            return htable.size();
        }

        public static void main(String[] args) {
            testFindSumPair();
            testDistinctValues();
        }

        private static void testFindSumPair() {
            ArrayList<Integer> array = new ArrayList<>();
            array.add(5); array.add(2); array.add(3); array.add(4); array.add(1);
            System.out.println("Test FindSumPair:");
            findSumPair(7, array);
        }

        private static void testDistinctValues() {
            ArrayList<Integer> array = new ArrayList<>();
            array.add(-1); array.add(0); array.add(1); array.add(2); array.add(-2); array.add(-3);
            System.out.println("Test DistinctValues:");
            int count = distinctValues(array);
            assert count == 4 : "DistinctValues should return 4, returned " + count;
            System.out.println("DistinctValues passed with count = " + count);
        }
    }

