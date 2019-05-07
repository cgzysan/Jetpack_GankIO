package io.jetpack.ysan.gankio.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat


/**
 * Created by YSAN on 2019-04-27
 */
class BottomNavigationViewBehavior : CoordinatorLayout.Behavior<BottomNavigationViewEx> {
    constructor() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: BottomNavigationViewEx,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: BottomNavigationViewEx,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        Log.i("ysan", "child height = " + child.height.toFloat() + ", translation = " + child.translationY + ", dy = " + dy)
        child.translationY = Math.max(0f, Math.min(child.height.toFloat(), child.translationY + dy))
    }
}