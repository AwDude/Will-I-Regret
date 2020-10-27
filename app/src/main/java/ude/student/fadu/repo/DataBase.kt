package ude.student.fadu.repo

import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import ude.student.fadu.repo.model.User
import ude.student.fadu.repo.model.User.*
import ude.student.fadu.util.asLive
import ude.student.fadu.util.equalTo
import ude.student.fadu.util.notEqualTo
import ude.student.fadu.util.sort

object DataBase {

	private val realm: Realm get() = Realm.getDefaultInstance()
	private val namedUsersQuery get() = realm.where<User>().notEqualTo(User::name, "")

	fun setGender(gender: Gender) = getUser().apply {
		realm.executeTransaction {
			this.gender = gender
		}
	}

	fun setGender(genderLevel: Int) = getUser().apply {
		realm.executeTransaction {
			this.genderLvl = genderLevel
		}
	}

	fun setAge(age: Age) = getUser().apply {
		realm.executeTransaction {
			this.age = age
		}
	}

	fun setAge(ageLevel: Int) = getUser().apply {
		realm.executeTransaction {
			this.ageLvl = ageLevel
		}
	}

	fun setEducation(education: Education) = getUser().apply {
		realm.executeTransaction {
			this.education = education
		}
	}

	fun setEducation(educationLevel: Int) = getUser().apply {
		realm.executeTransaction {
			this.educationLvl = educationLevel
		}
	}

	fun setOccupation(occupation: Occupation) = getUser().apply {
		realm.executeTransaction {
			this.occupation = occupation
		}
	}

	fun setOccupation(occupationLevel: Int) = getUser().apply {
		realm.executeTransaction {
			this.occupationLvl = occupationLevel
		}
	}

	fun setFromUS(fromUS: Boolean) = getUser().apply {
		realm.executeTransaction {
			this.fromUS = fromUS
		}
	}

	fun setName(name: String): Boolean {
		if (getUser(name) != null) return false
		val user = getUser()
		realm.executeTransaction {
			user.name = name
		}
		return true
	}

	fun getUser(name: String) = realm.where<User>().equalTo(User::name, name).findFirst()

	fun getUser() = DataBase.getUser("") ?: newUser()

	private fun newUser(): User {
		realm.beginTransaction()
		val user = realm.createObject<User>()
		realm.commitTransaction()
		return user
	}

	fun hasUsers() = namedUsersQuery.findAll().isNotEmpty()

	fun getNamedUsers() = namedUsersQuery.sort(User::name).findAllAsync().asLive()

	fun deleteUser(user: User) {
		if (user.isValid) {
			realm.executeTransaction {
				user.deleteFromRealm()
			}
		}
	}

}