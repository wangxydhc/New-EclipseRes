package date201709;

public class Solution {
	public int getMoneyAmount(int n) {
		if (n==1)
			return 0;
		int[][] matrix = new int[n + 1][n + 1];
		for(int m=1;m<n+1;m++){
			matrix[m][m]=0;
//			System.out.println("matrix["+m+"]["+m+"]="+matrix[m][m]);
		}
		for(int k=1;k<n;k++)
			for(int i=1;i+k<=n;i++){
                matrix[i][i+k]=Integer.MAX_VALUE;
				if(k==1)
					matrix[i][i+k]=i;
				for(int j=i+1;j<i+k;j++){					
					
//					System.out.println("i="+i+"  k="+k+" j="+j);
//					System.out.println("matrix["+i+"]["+(j-1)+"]="+matrix[i][j-1]);
//					System.out.println("matrix["+(j+1)+"]["+(i+k)+"]="+matrix[j+1][i+k]);
					matrix[i][i+k]=Math.min(j+Math.max(matrix[i][j-1],matrix[j+1][i+k]),matrix[i][i+k]);
//					System.out.println(matrix[i][i+k]+" " +matrix[i][j]+" " +matrix[j+1][i+k]);
				}
//				System.out.println("matrix["+i+"]["+(i+k)+"]="+matrix[i][i+k]);
			}
		return matrix[1][n];
	}
	public int count(int a,int b,int c,int d,int x){
		int weightS=x;
		int []tab=new int [weightS+1];
		int []weight=new int[]{a,b,2*c,2*d};		
		int []value=new int[]{1,1,1,1};		
		for(int i=0;i<weightS+1;i++)
			tab[0]=0;
		int temp=weightS;
		while(temp>0)
			tab[temp--]=Integer.MIN_VALUE;
		for(int n=0;n<4;n++)
			for(int wgh=0;wgh<=weightS;wgh++)
				for(int k=1;k*weight[n]<=wgh;k++)	
					tab[wgh] =Math.max(tab[wgh-k*weight[n]]+k*value[n],tab[wgh]);
		if(tab[weightS]<0)
			tab[weightS]=0;
		System.out.println(tab[weightS]);
		return tab[weightS];
	}
	public static void main(String[] args) {
		Solution d=new Solution();
		d.count(5, 8, 5, 3, 23);
	}
}


