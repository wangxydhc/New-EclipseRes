package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Generator {
	String []elements=new String[]{"149","278","YM","wxy","SY1506","0275","XZ","0012"}; 
//	String JUNIOR="149",SENIOR="278",NAME="wxy",NAMECAP="WXY",UNIVERSITY="1506325",SYMBLE=".",RANDOM1="3281",RANDOM2="0275";
	String keyWords="";
	int level=0;
	Generator(String keyWords,int level){
		this.keyWords=keyWords.toUpperCase();
		this.level=level;
	}
	public int keyWordsHandler(){		
		if (keyWords.length()<2)
			return 0;
		int []res=new int[keyWords.length()];
		for(int i=0;i<keyWords.length();i++){
			Random r=new Random();
			Character c=keyWords.charAt(i);
			long temp=c*2+100+c;
			r.setSeed(temp);
			int qw=r.nextInt(5000);
			res[i]=qw;
		}	
		int seed=1;
		for(int w:res){
			seed*=w;
			if (seed>1000000)
				seed/=100;
		}
		return seed;
	}
	public Object[] sequence(int level,int seed){
		if (seed==0)
			return null;
		int len;
		if(level==1)
			len=4;
		else if(level==2)
			len=2;
		else 
			len=0;
		Set <Integer>set=new HashSet<Integer>();
		Random r=new Random(seed);
		while(len>0){
//			System.out.println(len);			
			int temp=r.nextInt(8);
			if (set.contains(temp))
				continue;
			else{
				set.add(temp);
				len--;
//				System.out.println(temp);
			}
		}
		return set.toArray();
	}
	public String numToPW(Object[]nums){
		if (nums==null)
			return "error";
		StringBuffer sb=new StringBuffer();
		sb.append(this.keyWords);
		int len=nums.length;
		while(len>0){
			sb.append(elements[(int)nums[--len]]);			
			if(len%2==1)
				sb.append(".");
		}
		return sb.toString();
	}
	
	public String start() {
		int seed=this.keyWordsHandler();
		Object []ent=this.sequence(this.level, seed);
		String res=this.numToPW(ent);
		return res;
		
	}
	public static void main(String[] args) throws IOException {
		
		InputStreamReader is = new InputStreamReader(System.in);
		 BufferedReader br = new BufferedReader(is);
		String name =br.readLine();
		Generator gn=new Generator(name,1);
		System.out.println(gn.start());
		Generator gn1=new Generator(name,1);
		System.out.println(gn1.start());
	}
}
