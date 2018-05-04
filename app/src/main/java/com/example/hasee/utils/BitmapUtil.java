package com.example.hasee.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {

	public static byte[] Bitmap2Bytes(Bitmap bm){
		       ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		       return baos.toByteArray();
		    }



	/**
     * 把字节数组保存为一个文件
     * @Author HEH
     * @EditTime 2010-07-19 上午11:45:56
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }




	public static boolean saveImg(Bitmap bitmap, File file)
	{
		try {
			FileOutputStream out = new FileOutputStream(file);
			if(bitmap.compress(Bitmap.CompressFormat.PNG, 100, out))
			{
				out.flush();
				out.close();
			}
			out.flush();
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static Bitmap getBmp(File file) throws FileNotFoundException {

		if (file == null)
			return null;
		Options o = new Options();
		o.inJustDecodeBounds = true;
		Bitmap tmp ;
		o.inJustDecodeBounds = false;

		int rate = (int)(o.outHeight / (float)o.outWidth);
		if (rate <= 0)
		{
			rate = 1;
		}
		o.inSampleSize = rate;
		o.inPurgeable = true;
		o.inInputShareable = true;

		tmp = BitmapFactory.decodeFile(file.getAbsolutePath(), o);

		return tmp;
	}



	/**
	 * use to lessen pic 50%
	 * @param path sd card path
	 * @return bitmap
	 */
	public final static Bitmap lessenUriImage(String path)
	{
		Options options = new Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); //此时返回 bm 为空
		options.inJustDecodeBounds = false; //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = (int)(options.outHeight / (float)320);
		if (be <= 0)
			be = 1;
		options.inSampleSize = be; //重新读入图片，注意此时已经把 options.inJustDecodeBounds 设回 false 了
		bitmap= BitmapFactory.decodeFile(path,options);
		return bitmap;
	}



	/**
	 * 返回圆角图片
	 *
	 * @author 左博云
	 * @param bitmap
	 *            需要转换的位图
	 * @param pixels
	 *            圆角半径
	 * @return
	 * **/
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
		if (bitmap == null) {
			return bitmap;
		}

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static Bitmap rotate(Bitmap bitmap, int degree)
	{
		Matrix matrix = new Matrix();
		matrix.postRotate(degree); /*翻转90度*/
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	}






	/**
	 * 把bitmap转成圆形
	 * */
	public static Bitmap toRoundBitmap(Bitmap bitmap){
		int width=bitmap.getWidth();
		int height=bitmap.getHeight();
		int r=0;
		//取最短边做边长
		if(width<height){
			r=width;
		}else{
			r=height;
		}
		//构建一个bitmap
		Bitmap backgroundBm= Bitmap.createBitmap(width,height, Config.ARGB_8888);
		//new一个Canvas，在backgroundBmp上画图
		Canvas canvas=new Canvas(backgroundBm);
		Paint p=new Paint();
		//设置边缘光滑，去掉锯齿
		p.setAntiAlias(true);
		RectF rect=new RectF(0, 0, r, r);
		//通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
		//且都等于r/2时，画出来的圆角矩形就是圆形
		canvas.drawRoundRect(rect, r/2, r/2, p);
		//设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
		p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		//canvas将bitmap画在backgroundBmp上
		canvas.drawBitmap(bitmap, null, rect, p);
		return backgroundBm;
	}


	/**
	 * 模糊效果
	 * @param bmp
	 * @return
	 */
	public static Bitmap blurImage(Bitmap bmp)
	{
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int newColor = 0;

		int[][] colors = new int[9][3];
		for (int i = 1, length = width - 1; i < length; i++)
		{
			for (int k = 1, len = height - 1; k < len; k++)
			{
				for (int m = 0; m < 9; m++)
				{
					int s = 0;
					int p = 0;
					switch(m)
					{
						case 0:
							s = i - 1;
							p = k - 1;
							break;
						case 1:
							s = i;
							p = k - 1;
							break;
						case 2:
							s = i + 1;
							p = k - 1;
							break;
						case 3:
							s = i + 1;
							p = k;
							break;
						case 4:
							s = i + 1;
							p = k + 1;
							break;
						case 5:
							s = i;
							p = k + 1;
							break;
						case 6:
							s = i - 1;
							p = k + 1;
							break;
						case 7:
							s = i - 1;
							p = k;
							break;
						case 8:
							s = i;
							p = k;
					}
					pixColor = bmp.getPixel(s, p);
					colors[m][0] = Color.red(pixColor);
					colors[m][1] = Color.green(pixColor);
					colors[m][2] = Color.blue(pixColor);
				}

				for (int m = 0; m < 9; m++)
				{
					newR += colors[m][0];
					newG += colors[m][1];
					newB += colors[m][2];
				}

				newR = (int) (newR / 9F);
				newG = (int) (newG / 9F);
				newB = (int) (newB / 9F);

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));

				newColor = Color.argb(255, newR, newG, newB);
				bitmap.setPixel(i, k, newColor);

				newR = 0;
				newG = 0;
				newB = 0;
			}
		}

		return bitmap;
	}

	/**
	 * 柔化效果(高斯模糊)(优化后比上面快三倍)
	 * @param bmp
	 * @return
	 */
	public static Bitmap blurImageAmeliorate(Bitmap bmp)
	{
		long start = System.currentTimeMillis();
		// 高斯矩阵
		int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int delta = 16; // 值越小图片会越亮，越大则越暗

		int idx = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++)
		{
			for (int k = 1, len = width - 1; k < len; k++)
			{
				idx = 0;
				for (int m = -1; m <= 1; m++)
				{
					for (int n = -1; n <= 1; n++)
					{
						pixColor = pixels[(i + m) * width + k + n];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);

						newR = newR + (int) (pixR * gauss[idx]);
						newG = newG + (int) (pixG * gauss[idx]);
						newB = newB + (int) (pixB * gauss[idx]);
						idx++;
					}
				}

				newR /= delta;
				newG /= delta;
				newB /= delta;

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));

				pixels[i * width + k] = Color.argb(255, newR, newG, newB);

				newR = 0;
				newG = 0;
				newB = 0;
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "used time="+(end - start));
		return bitmap;
	}


	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
				: Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}




}
