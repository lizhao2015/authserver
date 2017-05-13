package com.lanhai.sample.elastric.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.status.Status;

import com.lanhai.sample.elastric.dto.ResultDataDTO;
import com.lanhai.sample.elastric.dto.ResultError;
import com.lanhai.sample.elastric.dto.SongDTO;
import com.lanhai.sample.elastric.exception.ResourceExceptions.ResourceException;
import com.lanhai.sample.elastric.po.Song;
import com.lanhai.sample.elastric.service.SongService;
import com.lanhai.sample.elastric.util.ExceptionCode;

/**
 * 歌曲资源
 * 
 * @author Nick
 *
 */
@Component
@Path("songs")
@Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SongResource extends AbstractResource
{
	@Autowired
	SongService songService;

	/**
	 * 搜索歌曲
	 * 
	 * @param paramBean
	 *            查询参数
	 * @return
	 */
	@GET
	public ResultDataDTO<List<SongDTO>> getSongsByWord(@BeanParam SongResourceParamBean paramBean) throws ResourceException
	{

		List<Song> songs = null;
		String decode = null;
		try
		{
			decode = URLDecoder.decode(paramBean.getWord(), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			generateResourceException(500, ExceptionCode.UNSUPPORTED_ENCODING_EXCEPTION, e);
		}

		try
		{
			songs = songService.searchSongs(decode);
		}
		catch (RuntimeException e)
		{
			generateResourceException(500, ExceptionCode.SEARCH_SONGS_EXCEPTION, e);
		}

		List<SongDTO> songDTOs = new ArrayList<SongDTO>();

		if (null != songs)
			for (Song song0 : songs)
			{
				SongDTO songDTO = new SongDTO();
				BeanUtils.copyProperties(song0, songDTO);
				songDTOs.add(songDTO);
			}

		return new ResultDataDTO<List<SongDTO>>(0, songDTOs);
	}

	/*
	 * @GET
	 * 
	 * @Path("query") public ResultDataDTO<List<SongDTO>>
	 * getSongsByWord0(@BeanParam SongResourceParamBean paramBean) {
	 * 
	 * List<Song> song = songService.searchSongs0(paramBean.getWord(),
	 * paramBean.getFrom(), paramBean.getSize());
	 * 
	 * List<SongDTO> songDTOs = new ArrayList<SongDTO>(); for (Song song0 :
	 * song) { SongDTO songDTO = new SongDTO(); BeanUtils.copyProperties(song0,
	 * songDTO); songDTOs.add(songDTO); }
	 * 
	 * return new ResultDataDTO<List<SongDTO>>(0, songDTOs); }
	 */
}
