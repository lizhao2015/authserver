package com.lanhai.sample.elastric.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mockito.internal.util.io.IOUtil;

import com.google.gson.JsonObject;

/**
 * ES工具类
 * <p>
 * 1.初始化索引文件大ES-cluster
 * <p>
 * 
 * @author Nick
 *
 */
public class ESUtils
{
	public static void main(String[] args)
	{
		traslationJson();

		// 创建索引

	}

	private static void traslationJson()
	{
		FileInputStream is = null;
		Collection<String> readLines = null; // 读取数据行
		try
		{
			is = new FileInputStream("C:/Users/Nick/Desktop/sample_data");
			readLines = IOUtil.readLines(is);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			IOUtil.close(is);
		}

		List<JsonObject> jsonObjects = new ArrayList<JsonObject>();

		// 解析并转换成JSON文件
		for (String line : readLines)
		{
			String[] sourceArray = line.split("\\|");

			// 数据合规性判断
			if (sourceArray.length == 0)
				continue;

			JsonObject jsonObject = new JsonObject();

			// JSON数据拼装
			jsonObject.addProperty("file_name", (sourceArray[0] == null ? "" : sourceArray[0]));
			jsonObject.addProperty("song_name", (sourceArray[0] == null ? "" : sourceArray[1]));
			jsonObject.addProperty("singer_name", (sourceArray[0] == null ? "" : sourceArray[2]));

			jsonObjects.add(jsonObject);
		}

		FileOutputStream output = null;
		try
		{
			File file = new File("C:/Users/Nick/Desktop/es_data_songs.json");

			// 每次重新初始化JSON数据文件
			if (file.exists())
			{
				if (file.delete())
					file.createNewFile();
			}
			else
			{
				file.createNewFile();
			}

			output = new FileOutputStream(file);

			for (int i = 0; i < jsonObjects.size(); i++)
			{
				int n = i + 1; // ID
				StringBuffer buffer = new StringBuffer();
				buffer.append("{ \"create\" : { \"_index\" : \"songs\", \"_type\" : \"song\", \"_id\" : \"" + (n) + "\" }}" + "\r\n");
				buffer.append(jsonObjects.get(i).toString() + "\r\n");
				buffer.append("\r\n");

				output.write(buffer.toString().getBytes(Charset.forName("UTF-8")));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				output.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
