package ude.student.fadu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import io.realm.Realm

abstract class AViewModel : ViewModel() {

	lateinit var navigate: ((NavDirections) -> Unit)
	lateinit var showToast: ((String) -> Unit)

}