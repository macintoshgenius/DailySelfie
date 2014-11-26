package willowcheng.postach.io.dailyselfie;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.app.ListActivity;

public class ItemListActivity extends ListActivity {

	ItemListAdapter mAdapter;
	String mCurrentPhotoPath;
	File photoFile;

	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final String TAG = "Daily Selfie";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAdapter = new ItemListAdapter(getApplicationContext());
		getListView().setAdapter(mAdapter);
	}

	private void dispatchTakePictureIntent() {
		Log.i(TAG, "Create Picture Intent");
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException ex) {
				// Error occurred while creating the File

			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "On Activity Result");
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			galleryAddPic();
			Log.i(TAG, "Item recorded");
			mAdapter.add(new ItemRecord(mCurrentPhotoPath,
					new SimpleDateFormat("yyyy-MM-d").format(new Date())));

		}
	}

	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}

	// private Bitmap setPic() {
	// // Get the dimensions of the View
	// int targetW = mImageView.getWidth();
	// int targetH = mImageView.getHeight();
	//
	// // Get the dimensions of the bitmap
	// BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	// bmOptions.inJustDecodeBounds = true;
	// BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	// int photoW = bmOptions.outWidth;
	// int photoH = bmOptions.outHeight;
	//
	// // Determine how much to scale down the image
	// int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
	//
	// // Decode the image file into a Bitmap sized to fill the View
	// bmOptions.inJustDecodeBounds = false;
	// bmOptions.inSampleSize = scaleFactor;
	// bmOptions.inPurgeable = true;
	//
	// Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	// return bitmap;
	// }

	private File createImageFile() throws IOException {
		Log.i(TAG, "Create Image File");
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		Log.i(TAG, "TimeStamp: " + timeStamp);
		String imageFileName = "JPEG_" + timeStamp + "_";
		Log.i(TAG, "imageFileName: " + imageFileName);
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		Log.i(TAG, "storageDir: " + storageDir);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);
		Log.i(TAG, "imagePath: " + image.getPath());

		// Save a file: path for use with ACTION_VIEW intents
		mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		Log.i(TAG, "mCurrentPhotoPath: " + mCurrentPhotoPath);
		return image;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_camera) {
			dispatchTakePictureIntent();
		}
		return super.onOptionsItemSelected(item);
	}
}
