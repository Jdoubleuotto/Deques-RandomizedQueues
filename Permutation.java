/* *****************************************************************************
 *  Name: John Otto
 *  Date: 1/2/2023
 *  Description:A client program Permutation.java that takes an integer
 * k as a command-line argument; reads a sequence of strings from standard input
 * using StdIn.readString(); and prints exactly k of them, uniformly at random.
 * Print each item from the sequence at most once.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    // 8 animals and want to print 2 at random
    // print one with 1/n probability where n is the amount of animals
    // then 1 /  n - 1

    // the algorithm must be O(n) in time
    // constant space O(1) with either one deque or randomized queue object
    // n times

    public static void main(String[] args) {
        // initialize the command line argument to be int k
        int k = Integer.parseInt(args[0]);
        // instantiate a randomized queue object of type string
        RandomizedQueue<String> q = new RandomizedQueue<>();

        // print a random selection of k amount of a string input
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }

        // print out random values from the queue k number of times
        for (int i = 0; i < k; i++) {
            String random = q.dequeue();
            System.out.println(random);
        }
    }
}

