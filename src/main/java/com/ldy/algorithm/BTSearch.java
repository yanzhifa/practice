package com.ldy.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yanz3 on 5/3/18.
 */
public class BTSearch {

    static class Tree {
        public int x;
        public Tree l;
        public Tree r;

        public Tree(int x, Tree l, Tree r) {
            this.x = x;
            this.l = l;
            this.r = r;
        }

        public Tree getL() {
            return l;
        }

        public void setL(Tree l) {
            this.l = l;
        }

        public Tree getR() {
            return r;
        }

        public void setR(Tree r) {
            this.r = r;
        }
    }

    public static ArrayList<String> binaryTreePaths(Tree root) {
        // Write your code here
        ArrayList<String> result = new ArrayList<String>();
        if (root == null)
            return result;
        String path = "";
        //Paths(root, result, path);

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(root.x);
        List<HashSet<Integer>> hashSetList = new ArrayList<>();
        paths(root, hashSetList, hashSet);
        return result;
    }

    public static void paths(Tree root, List<HashSet<Integer>> result, HashSet<Integer> path) {
        HashSet<Integer> tmp = new HashSet<>();
        if (root == null)
            return;
        if (root.l == null && root.r == null) {
            path.add(root.x);
            tmp.addAll(path);
            result.add(tmp);
            return;
        }
        path.add(root.x);
        tmp.addAll(path);
        paths(root.l, result, tmp);
        paths(root.r, result, tmp);

    }

    public static void Paths(Tree root, ArrayList<String> result, String path) {
        if (root == null)
            return;
        if (root.l == null && root.r == null) {
            if (path.equals(""))
                path += root.x;
            else
                path += "->" + root.x;
            result.add(path);
            return;
        }
        if (path.equals(""))
            path += root.x;
        else
            path += "->" + root.x;
        Paths(root.l, result, path);
        Paths(root.r, result, path);

    }

    public static void main(String[] args) {
        Tree root = new Tree(5,null,null);
        Tree t1 = new Tree(3, null, null);
        Tree t2 = new Tree(6, null, null);
        Tree t3 = new Tree(2, null, null);
        Tree t4 = new Tree(5, null, null);
        Tree t5 = new Tree(3, null, null);

        root.setL(t1);
        t1.setL(t2);
        t2.setL(t3);

        root.setR(t4);
        t4.setL(t5);

        System.out.println(binaryTreePaths(root));
    }
}
