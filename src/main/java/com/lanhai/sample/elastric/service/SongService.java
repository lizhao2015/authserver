package com.lanhai.sample.elastric.service;

import java.util.List;

import com.lanhai.sample.elastric.po.Song;

/**
 * 歌曲服务类
 * 
 * @author Nick
 *
 */
public interface SongService
{
	/**
	 * 搜索歌曲
	 * 
	 * @param uri
	 *            歌曲资源目录
	 * @param word
	 *            匹配搜索内容
	 * @return List<Song>
	 */
	List<Song> searchSongs(String word);

	/**
	 * 搜索歌曲
	 * 
	 * @param word
	 *            匹配搜索内容
	 * @param from
	 *            初始页
	 * @param size
	 *            每页显示数量
	 * @return List<Song>
	 */
	@Deprecated
	List<Song> searchSongs0(String word, Integer from, Integer size);
}
