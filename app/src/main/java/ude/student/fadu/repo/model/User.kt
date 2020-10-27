package ude.student.fadu.repo.model

import io.realm.RealmObject

open class User(var name: String = "",
				var genderLvl: Int = 0,
				var ageLvl: Int = 0,
				var educationLvl: Int = 0,
				var occupationLvl: Int = 0,
				var fromUS: Boolean = false) : RealmObject() {

	var gender: Gender
		get() = Gender.values()[genderLvl]
		set(value) {
			genderLvl = value.ordinal
		}

	var age: Age
		get() = Age.values()[ageLvl]
		set(value) {
			ageLvl = value.ordinal
		}

	var education: Education
		get() = Education.values()[educationLvl]
		set(value) {
			educationLvl = value.ordinal
		}

	var occupation: Occupation
		get() = Occupation.values()[occupationLvl]
		set(value) {
			occupationLvl = value.ordinal
		}

	override fun toString() = if (isValid) name else "INVALID"

	enum class Gender {
		MALE, DIVERS, FEMALE
	}

	enum class Age {
		UP_TO_25, FROM_26_TO_35, FROM_36_TO_45, FROM_46_TO_55, OVER_55
	}

	enum class Education {
		GRADUATE, UNDERGRADUATE, COLLEGE, HIGH_SCHOOL, PRIMARY_SCHOOL
	}

	enum class Occupation {
		FULL_TIME, PART_TIME, NOT_SEARCHING, SEARCHING, DISABLED_OR_RETIRED, STUDENT
	}
}