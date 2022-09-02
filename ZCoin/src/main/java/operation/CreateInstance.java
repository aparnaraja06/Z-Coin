package operation;



public enum CreateInstance {
	
COINOPERATION;
	
	CoinOperation coin = null;
	
	Checker check = null;
	
	public CoinOperation getCoinInstance()throws CustomException
	{
		if(coin==null)
		{
			coin = new CoinOperation();
			
			
		}
		
		return coin;
		
	}
	
	public Checker getCheckInstance()
	{
		if(check==null)
		{
			check = new Checker();
		}
		
		return check;
	}
	
	

}
