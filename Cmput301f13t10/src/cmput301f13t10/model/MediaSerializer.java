package cmput301f13t10.model;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
// see http://stackoverflow.com/questions/4795349/how-to-serialize-a-class-with-an-interface
public class MediaSerializer<T> implements JsonSerializer<T>, JsonDeserializer<T>
{

	@Override
	public T deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException
	{
		JsonObject jsonObject = (JsonObject) json;
		JsonElement typeName = getElementFromObject(jsonObject, "type");
		JsonElement data = getElementFromObject(jsonObject, "data");
		Type type = typeFromElement(typeName);
		return context.deserialize( data, type );
	}

	@Override
	public JsonElement serialize( T src, Type typeOfSrc, JsonSerializationContext context )
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty( "type", src.getClass().getName() );
		jsonObject.add( "data", context.serialize( src ) );
		return jsonObject;
	}
	
	private JsonElement getElementFromObject( JsonObject jsonObject, String name ) {
		JsonElement elem = jsonObject.get( name );
		if( elem == null ) throw new JsonParseException("no '" + name + "' member found in what was expected to be an interface wrapper");
		return elem;
	}
	
	private Type typeFromElement( JsonElement elem ) {
		try {
			return Class.forName( elem.getAsString() );
		} catch (ClassNotFoundException e) {
			throw new JsonParseException(e);
		}
	}

}
