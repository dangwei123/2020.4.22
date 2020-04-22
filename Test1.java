给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> res=new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        traversal(root,0);
        return res;
    }
    private void traversal(TreeNode root,int depth){
        if(root==null){
            return ;
        }
        if(depth==res.size()){
            res.add(root.val);
        }
        depth++;
        traversal(root.right,depth);
        traversal(root.left,depth);
    }
}

给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
class Solution {
    List<String> res=new ArrayList<>();
    private int[] dx={0,0,1,-1};
    private int[] dy={1,-1,0,0};
    private int row;
    private int col;
    public List<String> findWords(char[][] board, String[] words) {
        row=board.length;
        if(row==0) return res;
        col=board[0].length;
        Trie trie=new Trie();
        for(String s:words){
            trie.insert(s);
        }
        Node root=trie.root;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                back(board,root,"",i,j,new boolean[row][col]);
            }
        }
        return res;
    }
    private void back(char[][] board,Node root,String s,int i,int j,boolean[][] isVisited){
        if(i<0||j<0||i>=row||j>=col||isVisited[i][j]){
            return;
        }
        int index=board[i][j]-'a';
        root=root.next[index];
        if(root==null){
            return;
        }
        s+=board[i][j];
        isVisited[i][j]=true;
        if(root.isEnd){
            res.add(s);
            root.isEnd=false;
        }
        
        for(int k=0;k<4;k++){
            int x=i+dx[k];
            int y=j+dy[k];
            back(board,root,s,x,y,isVisited);
        }
        s=s.substring(0,s.length()-1);
        isVisited[i][j]=false;
    }
}

class Trie{
    
    Node root=new Node();
    
    public void insert(String s){
        Node cur=root;
        for(int i=0;i<s.length();i++){
            int index=s.charAt(i)-'a';
            if(cur.next[index]==null){
                cur.next[index]=new Node();
            }
            cur=cur.next[index];
        }
        cur.isEnd=true;
    }
}
class Node{
    public Node[] next=new Node[26];
    public boolean isEnd;
}
    
	
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
class Solution {
    public boolean isAnagram(String s, String t) {
        int n=t.length();
        if(s.length()!=n) return false;
        int[] needs=new int[26];
        int[] windows=new int[26];
        for(int i=0;i<n;i++){
            int index=t.charAt(i)-'a';
            needs[index]++;
        }
        int count=0;
        for(int i=0;i<s.length();i++){
            int index=s.charAt(i)-'a';
            windows[index]++;
            if(needs[index]>0&&needs[index]>=windows[index]){
                count++;
            }
        }
        return count==n;
    }
}

给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
class Solution {
    public int maxProduct(int[] nums) {
        int res=nums[0];
        int max=nums[0];
        int min=nums[0];
        for(int i=1;i<nums.length;i++){
            int pre=max;
            max=Math.max(nums[i],Math.max(max*nums[i],min*nums[i]));
            min=Math.min(nums[i],Math.min(pre*nums[i],min*nums[i]));
            res=Math.max(res,max);
        }
        return res;
    }
}