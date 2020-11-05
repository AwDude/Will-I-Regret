package ude.student.fadu.viewmodel

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ude.student.fadu.repo.DataBase
import ude.student.fadu.repo.model.Topic
import weka.classifiers.Classifier
import weka.classifiers.Evaluation
import java.io.ObjectInputStream

const val CLASSIFIER_FILE = "c45.model"
const val EVALUATION_FILE = "evaluation.model"

class RegretViewModel : AViewModel() {

	@Volatile
	private var classifier: Classifier? = null
	@Volatile
	private var evaluation: Evaluation? = null

	private var classifierJob: Job? = null
	private var evaluationJob: Job? = null

	fun loadData(userName: String, topic: Topic, assets: AssetManager) {
		loadClassifier(assets)
		loadEvaluation(assets)
		val user = DataBase.getUser(userName)
		Log.e("USER", user.toString())
	}

	private fun loadClassifier(assets: AssetManager) {
		if (classifier != null || classifierJob?.isActive == true) return
		classifierJob = viewModelScope.launch(Dispatchers.IO) {
			val obj = ObjectInputStream(assets.open(CLASSIFIER_FILE)).use { it.readObject() }
			if (obj is Classifier) {
				classifier = obj
				Log.e("CLASSIFIER", classifier?.toString() ?: "ERROR")
			}
		}
	}

	private fun loadEvaluation(assets: AssetManager) {
		if (evaluation != null || evaluationJob?.isActive == true) return
		evaluationJob = viewModelScope.launch(Dispatchers.IO) {
			val obj = ObjectInputStream(assets.open(EVALUATION_FILE)).use { it.readObject() }
			if (obj is Evaluation) {
				evaluation = obj
			}
		}
	}

}