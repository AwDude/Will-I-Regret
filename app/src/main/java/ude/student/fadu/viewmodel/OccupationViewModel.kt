package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase
import ude.student.fadu.view.fragment.OccupationFragmentDirections

class OccupationViewModel : AViewModel() {

	var occupationLevel = DataBase.getUser().occupationLvl

	val setOccupation = fun(occupation: Float) {
		occupationLevel = occupation.toInt()
	}

	fun onNextClick() {
		DataBase.setOccupation(occupationLevel)
		navigate(OccupationFragmentDirections.actionShowUs())
	}
}