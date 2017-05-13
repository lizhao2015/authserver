package com.lanhai.sample.elastric.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lanhai.sample.elastric.config.AppProperties;
import com.lanhai.sample.elastric.config.SongUriProperties;
import com.lanhai.sample.elastric.po.Song;

@Service
public class SongServiceImpl implements SongService
{
	@Autowired
	RestTemplate restTemplate;

	private final AppProperties properties;

	private final SongUriProperties uriProperties;

	@Autowired
	SongService songService;

	public SongServiceImpl(AppProperties properties, SongUriProperties uriProperties)
	{
		this.properties = properties;
		this.uriProperties = uriProperties;
	}

	@Override
	public List<Song> searchSongs(String word)
	{
		String template = getTemplate();

		String content = restTemplate.postForObject((properties.getUrl() + "/songs/_search"), template.replace("#query_value", word), String.class);

		JSONObject hits0 = null;
		try
		{
			JSONObject queryJson = new JSONObject(content);

			hits0 = queryJson.getJSONObject("hits");

		}
		catch (JSONException e1)
		{
			throw new RuntimeException();
		}

		List<Song> songs = new ArrayList<Song>();

		try
		{
			JSONArray hits = hits0.getJSONArray("hits");

			for (int i = 0; i < hits.length(); i++)
			{
				JSONObject hit = hits.getJSONObject(i);

				JSONObject _source = hit.getJSONObject("_source");

				Song song = new Song();
				song.setFileName(_source.getString("file_name"));
				song.setSongName(_source.getString("song_name"));
				song.setSingerName(_source.getString("singer_name"));

				songs.add(song);
			}
		}
		catch (JSONException e)
		{
			throw new RuntimeException();
		}

		return songs;
	}

	/**
	 * 获取查询策略模板
	 * 
	 * @return
	 */
	private String getTemplate()
	{
		String template = "{\"query\":{\"multi_match\":{\"query\":\"#query_value\",\"fields\":[\"song_name\",\"singer_name^2\"],\"type\":\"phrase\",\"slop\":3}},\"_source\":[\"song_name\",\"singer_name\",\"file_name\"]}";
		return template;
	}

	@Override
	public List<Song> searchSongs0(String word, Integer from, Integer size)
	{
		if (null == from || from < 0)
			from = 0;

		if (null == size || size < 0)
			size = 10;

		List<Song> songs = new ArrayList<Song>();

		Settings settings = Settings.builder().put("cluster.name", this.properties.getClusterName()).put("client.transport.sniff", true).build();

		TransportClient client = null;
		try
		{
			client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(this.properties.getHost()), this.properties.getClientPort()));

			SearchRequestBuilder responsebuilder = client.prepareSearch(uriProperties.getIndex()).setTypes(uriProperties.getType());

			SearchResponse response = responsebuilder.setQuery(QueryBuilders.multiMatchQuery(word, new String[]
			{ "song_name", "singer_name^2" })).setTypes("phrase").setFrom(from).setSize(size).setExplain(true).execute().actionGet();

			SearchHits hits0 = response.getHits();

			if (null == hits0)
				return songs;

			SearchHit[] hits = hits0.getHits();
			if (hits.length == 0)
				return songs;
			for (int i = 0; i < hits.length; i++)
			{
				Map<String, Object> source = hits[i].getSource();
				Song song = new Song();
				song.setFileName((String) source.get("file_name"));
				song.setSongName((String) source.get("song_name"));
				song.setSingerName((String) source.get("singer_name"));

				songs.add(song);
			}
		}
		catch (UnknownHostException e)
		{
			throw new RuntimeException();
		}
		finally
		{
			client.close();
		}

		return songs;
	}
}
