package com.lanhai.sample.elastric.util;

/**
 * 异常代码
 * <p>
 * 100~199 为通用异常
 * 200~300 歌曲检索异常
 * <p>
 *
 */
public enum ExceptionCode
{
	UNKONWN_EXCEPTION(-1, "Unkown Excepiton"), 
	UNSUPPORTED_ENCODING_EXCEPTION(-100, "Unsupported_encoding_exception"), 
	SEARCH_SONGS_EXCEPTION(-200, "Search songs exception"), ;

	final int code;

	final String message;

	ExceptionCode(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public int getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}

}
