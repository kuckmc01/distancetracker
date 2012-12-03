package edu.uc.ui;

import java.util.ArrayList;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;


public class DistanceTrackerItemizedOverlay extends ItemizedOverlay {

	// create an OverlayItem ArrayList, in which you'll put each of the OverlayItem objects
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	//This constructor defines the default marker for each of the OverlayItem objects
	public DistanceTrackerItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	//This constructor set the ability to handle touch events on the overlay items.
	public DistanceTrackerItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	//this method will unable you to add a new OverlayItem objects to the ArrayList
	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	//override this method to properly read from the ArrayList and return the OverlayItem from the position specified by the given integer.
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	//Override this method to return the current number of items in the arrayList
	@Override
	public int size() {
		return mOverlays.size();
	}

	//Override onTap method to handle the event when the user tapped on an item.
	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}
}
