package io.jetpack.ysan.gankio.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import io.jetpack.ysan.gankio.R
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by YSAN on 2019/3/28
 */
class HomeFragment : Fragment() {

    private var mLastNewFragment: LastNewFragment? = null
    private var mCategoryFragment: CategoryFragment? = null
    private var mReadFragment: ReadFragment? = null
    private var mPicFragment: PicFragment? = null
    private var mMoreFragment: MoreFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nv_bottom.enableAnimation(false)
        nv_bottom.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        nv_bottom.isItemHorizontalTranslationEnabled = false
        nv_bottom.setOnNavigationItemSelectedListener {
            switchFragment(it.itemId)
            true
        }
        nv_bottom.currentItem = 0
        switchFragment(R.id.tab_lastnew)
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mLastNewFragment?.let { transaction.hide(it) }
        mCategoryFragment?.let { transaction.hide(it) }
        mReadFragment?.let { transaction.hide(it) }
        mPicFragment?.let { transaction.hide(it) }
        mMoreFragment?.let { transaction.hide(it) }
    }

    /**
     * 切换 fragment
     * @param
     */
    private fun switchFragment(id: Int) {
        val transaction = childFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (id) {
            R.id.tab_lastnew
            -> mLastNewFragment?.let {
                transaction.show(it)
            } ?: LastNewFragment.getInstance().let {
                mLastNewFragment = it
                transaction.add(R.id.fragment_container, it, "lastNew")
            }
            R.id.tab_category
            -> mCategoryFragment?.let {
                transaction.show(it)
            } ?: CategoryFragment.getInstance().let {
                mCategoryFragment = it
                transaction.add(R.id.fragment_container, it, "category")
            }
            R.id.tab_read
            -> mReadFragment?.let {
                transaction.show(it)
            } ?: ReadFragment.getInstance().let {
                mReadFragment = it
                transaction.add(R.id.fragment_container, it, "read")
            }
            R.id.tab_pictures
            -> mPicFragment?.let {
                transaction.show(it)
            } ?: PicFragment.getInstance().let {
                mPicFragment = it
                transaction.add(R.id.fragment_container, it, "pic")
            }
            R.id.tab_more
            -> mMoreFragment?.let {
                transaction.show(it)
            } ?: MoreFragment.getInstance().let {
                mMoreFragment = it
                transaction.add(R.id.fragment_container, it, "more")
            }
        }
        transaction.commitAllowingStateLoss()
    }
}