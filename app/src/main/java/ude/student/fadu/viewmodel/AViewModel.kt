package ude.student.fadu.viewmodel

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

abstract class AViewModel : ViewModel() {

	open val onKeyboardShown: ((Boolean) -> Unit)? = null
	open val onBackPress: OnBackPressedCallback? = null
	lateinit var pressBack: () -> Unit
	lateinit var navigate: (NavDirections) -> Unit
	lateinit var showToast: (String) -> Unit
}