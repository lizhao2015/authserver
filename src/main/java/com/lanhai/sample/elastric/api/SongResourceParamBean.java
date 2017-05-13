package com.lanhai.sample.elastric.api;

import javax.ws.rs.QueryParam;

/**
 * 歌曲资源查询参数封装类
 * 
 * @author Nick
 *
 */
public class SongResourceParamBean
{
	@QueryParam("w")
	String word;
	
	@QueryParam("from")
	Integer from;
	
	@QueryParam("size")
	Integer size;

	public String getWord()
	{
		return word;
	}

	public Integer getFrom()
	{
		return from;
	}

	public Integer getSize()
	{
		return size;
	}
}
