import java.util.HashMap;

class STC extends Object
{
	String name;
	String type;
	DataType dataType;
	String scope;
	HashMap<String,Object> values;

	public STC(String n, String t, String s, DataType d)
	{
		name = n;
		type = t;
		scope = s;
		dataType = d;
		values = new HashMap<String, Object>();
	}

	public STC(String n, String t, DataType d)
	{
		name = n;
		type = t;
		dataType = d;
		values = new HashMap<String, Object>();
	}

	public void setScope(String s)
	{
		scope = s;
	}

	public String getScope(String s)
	{
		return scope;
	}

	public void setValue(String n, Object value)
	{
		values.put(n, value);
	}

	public Object getValue(String n)
	{
		return values.get(n);
	}

}
