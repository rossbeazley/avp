package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 30/01/2014
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public interface InflatedViewFactory {
    InflatedLayoutView createInflatedView(LayoutInflater inflater, ViewGroup container, int layoutId);
}
