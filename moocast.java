/*
Farmer John's N cows (1≤N≤1000) want to organize an emergency "moo-cast" system for broadcasting important messages among themselves.
Instead of mooing at each-other over long distances, the cows decide to equip themselves with walkie-talkies, one for each cow. These walkie-talkies each have a limited transmission radius, but cows can relay messages to one-another along a path consisting of several hops, so it is not necessary for every cow to be able to transmit directly to every other cow.

The cows need to decide how much money to spend on their walkie-talkies. If they spend $X, they will each get a walkie-talkie capable of transmitting up to a distance of X‾‾√. That is, the squared distance between two cows must be at most X for them to be able to communicate.

Please help the cows determine the minimum integer value of X such that a broadcast from any cow will ultimately be able to reach every other cow.
*/

import java.io.*;
import java.util.*;

public class moocast {
	private static BufferedReader reader;
	private static PrintWriter out;
	private static int N;
	private static int[][] cords;
	private static int[] closest;
	private static boolean[] hasClose;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		reader = new BufferedReader(new FileReader("moocast.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		N = Integer.parseInt(reader.readLine());
		cords = new int[N][2];
		closest = new int[N];
		hasClose = new boolean[N];
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < N; i++) {
			build.append(reader.readLine() + " ");
		}
		StringTokenizer tk = new StringTokenizer(build.toString());
		for (int i = 0; i < N; i++) {
			for (int a = 0; a < 2; a++) {
				cords[i][a] = Integer.parseInt(tk.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			int close = -1;
			for (int a = 0; a < N; a++) {
				if (i != a) {
					if (close == -1 || distSqu(i, a) < distSqu(i, close)) {
						close = a;
					}
				}
			}
			closest[i] = close;
			hasClose[close] = true;
		}
		for (int i = 0; i < N; i++) {
			if (!hasClose[i]) {
				if (distSqu(i, closest[i]) > distSqu(closest[i], closest[closest[i]])) {
					closest[closest[i]] = i;
				}
				hasClose[i] = true;
			}
		}
		int min = 0;
		for (int i = 0; i < N; i++) {
			int currDist = distSqu(i, closest[i]);
			if (currDist > min) {
				min = currDist;
			}
		}
		System.out.println(min);
		out.println(min);
		out.close();
	}

	public static int distSqu(int i1, int i2) {
		return (int) Math.pow(cords[i1][0] - cords[i2][0], 2) + (int) Math.pow(cords[i1][1] - cords[i2][1], 2);
	}
}
