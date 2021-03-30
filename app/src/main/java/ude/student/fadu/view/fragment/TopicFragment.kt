package ude.student.fadu.view.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import ude.student.fadu.BR
import ude.student.fadu.R
import ude.student.fadu.databinding.FragmentTopicBinding
import ude.student.fadu.viewmodel.TopicViewModel

class TopicFragment : AFragment<TopicViewModel, FragmentTopicBinding>() {

	private val args: TopicFragmentArgs by navArgs()

	override fun getViewModelClass() = TopicViewModel::class
	override fun getLayoutID() = R.layout.fragment_topic
	override fun getViewModelBindingID() = BR.vm

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.userName = args.userName
	}

}
