class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (words.length == 0 || s.length() == 0) {
            return ans;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) return ans;

        // Frequency map of original words
        HashMap<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Iterate with sliding window starting at each offset
        for (int i = 0; i < wordLen; i++) {
            int start = i;
            int matchedWords = 0;
            HashMap<String, Integer> currFreq = new HashMap<>();

            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String currWord = s.substring(j, j + wordLen);

                if (wordFreq.containsKey(currWord)) {
                    currFreq.put(currWord, currFreq.getOrDefault(currWord, 0) + 1);
                    matchedWords++;

                    while (currFreq.get(currWord) > wordFreq.get(currWord)) {
                        String leftWord = s.substring(start, start + wordLen);
                        currFreq.put(leftWord, currFreq.get(leftWord) - 1);
                        start += wordLen;
                        matchedWords--;
                    }

                    if (matchedWords == wordCount) {
                        ans.add(start);
                    }
                } else {
                    currFreq.clear();
                    matchedWords = 0;
                    start = j + wordLen;
                }
            }
        }

        return ans;
    }
}
