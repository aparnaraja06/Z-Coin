package operation;



public enum CreateInstance {
	
COINOPERATION;
	
	
	CoinOperation coin = null;

	Checker check = null;
	
	MysqlOperation mysql = null;
	
	PsqlOperation psql = null;
	
	
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
	
	public MysqlOperation getMysqlInstance()throws CustomException
	{
		if(mysql==null)
		{
			mysql = new MysqlOperation();
		}
		
		return mysql;
	}
	
	public PsqlOperation getPsqlInstance()throws CustomException
	{
		if(psql==null)
		{
			psql = new PsqlOperation();
		}
		
		return psql;
	}
	
	

}
