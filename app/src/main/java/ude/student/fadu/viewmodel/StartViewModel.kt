package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase
import ude.student.fadu.view.fragment.StartFragmentDirections

class StartViewModel : AViewModel() {

	fun onStartClick() {
		if (true){//DataBase.hasUsers()) {
			navigate(StartFragmentDirections.actionShowSelectUser())
		} else {
			navigate(StartFragmentDirections.actionShowGender())
		}
	}
}