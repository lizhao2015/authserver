package com.lanhai.sample.elastric.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 返回结果传输对象
 * 
 * @author Nick
 *
 * @param <T>
 *            业务传输对象
 */
@XmlRootElement
public class ResultDataDTO<T>
{
	public int result;

	@XmlElement
	public T data;

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public ResultDataDTO()
	{
	}

	public ResultDataDTO(int result, T data)
	{
		super();
		this.result = result;
		this.data = data;
	}

}
