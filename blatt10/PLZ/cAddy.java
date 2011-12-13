package PLZ;

public class cAddy 
implements Comparable<cAddy>
{
	private int _PLZ;
	private String _name;
	private String _kanton;
	
	private int _unknown1,_unknown2,_unknown3;

	
	public cAddy (int PLZ, 
			String name, 
			String kanton,
			int unknown1,//idk what these numbers stand for, but I'll store them anyways
			int unknown2, 
			int unknown3)
	{
		_PLZ = PLZ;
		_name = name;
		_kanton = kanton;
		_unknown1 = unknown1;
		_unknown2 = unknown2;
		_unknown3 = unknown3; 
	}
	
	//used to sort stuff in a list & binary search
	public int compareTo(cAddy a) 
	{
		return _PLZ-a.GetPLZ();
	}
	
	public int GetPLZ()
	{
		return _PLZ;
	}
	
	public String GetName()
	{
		return _name;
	}
	
	public String GetKanton()
	{
		return _kanton;
	}
	
	public int GetUnknown1()
	{
		return _unknown1;
	}
	
	public int GetUnknown2()
	{
		return _unknown2;
	}
	
	public int GetUnknown3()
	{
		return _unknown3;
	}
}
