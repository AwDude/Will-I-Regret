package ude.student.fadu.view.adapter.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ude.student.fadu.repo.LiveList
import ude.student.fadu.view.adapter.recyclerview.RecyclerViewAdapter.BindingHolder

class RecyclerViewAdapter<T>(recyclerView: RecyclerView,
							 private val items: LiveList<T>,
							 private val itemLayout: Int,
							 private val viewModel: ViewModel?,
							 private val viewModelBinding: Int?,
							 private val itemBinding: Int?,
							 private val getItemID: ((T) -> Long)?) : RecyclerView.Adapter<BindingHolder>(),
																	  LiveList.ListObserver {

	init {
		if (getItemID != null) setHasStableIds(true)
	}

	private val antiViewGlitchObserver = object : LiveList.ListObserver {
		override fun notifyDataSetChanged() {}
		override fun notifyItemChanged(position: Int) {}
		override fun notifyItemRangeChanged(positionStart: Int, itemCount: Int) {}
		override fun notifyItemInserted(position: Int) {}
		override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {}
		override fun notifyItemRangeRemoved(positionStart: Int, itemCount: Int) {}
		override fun notifyItemMoved(fromPosition: Int, toPosition: Int) {}
		override fun notifyItemRemoved(position: Int) {
			recyclerView.getChildAt(position)?.run {
				recyclerView.removeViewAt(position)
			}
		}
	}

	override fun getItemId(position: Int) = getItemID?.invoke(items[position]) ?: super.getItemId(position)

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		super.onAttachedToRecyclerView(recyclerView)
		items.observe(recyclerView.context, antiViewGlitchObserver)
		items.observe(recyclerView.context, this)
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		super.onDetachedFromRecyclerView(recyclerView)
		items.stopObserve(this)
		items.stopObserve(antiViewGlitchObserver)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
		val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), itemLayout, parent, false)
		return BindingHolder(binding)
	}

	@SuppressLint("ClickableViewAccessibility")
	override fun onBindViewHolder(holder: BindingHolder, position: Int) {
		if (viewModel != null && viewModelBinding != null) {
			holder.binding.setVariable(viewModelBinding, viewModel)
		}
		if (itemBinding != null) {
			holder.binding.setVariable(itemBinding, items[position])
		}
	}

	override fun getItemCount() = items.size

	class BindingHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}
