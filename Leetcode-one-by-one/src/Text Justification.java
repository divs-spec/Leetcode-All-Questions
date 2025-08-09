public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        
        while (index < words.length) {
            int totalChars = words[index].length();
            int last = index + 1;
            
            //  fit as many words as possible in the current line
            while (last < words.length) {
                if (totalChars + 1 + words[last].length() > maxWidth) break;
                totalChars += 1 + words[last].length(); // 1 for space
                last++;
            }
            
            StringBuilder sb = new StringBuilder();
            int gaps = last - index - 1; // number of gaps between words
            
           
            if (last == words.length || gaps == 0) {
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) sb.append(" ");
                }
                // pad remaining spaces at the end
                int remaining = maxWidth - sb.length();
                for (int i = 0; i < remaining; i++) {
                    sb.append(" ");
                }
            } else {

                // total spaces to distribute
                int spaces = maxWidth - totalChars + gaps; 
                
                int spacePerGap = spaces / gaps;
                int extraSpaces = spaces % gaps;
                
                for (int i = index; i < last - 1; i++) {
                    sb.append(words[i]);
                    for (int s = 0; s < spacePerGap; s++) {
                        sb.append(" ");
                    }
                    if (extraSpaces > 0) {
                        sb.append(" ");
                        extraSpaces--;
                    }
                }
                sb.append(words[last - 1]); // last word in the line
            }
            
            result.add(sb.toString());
            index = last;
        }
        
        return result;
    }
}
