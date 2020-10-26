package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase
import ude.student.fadu.repo.model.User.Gender.*
import ude.student.fadu.view.fragment.GenderFragmentDirections

class GenderViewModel : AViewModel() {

	fun onMaleClick() {
		DataBase.setGender(MALE)
		navigate(GenderFragmentDirections.actionShowAge())
	}

	fun onDiversClick() {
		DataBase.setGender(DIVERS)
		navigate(GenderFragmentDirections.actionShowAge())
	}

	fun onFemaleClick() {
		DataBase.setGender(FEMALE)
		navigate(GenderFragmentDirections.actionShowAge())
	}
}