package com.lanhai.sample.elastric.dto;

import com.lanhai.sample.elastric.util.ExceptionCode;

/**
 * 错误信息
 * 
 * @author lizhao
 *
 */
public class ResultError
{
	int result;// 返回结果

	int code; // 代码

	String message; // 描述

	String cause;// 原因

	public ResultError(int result, ExceptionCode exceptionCode)
	{
		this.result = result;
		this.code = exceptionCode.getCode();
		this.message = exceptionCode.getMessage();
	}

	public ResultError(int result, ExceptionCode exceptionCode, String cause)
	{
		this.result = result;
		this.code = exceptionCode.getCode();
		this.message = exceptionCode.getMessage();
		this.cause = cause;
	}

	public ResultError(int result, int code, String message)
	{
		this.result = result;
		this.code = code;
		this.message = message;
	}

	public ResultError(int result, int code, String message, String cause)
	{
		this.result = result;
		this.code = code;
		this.message = message;
		this.cause = cause;
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getCause()
	{
		return cause;
	}

	public void setCause(String cause)
	{
		this.cause = cause;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}
}
