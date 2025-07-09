class Solution {
    public void rotate(int[][] matrix) {
        int len=matrix.length-1;
		int counter=0;
		int total=matrix.length/2;
    //reverse/swap the elements of necessary rows
    //from two end like two pointer and move it relatively
    //(ex- for 3x3 the outer two rows only required to reverse)
    // to determine the swap/reverse we use "total variable" to loop over the elements

    //"counter" like left pointer from start
    //"len-i" would be right pointer from end
		while(counter<total) {
				int i=counter;
				for(int j=0;j<matrix[i].length;j++) {
					int temp=matrix[i][j];
					matrix[i][j]=matrix[len-i][j];
					matrix[len-i][j]=temp;
				}
			counter++;
		}
    // transpose the elements
    // I have taken the right part of diagonal elements
		for(int i=0;i<len;i++) {
			for(int j=i+1;j<matrix[i].length;j++) {
				int temp=matrix[i][j];
				matrix[i][j]=matrix[j][i];
				matrix[j][i]=temp;
			}
		}
    }
}
