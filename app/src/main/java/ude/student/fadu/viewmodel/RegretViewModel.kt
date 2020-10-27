package ude.student.fadu.viewmodel

import ude.student.fadu.repo.model.Topic

class RegretViewModel : AViewModel() {

	lateinit var userName: String
	lateinit var topic: Topic

	fun getText() = "$userName ${topic.name}"

}