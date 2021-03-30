package ude.student.fadu.viewmodel

import androidx.activity.OnBackPressedCallback
import ude.student.fadu.repo.model.Topic
import ude.student.fadu.view.fragment.TopicFragmentDirections

class TopicViewModel : AViewModel() {

	lateinit var userName: String
	var topic = 2
	override val onBackPress = object : OnBackPressedCallback(true) {
		override fun handleOnBackPressed() {
			if (userName.isEmpty()) {
				this.isEnabled = false
				pressBack()
				this.isEnabled = true
			} else {
				navigate(TopicFragmentDirections.actionShowSelectUser())
			}
		}
	}

	val setTopic = fun(topicVal: Float) {
		topic = topicVal.toInt()
	}

	fun onNextClick() {
		navigate(TopicFragmentDirections.actionShowRegret(userName, Topic.values()[topic]))
	}
}