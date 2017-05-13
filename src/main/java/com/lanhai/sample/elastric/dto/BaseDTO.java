package com.lanhai.sample.elastric.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 基本传输数据
 * <p>
 * 
 * @author Nick
 *
 */
@XmlRootElement
public class BaseDTO
{
	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	int result = 1;

	public BaseDTO(int result)
	{
		super();
		this.result = result;
	}

	public BaseDTO()
	{
		super();
	}

}
