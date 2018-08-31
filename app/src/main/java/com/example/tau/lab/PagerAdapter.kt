package com.example.tau.lab

import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import com.example.tau.lab.fragments.ListFragment
import com.example.tau.lab.util.Logger
import java.lang.ref.WeakReference
import java.util.HashMap

class PagerAdapter(
        fragmentManager: FragmentManager,
        resources: Resources
) : FragmentStatePagerAdapter(fragmentManager) {

    private val pages: HashMap<Int, WeakReference<Fragment>?> = HashMap()
    private var pageTitles: Array<String> = resources.getStringArray(R.array.page_titles)

    override fun instantiateItem(container: ViewGroup, position: Int): Fragment {
        val fragment = super.instantiateItem(container, position) as Fragment
        if (pages[position] == null) pages[position] = WeakReference(fragment)
        return fragment
    }

    override fun getCount(): Int = PAGES_COUNT

    override fun getItem(position: Int): Fragment? {
        Logger.d(LOG_TAG, "getItem. position = $position")
        when (position) {
            0 -> return ListFragment.newInstance(AnimalType.MAMMAL)
            1 -> return ListFragment.newInstance(AnimalType.BIRD)
        }
        return null
    }

    override fun getPageTitle(position: Int): CharSequence? = pageTitles[position]

    fun getProductsFragment(): ListFragment? {
        return pages[0]?.get() as ListFragment?
    }

    companion object {
        private const val LOG_TAG = "PagerAdapter"
        private const val PAGES_COUNT = 2
    }
}
