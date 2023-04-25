package ohm.softa.a06.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JokeArrayAdapter extends TypeAdapter<Joke[]> {

	@Override
	public void write(JsonWriter out, Joke[] value) throws IOException {

	}

	@Override
	public Joke[] read(JsonReader in) throws IOException {
		var JsonElement = JsonParser.parseReader(in);
		var JsonMembers = JsonElement.getAsJsonObject().get("result").getAsJsonArray();

		ArrayList<Joke> result = new ArrayList<Joke>();

		Gson g = new Gson();

		for (JsonElement member:
			 JsonMembers) {
			result.add(g.fromJson(member, Joke.class));
		}
		return result.toArray(new Joke[0]);
	}
}
