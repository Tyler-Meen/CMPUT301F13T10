/*
 * "Creative Commons https://github.com/rayzhangcl/ESDemo/blob/master/ESDemo/src/ca/ualberta/cs/CMPUT301/chenlei/ElasticSearchResponse.java" by rayzhangcl is licensed under CC0
 */

package cmput301f13t10.model;

public class ElasticSearchResponse<T>
{
	String _index;
	String _type;
	String _id;
	int _version;
	boolean exists;
	T _source;
	double max_score;

	public T getSource()
	{
		return _source;
	}
}
