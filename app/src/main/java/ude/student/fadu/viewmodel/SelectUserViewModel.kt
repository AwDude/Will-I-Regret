package ude.student.fadu.viewmodel

import ude.student.fadu.repo.DataBase
import ude.student.fadu.repo.model.User
import ude.student.fadu.view.fragment.SelectUserFragmentDirections

class SelectUserViewModel : AViewModel() {

	val users = DataBase.getNamedUsers()

	fun onUserSelected(user: User) {
		navigate(SelectUserFragmentDirections.actionShowTopic(user.name))
	}

	fun onNewUserClicked() {
		if (users.isEmpty()) {
			navigate(SelectUserFragmentDirections.actionShowGenderClearBackStack())
		} else {
			navigate(SelectUserFragmentDirections.actionShowGender())
		}
	}

	fun onDeleteUser(user: User) {
		DataBase.deleteUser(user)
	}

}