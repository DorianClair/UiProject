package application;

public class CircleChartInfo {
	
	int amount = 1800;
	int max = 2000;
	
	public CircleChartInfo()
	{
		
	}
	
	public double getAmount()
	{
		return  (double) amount;
	}
	
	public double getMax()
	{
		return (double) max;
	}
	
	public void setAmount(int a)
	{
		amount = amount + a;
	}

	public void setMax(int m)
	{
		max = m;
	}
}
