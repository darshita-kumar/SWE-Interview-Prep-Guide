/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import static java.lang.System.out;

class GFG {
    public static void main(String[] args) {

        int n = 5;
        DisjointSet ds = new DisjointSet(n);
        ds.unionBySize(3, 4);
        ds.unionBySize(2, 3);
        ds.unionBySize(1, 2);
        ds.unionBySize(0, 1);
        ds.unionBySize(5, 6);
        
        out.println("Do 1 and 3 belong to same component?: "+(ds.findUParent(1)==ds.findUParent(3)));
        out.println("Do 1 and 5 belong to same component?: "+(ds.findUParent(1)==ds.findUParent(5)));
    }
}

class DisjointSet {
    int[] parent;
    int[] size;
    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++) {
            parent[i]=i;
            size[i]=1;
        }
    }

    public int findUParent(int node) {
        if(node == parent[node])
            return node;
        // Path compression
        parent[node] = findUParent(node);
        return parent[node];
    }

    public void unionBySize(int u, int v) {
        int parentU = findUParent(u);
        int parentV = findUParent(v);
        if(parentU == parentV) return;
        // Attach smaller to larger
        if(size[parentU] > size[parentV]) {
            parent[parentV] = parentU;
            size[parentU] += size[parentV];
        } else {
            parent[parentU] = parentV;
            size[parentV] += size[parentU];
        }
    }
}

