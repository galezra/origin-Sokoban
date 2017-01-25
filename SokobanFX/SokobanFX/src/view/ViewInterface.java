package view;

public interface ViewInterface {
	public String getUserCommand();
	public void closeAllThreads();
	public String getArrByString();
	public void setSteps(int step);
	public void setArr(char[][]arr);
	public void setDone(boolean isDone);

}
