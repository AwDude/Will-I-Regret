package ude.student.fadu.view.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import ude.student.fadu.BR
import ude.student.fadu.R
import ude.student.fadu.databinding.FragmentRegretBinding
import ude.student.fadu.viewmodel.RegretViewModel

class RegretFragment : AFragment<RegretViewModel, FragmentRegretBinding>() {

	private val args: RegretFragmentArgs by navArgs()

	override fun getViewModelClass() = RegretViewModel::class
	override fun getLayoutID() = R.layout.fragment_regret
	override fun getViewModelBindingID() = BR.vm

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) = super.onViewCreated(view, savedInstanceState).also {
		viewModel.loadData(args.userName, args.topic, requireContext().applicationContext.assets)
	}
}
