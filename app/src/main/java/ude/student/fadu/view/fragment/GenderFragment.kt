package ude.student.fadu.view.fragment

import ude.student.fadu.R
import ude.student.fadu.BR
import ude.student.fadu.databinding.FragmentGenderBinding
import ude.student.fadu.viewmodel.StartViewModel
import ude.student.fadu.databinding.FragmentStartBinding
import ude.student.fadu.viewmodel.GenderViewModel

class GenderFragment : AFragment<GenderViewModel, FragmentGenderBinding>() {

	override fun getViewModelClass() = GenderViewModel::class
	override fun getLayoutID() = R.layout.fragment_gender
	override fun getViewModelBindingID() = BR.vm

}
