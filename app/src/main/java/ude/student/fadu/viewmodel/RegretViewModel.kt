package ude.student.fadu.viewmodel

import android.content.res.AssetManager
import android.os.Handler
import android.util.Log
import androidx.core.os.postDelayed
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ude.student.fadu.R
import ude.student.fadu.repo.DataBase
import ude.student.fadu.repo.model.Topic
import ude.student.fadu.repo.model.User
import weka.classifiers.Classifier
import weka.core.DenseInstance
import weka.core.Instances
import java.io.ObjectInputStream
import java.util.*

const val CLASSIFIER_FILE = "c45.model"
const val HEADER_FILE = "dataset.header"

class RegretViewModel : AViewModel() {

	val animation = MutableLiveData<Int>()
	val text = MutableLiveData(R.string.no_text)
	val textColor = MutableLiveData(R.color.colorNo)

	private var classifier: Classifier? = null
	private var header: Instances? = null

	private var classifierJob: Job? = null
	private var headerJob: Job? = null

	fun loadData(userName: String, topic: Topic, assets: AssetManager) = viewModelScope.launch {
		loadClassifier(assets)
		loadHeader(assets)
		val user = DataBase.getUser(userName)
		try {
			classifierJob?.join()
			headerJob?.join()
		} catch (e: CancellationException) {
			// viewmodel was probably destroyed while loading
		}
		user?.let {
			evaluate(it, topic)
		} ?: Log.e("loadUser", "no user found in database for username: $userName")
	}

	private fun evaluate(user: User, topic: Topic) = classifier?.let { cl ->
		val head = header ?: return@let
		if (head.numAttributes() < 7) return@let

		val instance = DenseInstance(7).apply {
			setValue(head.attribute(0), if (user.fromUS) "yes" else "no")
			setValue(head.attribute(1), user.gender.abbreviation)
			setValue(head.attribute(2), 20.0 + (10 * user.ageLvl)) // TODO fix age data model or user input
			setValue(head.attribute(3), user.education.name.toLowerCase(Locale.ENGLISH))
			setValue(head.attribute(4), user.occupation.name.toLowerCase(Locale.ENGLISH))
			setValue(head.attribute(5), topic.name.toLowerCase(Locale.ENGLISH))
			setDataset(head)
		}
		val prediction = cl.classifyInstance(instance)
		showPrediction(prediction == 0.0) // regret estimation: 0.0 = high, 1.0 = low
	}

	private fun showPrediction(willRegret: Boolean) {
		animation.value = if (willRegret) {
			textColor.value = R.color.colorNo
			R.anim.rotate_down
		} else {
			textColor.value = R.color.colorYes
			R.anim.rotate_up
		}
		Handler().postDelayed(1500) {
			text.value = if (willRegret) {
				R.string.label_regret_yes
			} else {
				R.string.label_regret_not
			}
		}
	}

	private fun loadClassifier(assets: AssetManager) {
		if (classifierJob?.isActive == true || classifier != null) return
		classifierJob = viewModelScope.launch(Dispatchers.IO) {
			val obj = ObjectInputStream(assets.open(CLASSIFIER_FILE)).use { it.readObject() }
			if (obj is Classifier) {
				classifier = obj
			} else {
				Log.e("loadClassifier", "corrupted file")
			}
		}
	}

	private fun loadHeader(assets: AssetManager) {
		if (headerJob?.isActive == true || header != null) return
		headerJob = viewModelScope.launch(Dispatchers.IO) {
			val obj = ObjectInputStream(assets.open(HEADER_FILE)).use { it.readObject() }
			if (obj is Instances) {
				header = obj
			} else {
				Log.e("loadEvaluation", "corrupted file")
			}
		}
	}

}