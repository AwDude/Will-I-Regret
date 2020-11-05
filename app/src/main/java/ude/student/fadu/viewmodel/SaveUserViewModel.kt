package ude.student.fadu.viewmodel

import androidx.lifecycle.MutableLiveData
import ude.student.fadu.R
import ude.student.fadu.repo.DataBase
import ude.student.fadu.view.fragment.SaveUserFragmentDirections

class SaveUserViewModel : AViewModel() {

	val shrink = MutableLiveData(false)
	val userName = MutableLiveData("")
	val errorText = MutableLiveData(R.string.no_text)
	override val onKeyboardShown: ((Boolean) -> Unit)? = { visible ->
		shrink.value = visible
	}

	fun onSkipClick() {
		navigate(SaveUserFragmentDirections.actionShowTopic())
	}

	fun onSaveClick() {
		val name = userName.value
		when {
			name.isNullOrBlank() -> {
				errorText.value = R.string.error_enter_name
			}
			DataBase.setName(name) -> {
				navigate(SaveUserFragmentDirections.actionShowTopic(name))
			}
			else -> errorText.value = R.string.error_name_in_use
		}
	}

	fun clearError() {
		errorText.value = R.string.no_text
	}

}