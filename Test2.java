给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
class Solution {
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        k%=n;
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
    }
    private void reverse(int[] nums,int left,int right){
        while(left<right){
            int tmp=nums[left];
            nums[left++]=nums[right];
            nums[right--]=tmp;
        }
    }
}

打乱一个没有重复元素的数组。
class Solution {
    Random random=new Random();
    private int[] original;
    private int[] copy;
    private int len;
    public Solution(int[] nums) {
        len=nums.length;
        original=nums.clone();
        copy=nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        copy=original.clone();
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for(int i=0;i<len;i++){
            int r=i+random.nextInt(len-i);
            swap(copy,i,r);
        }
        return copy;
    }
    private void swap(int[] nums,int left,int right){
        int tmp=nums[left];
        nums[left]=nums[right];
        nums[right]=tmp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
 
 给定两个数组，编写一个函数来计算它们的交集。
 class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list=new LinkedList<>();
        List<Integer> res=new ArrayList<>();
        for(int num:nums1){
            list.add(num);
        }
        for(int num:nums2){
            if(list.contains(Integer.valueOf(num))){
                list.remove(Integer.valueOf(num));
                res.add(num);
            }
        }
        int[] r=new int[res.size()];
        for(int i=0;i<res.size();i++){
            r[i]=res.get(i);
        }
        return r;
    }
}

给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

数学表达式如下:

如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3) return false;
        int a=Integer.MAX_VALUE;
        int b=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=a){
                a=nums[i];
            }else if(nums[i]<=b){
                b=nums[i];
            }else{
                return true;
            }
        }
        return false;
    }
}

设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) —— 将元素 x 推入栈中。
pop() —— 删除栈顶的元素。
top() —— 获取栈顶元素。
getMin() —— 检索栈中的最小元素。
class MinStack {
    private static class Node{
        private int value;
        private int min;
        public Node(int value,int min){
            this.value=value;
            this.min=min;
        }
    }
    
    private Stack<Node> stack=new Stack<>();
    private int size=0;
    /** initialize your data structure here. */
    public MinStack() {

    }
    
    public void push(int x) {
        if(size==0){
            stack.push(new Node(x,x));
        }else{
            int m=Math.min(stack.peek().min,x);
            stack.push(new Node(x,m));
        }
        size++;
    }
    
    public void pop() {
        if(size==0){
            return;
        }
        size--;
        stack.pop();
    }
    
    public int top() {
        if(size==0) return 0;
        return stack.peek().value;
    }
    
    public int getMin() {
        if(size==0) return 0;
        return stack.peek().min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
 
 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 class Solution {
    private int res;
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums,0,nums.length-1,nums.length-k);
        return res;
    }
    private void quickSort(int[] nums,int left,int right,int k){
        int pivot=partition(nums,left,right);
        if(pivot==k){
            res=nums[pivot];
            return;
        }else if(pivot<k){
            quickSort(nums,pivot+1,right,k);
        }else{
            quickSort(nums,left,pivot-1,k);
        }
    }
    private int partition(int[] nums,int left,int right){
        int pivot=nums[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&nums[j]>=pivot){
                j--;
            }
            nums[i]=nums[j];
            while(i<j&&nums[i]<=pivot){
                i++;
            }
            nums[j]=nums[i];
        }
        nums[i]=pivot;
        return i;
    }
}

中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
class MedianFinder {
    PriorityQueue<Integer> max=new PriorityQueue<>((a,b)->b-a);
    PriorityQueue<Integer> min=new PriorityQueue<>();
    private int size;
    /** initialize your data structure here. */
    public MedianFinder() {

    }
    
    public void addNum(int num) {
        size++;
        max.offer(num);
        min.offer(max.poll());
        if((size&1)==1){
            max.offer(min.poll());
        }
    }
    
    public double findMedian() {
        if((size&1)==1){
            return max.peek();
        }
        return (max.peek()+min.peek())/2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
 
 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row=matrix.length;
        if(row==0) return 0;
        int col=matrix[0].length;
        int left=matrix[0][0];
        int right=matrix[row-1][col-1];
        while(left<right){
            int mid=left+(right-left)/2;
            int count=0;
            int i=row-1;
            int j=0;
            while(i>=0&&j<col){
                if(matrix[i][j]<=mid){
                    count+=(i+1);
                    j++;
                }else{
                    i--;
                }
            }
            
            if(count<k){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }
}

