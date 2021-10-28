
public class Config {
	private int size;
	private int threadsCount;
	private Boolean isQuiet;
	private final Integer precision = 1000;
	
	public Config(String[] args) {
		init(args);
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getThreadsCount() {
		return threadsCount;
	}
	public void setThreadsCount(int threadsCount) {
		this.threadsCount = threadsCount;
	}
	public Boolean getIsQuiet() {
		return isQuiet;
	}
	public void setIsQuiet(Boolean isQuiet) {
		this.isQuiet = isQuiet;
	}
	public Integer getPrecision() {
		return precision;
	}
	
	private void init(String[] args) {
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-q")) {
				isQuiet = true;
			}
			else if(args[i].equals("-p")) {
				size = Integer.parseInt(args[i + 1]);
				i++;
			}
			else if(args[i].equals("-t")) {
				threadsCount = Integer.parseInt(args[i + 1]);
				i++;
			}
			else if(args[i].equals("-o")) {
				i++;
			} else {
				System.out.println("Illegal argument !!!");
			}
		}
		
		if(size == 0) {
			size = 10;
		}

		if(threadsCount == 0) {
			threadsCount = 1;
		}
		
		if(isQuiet == null) {
			isQuiet = false;
		}
		
	
	}
	

}
