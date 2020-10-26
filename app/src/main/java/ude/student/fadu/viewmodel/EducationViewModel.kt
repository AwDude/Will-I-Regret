package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase
import ude.student.fadu.view.fragment.EducationFragmentDirections

class EducationViewModel : AViewModel() {

	var educationLevel = DataBase.getUser().educationLvl

	val setEducation = fun(education: Float) {
		educationLevel = education.toInt()
	}

	fun onNextClick() {
		DataBase.setEducation(educationLevel)
		navigate(EducationFragmentDirections.actionShowOccupation())
	}
}