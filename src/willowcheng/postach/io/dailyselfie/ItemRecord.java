package willowcheng.postach.io.dailyselfie;

import android.graphics.Bitmap;


public class ItemRecord {
	private static Bitmap mPictureBitmap;
	private String mDate;

	public ItemRecord(Bitmap pictureBitmap, String date) {
		this.mPictureBitmap = pictureBitmap;
		this.mDate = date;
	}
	
	public ItemRecord() {
		
	}

	public static Bitmap getPicture() {
		return mPictureBitmap;
	}

	public void setPicture(Bitmap pictureBitmap) {
		this.mPictureBitmap = pictureBitmap;
	}

	public String getDate() {
		return mDate;
	}

	public void setDate(String date) {
		this.mDate = date;
	}

	@Override
	public String toString() {
		// TODO
		//return "Place: " + mPlaceName + " Country: " + mCountryName;
		return " ";
	}
}
