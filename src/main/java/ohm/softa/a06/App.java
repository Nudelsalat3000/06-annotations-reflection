package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.model.Joke;
import ohm.softa.a06.model.JokeArrayAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App{

	public static void main(String[] args) throws IOException {
		// TODO fetch a random joke and print it to STDOUT
		Gson g = new GsonBuilder().registerTypeAdapter(Joke[].class, new JokeArrayAdapter()).create();

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://api.chucknorris.io/")
			.addConverterFactory(GsonConverterFactory.create(g))
			.build();
		CNJDBApi service = retrofit.create(CNJDBApi.class);

		//Random Joke
		var RandomJokeRequest = service.getRandomJoke();
		var ResponseJoke = RandomJokeRequest.execute().body();
		System.out.println(ResponseJoke.toString());
		System.out.println();

		//Random Joke by Category
		String[] cat = {"animal"};
		var CategoryJokeRequest = service.getRandomJoke(cat);
		var ResponseCategoryJoke = CategoryJokeRequest.execute().body();
		System.out.println(ResponseCategoryJoke.toString());
		System.out.println();

		//Joke by Search
		String searchVal = "animal";
		var SearchJokeRequest = service.JokeBySearch(searchVal);
		var ResponseSearchJoke = SearchJokeRequest.execute().body();
		for (Joke j:
			 ResponseSearchJoke) {
			System.out.println(j.getContent());
		}
		System.out.println();

		//Joke by ID
		String jokeID = "XflrHU7mS_K4ksjyOKveag";
		var GetJokeRequest = service.getJoke(jokeID);
		var ResponseGetJoke = GetJokeRequest.execute().body();
		System.out.println(ResponseGetJoke.toString());




	}
}
