/*
 * Copyright 2020-2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial
 * Please see LICENSE files in the repository root for full details.
 */
package im.vector.app.features.autocomplete

import android.content.Context
import android.database.DataSetObserver
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otaliastudios.autocomplete.AutocompletePresenter

abstract class RecyclerViewPresenter<T : Any>(context: Context) : AutocompletePresenter<T>(context) {

    private var recyclerView: RecyclerView? = null
    private var clicks: ClickProvider<T>? = null
    private var observer: RecyclerView.AdapterDataObserver? = null

    override fun registerClickProvider(provider: ClickProvider<T>) {
        clicks = provider
    }

    override fun registerDataSetObserver(observer: DataSetObserver) {
        this.observer = Observer(observer)
    }

    @CallSuper
    override fun getView(): ViewGroup {
        val adapter = instantiateAdapter()
        observer?.also {
            adapter.registerAdapterDataObserver(it)
        }
        return RecyclerView(context).apply {
            this.adapter = adapter
            this.layoutManager = instantiateLayoutManager()
            this.itemAnimator = null
        }
    }

    override fun onViewShown() {}

    @CallSuper
    override fun onViewHidden() {
        observer?.also {
            recyclerView?.adapter?.unregisterAdapterDataObserver(it)
        }
        recyclerView = null
        observer = null
    }

    /**
     * Dispatch click event to Autocomplete.Callback.
     * Should be called when items are clicked.
     *
     * @param item the clicked item.
     */
    protected fun dispatchClick(item: T) {
        if (clicks != null) clicks?.click(item)
    }

    /**
     * Request that the popup should recompute its dimensions based on a recent change in
     * the view being displayed.
     *
     * This is already managed internally for [RecyclerView] events.
     * Only use it for changes in other views that you have added to the popup,
     * and only if one of the dimensions for the popup is WRAP_CONTENT .
     */
    protected fun dispatchLayoutChange() {
        if (observer != null) observer!!.onChanged()
    }

    /**
     * Provide an adapter for the recycler.
     * This should be a fresh instance every time this is called.
     *
     * @return a new adapter.
     */
    protected abstract fun instantiateAdapter(): RecyclerView.Adapter<*>

    /**
     * Provides a layout manager for the recycler.
     * This should be a fresh instance every time this is called.
     * Defaults to a vertical LinearLayoutManager, which is guaranteed to work well.
     *
     * @return a new layout manager.
     */
    protected fun instantiateLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private class Observer constructor(private val root: DataSetObserver) : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            root.onChanged()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            root.onChanged()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            root.onChanged()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            root.onChanged()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            root.onChanged()
        }
    }
}
