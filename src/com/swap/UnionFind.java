package com.swap;

public class UnionFind {
	
	int[] set;
	
	public UnionFind(int size)
	{
		set = new int[size];
		for (int i = 0; i < size; i++)
		{
			set[i] = i;
		}
	}
	
	public void union(int p, int q)
	{
		if (connected(p, q)) return;
		int pid = set[p];
		for (int i = 0; i < set.length; i++)
		{
			if (set[i] == pid) set[i] = set[q];
		}
	}
	
	public int find(int p)
	{
		return set[p];
	}
	
	boolean connected(int p, int q)
	{
		return set[p] == set[q];
	}

	public int getSize(int p)
	{
		int sum = 0;
		
		for (int i = 0; i < set.length; i++)
		{
			if (connected(p, i)) sum++;
		}
		
		return sum;
	}
}
