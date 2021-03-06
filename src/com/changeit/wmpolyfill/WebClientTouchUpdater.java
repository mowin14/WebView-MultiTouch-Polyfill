package com.changeit.wmpolyfill;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;
import android.util.Log;
import android.webkit.WebView;

/**
 * Class implements a TimerTask that sends the touches collected by WebClient to the WebApp
 *
 * @author fastrde
 *
 */
public class WebClientTouchUpdater extends TimerTask
{

    private WebView view;
    private WebClient wmp;

    @Override
    /**
     * Firing the touches collected between the last time and now
     */
    public void run()
    {
	ArrayList<String> touches = wmp.getTouches();
	if (touches.size() > 0) {
	    Iterator<String> itr = touches.iterator();
	    Log.d("changeit.wmp", "Sending Batch: ");
	    while (itr.hasNext()) {
		String wmpTouch = itr.next();
		Log.d("changeit.wmp", "Sending " + wmpTouch);
		//TODO: hand over the complete array in _one_ javascript call...
		view.loadUrl("javascript: WMP.polyfill(" + wmpTouch + ");");
	    }
	}
    }

    /**
     * Constructor
     *
     * @param wmp
     * @param view
     */
    public WebClientTouchUpdater(WebClient wmp, WebView view)
    {
	super();
	this.wmp = wmp;
	this.view = view;
    }
}
