package ude.student.fadu.view.fragment

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat.Type
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ude.student.fadu.util.autoCleared
import ude.student.fadu.viewmodel.AViewModel
import kotlin.reflect.KClass

@Suppress("MemberVisibilityCanBePrivate")
abstract class AFragment<VM : AViewModel, BIND : ViewDataBinding> : Fragment() {

	protected lateinit var viewModel: VM
	protected var binding by autoCleared<BIND>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		viewModel = ViewModelProvider(this).get(getViewModelClass().java)
		viewModel.navigate = findNavController()::navigate
		viewModel.showToast = ::showToast
		viewModel.pressBack = requireActivity()::onBackPressed
		addOnBackPress(viewModel.onBackPress)

		binding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		binding.setVariable(getViewModelBindingID(), viewModel)

		return binding.root
	}

	override fun onResume() = super.onResume().also {
		setOnKeyboardShown(viewModel.onKeyboardShown)
		viewModel.onResume()
	}

	override fun onPause() = super.onPause().also {
		val rootView = activity?.window?.decorView?.rootView ?: return
		ViewCompat.setOnApplyWindowInsetsListener(rootView, null)
	}

	abstract fun getViewModelClass(): KClass<VM>

	abstract fun getViewModelBindingID(): Int

	abstract fun getLayoutID(): Int

	protected fun addOnBackPress(callback: OnBackPressedCallback?) {
		callback ?: return
		activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
	}

	protected fun setOnKeyboardShown(onShown: ((Boolean) -> Unit)?) = onShown?.let {
		val rootView = activity?.window?.decorView?.rootView ?: return@let
		ViewCompat.setOnApplyWindowInsetsListener(rootView) { _, insets ->
			onShown(insets.isVisible(Type.ime()))
			insets.inset(insets.getInsets(Type.navigationBars()))
		}
	}

	protected fun showToast(text: String) {
		if (Looper.myLooper() == null) {
			activity?.runOnUiThread {
				Toast.makeText(activity?.applicationContext, text, Toast.LENGTH_SHORT).show()
			}
		} else {
			Toast.makeText(activity?.applicationContext, text, Toast.LENGTH_SHORT).show()
		}
	}

}
