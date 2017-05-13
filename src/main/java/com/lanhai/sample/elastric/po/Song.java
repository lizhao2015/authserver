package com.lanhai.sample.elastric.po;

/**
 * 歌曲PO类
 * 
 * @author Nick
 *
 */
public class Song
{
	private String id;

	private String songName; // 歌曲名称

	private String singerName; // 演唱者

	private String fileName; // 文件名称

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSongName()
	{
		return songName;
	}

	public void setSongName(String songName)
	{
		this.songName = songName;
	}

	public String getSingerName()
	{
		return singerName;
	}

	public void setSingerName(String singerName)
	{
		this.singerName = singerName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

}
