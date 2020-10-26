package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase
import ude.student.fadu.view.fragment.AgeFragmentDirections

class AgeViewModel : AViewModel() {

	var ageLevel = DataBase.getUser().ageLvl

	val setAge = fun(age: Float) {
		ageLevel = age.toInt()
	}

	fun onNextClick() {
		DataBase.setAge(ageLevel)
		navigate(AgeFragmentDirections.actionShowEducation())
	}
}