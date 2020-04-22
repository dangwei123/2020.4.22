给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                return map.get(o1)-map.get(o2);
            }
        });
        
        for(Map.Entry<Integer,Integer> num:map.entrySet()){
            queue.offer(num.getKey());
            if(queue.size()>k){
                queue.poll();
            }
        }
        int[] res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=queue.poll();
        }
        return res;
    }
}

给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        int[] res=new int[n-k+1];
        int max=maxIndex(nums,0,k-1);
        int j=0;
        res[j++]=nums[max];
        int left=0;
        for(int right=k;right<n;right++){
            left++;
            if(left>max){
                max=maxIndex(nums,left,right);
            }
            if(nums[right]>=nums[max]){
                max=right;
            }
            res[j++]=nums[max];
        }
        return res;
    }
    private int maxIndex(int[]nums,int left,int right){
        int max=left;
        for(int i=left+1;i<=right;i++){
            if(nums[i]>=nums[max]){
                max=i;
            }
        }
        return max;
    }
}

实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack=new Stack<>();
        char sign='+';
        int num=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c>='0'){
                num=num*10+(c-'0');
            }
            if((c<'0'&&c!=' ')||i==s.length()-1){
                if(sign=='+'){
                    stack.push(num);
                }else if(sign=='-'){
                    stack.push(-num);
                }else if(sign=='*'){
                    stack.push(stack.pop()*num);
                }else if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                
                num=0;
                sign=c;
            }
        }
        int res=0;
        while(!stack.isEmpty()){
            res+=stack.pop();
        }
        return res;
    }
}

给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。

列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> queue=new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        add(nestedList);
    }
    private void add(List<NestedInteger> nestedList){
        for(NestedInteger i:nestedList){
            if(i.isInteger()){
                queue.offer(i.getInteger());
            }else{
                add(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的 深拷贝。 
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        Node cur=head;
        while(cur!=null){
            Node node=new Node(cur.val);
            node.next=cur.next;
            cur.next=node;
            cur=cur.next.next;
        }
        cur=head;
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }
        cur=head;
        Node newHead=head.next;
        while(cur!=null){
            Node next=cur.next;
            if(next!=null){
                cur.next=next.next;
                
            }
            cur=next;
        }
        return newHead;
    }
}

