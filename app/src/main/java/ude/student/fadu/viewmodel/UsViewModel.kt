package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase

class UsViewModel : AViewModel() {

	fun onYesClick() {
		DataBase.setFromUS(true)
		val u = DataBase.getUser()
		showToast("${u.gender} ${u.age} ${u.education} ${u.occupation} ${u.fromUS}")
	}

	fun onNoClick() {
		DataBase.setFromUS(false)
		val u = DataBase.getUser()
		showToast("${u.gender} ${u.age} ${u.education} ${u.occupation} ${u.fromUS}")
	}
}