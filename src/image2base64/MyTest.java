package image2base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MyTest {
	private static String inputBasePath = "E:\\MONA WOLF-生活回忆\\picture-回忆\\纯白交响曲\\";
	private static String outBasePath = "E:\\";
	private static String fileName = "纯白交响曲-017.jpg";

	public static void main(String[] args) {
		String imagePath = GetImageStr(inputBasePath + fileName);
		saveBase64(outBasePath + "copy", imagePath);
		GenerateImage(outBasePath + "copy" + fileName, imagePath);
	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imagePath
	 *            图片地址
	 * @return base64值
	 */
	private static String GetImageStr(String imagePath) {
		InputStream in = null;
		byte[] data = null;
		/* 读取图片字节数组 */
		try {
			in = new FileInputStream(imagePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* 返回Base64编码过的字节数组字符串 */
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param outPath
	 *            输出地址
	 * @param base64Str
	 *            图片base64字符串
	 */
	private static void GenerateImage(String outPath, String base64Str) {
		/* Base64解码 */
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64Str);
			for (int i = 0; i < b.length; ++i) {
				/* 调整异常数据 */
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			/* 新生成的图片 */
			OutputStream out = new FileOutputStream(outPath);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存base64文本
	 * 
	 * @param outPath
	 *            输出地址
	 * @param base64Str
	 *            图片base64字符串
	 */
	private static void saveBase64(String outPath, String base64Str) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(outPath);
			fw.write(base64Str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}