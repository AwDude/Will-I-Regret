package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase
import ude.student.fadu.view.fragment.UsFragmentDirections

class UsViewModel : AViewModel() {

	fun onYesClick() {
		DataBase.setFromUS(true)
		navigate(UsFragmentDirections.actionShowSaveUser())
	}

	fun onNoClick() {
		DataBase.setFromUS(false)
		navigate(UsFragmentDirections.actionShowSaveUser())
	}
}