/*
Farmer John is arranging his N cows in a line to take a photo (1≤N≤100,000). The height of the ith cow in sequence is hi, and the heights of all cows are distinct.
As with all photographs of his cows, FJ wants this one to come out looking as nice as possible. He decides that cow i looks "unbalanced" if Li and Ri differ by more than factor of 2, where Li and Ri are the number of cows taller than i on her left and right, respectively. That is, i is unbalanced if the larger of Li and Ri is strictly more than twice the smaller of these two numbers. FJ is hoping that not too many of his cows are unbalanced.

Please help FJ compute the total number of unbalanced cows.
*/

import java.io.*;
import java.util.*;

public class bphoto {
	private static final String DIRECTORY = "C:\\Users\\Erik Han\\Google Drive\\eclipse\\Contests\\src\\",
			TASK = "bphoto";
	private static BufferedReader reader;
	private static PrintWriter out;
	private static int N;
	// private static int[] ct;
	private static int[] higher;
	private static int[] higher2;
	private static int[] data;
	private static ArrayList<Integer> heights = new ArrayList<Integer>();
	private static HashMap<Integer, Integer> key = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			reader = new BufferedReader(new FileReader(// DIRECTORY +
					TASK + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(// DIRECTORY
																	// +
					TASK + ".out")));
		} catch (IOException e) {
			reader = new BufferedReader(new FileReader(DIRECTORY + TASK + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(DIRECTORY + TASK + ".out")));
		}

		// System.out.println(ct.length);
		N = Integer.parseInt(reader.readLine());
		// ct = new int[N];
		higher = new int[N];
		higher2 = new int[N];
		data = new int[N];
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(reader.readLine());
			key.put(data[i], i);
		}
		for (int i = 0; i < N; i++) {
			int amt = add(data[i]);
			higher[i] = amt;
			// System.out.println(ct.length+" "+data.length+" "+data[i]);
			// ct[key.get(data[i])]++;
		}
		/*for (int i = 0; i < N; i++) {
			System.out.println(higher[i]);
		}*/
		heights.clear();

		for (int i = N - 1; i >= 0; i--) {
			int amt = add(data[i]);
			higher2[i] = amt;
		}
		/*System.out.println();
		for (int i = 0; i < N; i++) {
			System.out.println(higher2[i]);
		}*/

		//System.out.println();
		int ans = 0;
		for (int i = 0; i < N; i++) {
			if (Math.max(higher[i], higher2[i]) > Math.min(higher[i], higher2[i]) * 2) {
				//System.out.println(i);
				ans++;
			}
		}
		System.out.println(ans);
		out.println(ans);
		out.close();

	}

	public static int add(int targ) {
		if (heights.isEmpty()) {
			heights.add(targ);
			return 0;
		}
		int l = 0;
		int r = heights.size() - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (heights.get(mid) < targ) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
			// System.out.println(r);
		}

		if (r == -1) {
			heights.add(l, targ);
			return heights.size() - 1;
		}

		int ans = heights.size() - 1 - r;

		heights.add(l, targ);
		return ans;
	}
}
