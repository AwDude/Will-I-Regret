package ude.student.fadu.viewmodel

import io.realm.Realm
import io.realm.kotlin.where
import ude.student.fadu.repo.DataBase
import ude.student.fadu.repo.model.User

class SelectUserViewModel : AViewModel() {

	val users = DataBase.getAllUsers()

	fun onUserSelected(user: User) {
		showToast(user.name)
	}

	fun onNewUserClicked() {
		val realm = Realm.getDefaultInstance()
		val count = realm.where<User>().count()
		val user = User("User #$count")
		realm.executeTransaction {
			it.insertOrUpdate(user)
		}
	}

	fun onDeleteUser(user: User) = DataBase.deleteUser(user)

}