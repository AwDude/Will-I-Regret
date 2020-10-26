package ude.student.fadu.view.fragment

import ude.student.fadu.R
import ude.student.fadu.BR
import ude.student.fadu.viewmodel.StartViewModel
import ude.student.fadu.databinding.FragmentStartBinding

class StartFragment : AFragment<StartViewModel, FragmentStartBinding>() {

	override fun getViewModelClass() = StartViewModel::class
	override fun getLayoutID() = R.layout.fragment_start
	override fun getViewModelBindingID() = BR.vm

}
