package willowcheng.postach.io.dailyselfie;

import android.graphics.Bitmap;


public class ItemRecord {
	private Bitmap mPictureBitmap;
	private String mDate;

	public ItemRecord(Bitmap pictureBitmap, String date) {
		this.mPictureBitmap = pictureBitmap;
		this.mDate = date;
	}
	
	public ItemRecord(Bitmap pictureBitmap) {
		this.mPictureBitmap = pictureBitmap;
	}
	
	public ItemRecord() {
		
	}

	public Bitmap getPicture() {
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

}
