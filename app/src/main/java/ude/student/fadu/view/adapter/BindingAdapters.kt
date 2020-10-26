@file:Suppress("unused")

package ude.student.fadu.view.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmQuery
import ude.student.fadu.BR
import ude.student.fadu.repo.LiveList
import ude.student.fadu.view.adapter.recyclerview.RecyclerViewAdapter
import ude.student.fadu.view.component.VerticalSlider
import kotlin.reflect.KProperty

private fun getBindingID(fieldName: String?): Int? = fieldName?.let { field ->
	BR::class.java.getDeclaredField(field).getInt(null)
}

@BindingAdapter("onChange")
fun VerticalSlider.bindOnChange(onChange: (Float) -> Unit) {
	addOnChangeListener { _, value, _ ->
		onChange(value)
	}
}

@BindingAdapter("initial")
fun VerticalSlider.bindInitial(value: Int) {
	setValue(value.toFloat())
}

@BindingAdapter("items",
	"itemLayout",
	"itemBinding",
	"viewModelBinding",
	"viewModel",
	"getItemID",
	requireAll = false)
fun <T> RecyclerView.bindItems(items: LiveList<T>,
							   itemLayout: Int,
							   itemBinding: String?,
							   viewModelBinding: String?,
							   viewModel: ViewModel?,
							   getItemID: ((T) -> Long)?) = adapter ?: run {
	val vmBindingID = getBindingID(viewModelBinding)
	val itemBindingID = getBindingID(itemBinding)
	adapter = RecyclerViewAdapter(this, items, itemLayout, viewModel, vmBindingID, itemBindingID, getItemID)
}