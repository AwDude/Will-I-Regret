package ude.student.fadu.viewmodel

import io.realm.Realm
import ude.student.fadu.repo.DataBase
import ude.student.fadu.repo.model.User

class SelectUserViewModel : AViewModel() {

	val users = DataBase.getAllUsers()

	fun onUserSelected(user: User) {
		showToast(user.name)
	}

	fun onNewUserClicked() {
		val user = DataBase.newUser()
		Realm.getDefaultInstance().executeTransaction {
			user.name = "asd" + user.id
		}
	}

	fun onDeleteUser(user: User) = DataBase.deleteUser(user)

}