package cn.kingcd.up;

import android.widget.Toast;

/**
 * Toast
 * @author fei
 */
public class T
{

	private T()
	{
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	private static Toast toast;

	public static void staticShowToast( String text) {
		if (toast == null) {
			toast = Toast.makeText(MyApp.getInstance(), text, Toast.LENGTH_LONG);
		}
		toast.setText(text);
		toast.show();
	}



}