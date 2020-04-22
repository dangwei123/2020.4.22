给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
class Solution {
    public String minWindow(String s, String t) {
        int[] needs=new int[127];
        int[] windows=new int[127];
        int len=t.length();
        for(int i=0;i<len;i++){
            char c=t.charAt(i);
            needs[c]++;
        }
        int left=0;
        int right=0;
        int valid=0;
        int minlen=s.length()+1;
        String res="";
        while(right<s.length()){
            char c=s.charAt(right);
            windows[c]++;
            if(needs[c]>0&&needs[c]>=windows[c]){
                    valid++;
            }
            right++;
            while(valid==len){
                if(right-left<minlen){
                    begin=left;
                    minlen=right-left;
                    res=s.substring(left,right);
                }

                char l=s.charAt(left);
                if(needs[l]>0&&needs[l]>=windows[l]){
                    valid--;
                }
                windows[l]--;
                left++;
            }
        }
        return res;
    }
}

给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] needs=new  int[26];
        int[] windows=new int[26];
        int n=s1.length();
        for(int i=0;i<n;i++){
            needs[s1.charAt(i)-'a']++;
        }

        int left=0;
        int right=0;
        int valid=0;
        while(right<s2.length()){
            int c=s2.charAt(right)-'a';
            windows[c]++;
            if(needs[c]>0&&needs[c]>=windows[c]){
                valid++;
            }
            right++;
            while(valid==n){
                if(right-left==n){
                    return true;
                }
                int index=s2.charAt(left)-'a';
                if(needs[index]>0&&needs[index]>=windows[index]){
                    valid--;
                }
                windows[index]--;
                left++;
            }
        }
        return false;
    }
}

给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] needs=new int[26];
        int[] windows=new int [26];
        int len=p.length();
        for(int i=0;i<len;i++){
            needs[p.charAt(i)-'a']++;
        }
        List<Integer> res=new ArrayList<>();
        int left=0;
        int right=0;
        int count=0;
        while(right<s.length()){
            int a=s.charAt(right)-'a';
            windows[a]++;
            if(needs[a]>0&&needs[a]>=windows[a]){
                    count++;
            }
            while(count==len){
                if(right-left+1==len){
                    res.add(left);
                }
                int b=s.charAt(left)-'a';
                if(needs[b]>0&&needs[b]>=windows[b]){
                       count--;
                }
                windows[b]--;
                left++;
            }
            right++;
        }
        return res;
    }
}

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] windows=new int[127];
        int left=0;
        int right=0;
        int res=0;
        while(right<s.length()){
            char c=s.charAt(right);
            windows[c]++;
            while(left<=right&&windows[c]>1){
                windows[s.charAt(left)]--;
                left++;
                
            }

            if(right-left+1>res){
                res=right-left+1;
            }
            right++;
        }
        return res;
    }
}

