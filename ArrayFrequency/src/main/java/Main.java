
public class Main {

	public static void main(String[] args) {
//		System.out.print(longestSentence("To the moon.Sisi is very kind!Hello."));
		System.out.println(game2(5,1));
	}

	public void sum() {
		int[] arr = new int[] { 1, 1, 3, 3, 7, 6, 3, 2, 4, 9, 1 };
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					sum++;
				}

			}
		}

		System.out.println(sum);
	}



	public static int longestSentence(String text) {
		int maxSentenceLen = 0;

		String[] sentences = text.split("[\\!|\\.|\\?]");

		for (String sentence : sentences) {

			int sentenceLen = sentence.split("[\\s+]").length;
			if (sentenceLen > maxSentenceLen) {
				maxSentenceLen = sentenceLen;
			}

		}
		return maxSentenceLen;

	}
	
	
	public static int game(int tokens,int maxAllIn,int n )
	{
		if(tokens==n)
			{
			return 1;
			}
		else
		{
			if(maxAllIn<=0 || tokens*2>n)return 1+ game(tokens+1,maxAllIn,n);
			return 1+Math.min(game(tokens+1,maxAllIn,n),game(tokens*2,maxAllIn-1,n));
		}
		
		
	}
	
	public static int game2(int tokens,int maxAllIn)
	{
		if(tokens<=1)
			{
			return 1;
			}
		else
		{
			if(maxAllIn<=0 || tokens<=1 || tokens%2!=0)
				return 1+ game2(tokens-1,maxAllIn);
			return 1+Math.min(game2(tokens-1,maxAllIn),game2(tokens/2,maxAllIn-1));
		}
		
		
	}
	
	
	

}
