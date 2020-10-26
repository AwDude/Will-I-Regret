package ude.student.fadu.repo

import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import ude.student.fadu.repo.model.User
import ude.student.fadu.repo.model.User.*
import ude.student.fadu.util.asLive
import ude.student.fadu.util.equalTo
import ude.student.fadu.util.notEqualTo

object DataBase {

	private val realm: Realm get() = Realm.getDefaultInstance()

	fun setGender(gender: Gender) = realm.executeTransaction {
		getUser().gender = gender
	}

	fun setGender(genderLevel: Int) = realm.executeTransaction {
		getUser().genderLvl = genderLevel
	}

	fun setAge(age: Age) = realm.executeTransaction {
		getUser().age = age
	}

	fun setAge(ageLevel: Int) = realm.executeTransaction {
		getUser().ageLvl = ageLevel
	}

	fun setEducation(education: Education) = realm.executeTransaction {
		getUser().education = education
	}

	fun setEducation(educationLevel: Int) = realm.executeTransaction {
		getUser().educationLvl = educationLevel
	}

	fun setOccupation(occupation: Occupation) = realm.executeTransaction {
		getUser().occupation = occupation
	}

	fun setOccupation(occupationLevel: Int) = realm.executeTransaction {
		getUser().occupationLvl = occupationLevel
	}

	fun setFromUS(fromUS: Boolean) = realm.executeTransaction {
		getUser().fromUS = fromUS
	}

	fun setName(name: String) = realm.executeTransaction {
		getUser().name = name
	}

	fun getUser() = realm.where<User>().equalTo(User::name, "").findFirst() ?: newUser()

	fun newUser(): User {
		realm.beginTransaction()
		val user = realm.createObject<User>()
		realm.commitTransaction()
		return user
	}

	fun hasUsers() = realm.where<User>().notEqualTo(User::name, "").findAll().isNotEmpty()

	fun getAllUsers() = realm.where<User>().findAllAsync().asLive()

	fun deleteUser(user: User) = realm.executeTransaction {
		if (user.isValid) {
			user.deleteFromRealm()
		}
	}

}