package ude.student.fadu.view.fragment

import ude.student.fadu.R
import ude.student.fadu.BR
import ude.student.fadu.databinding.FragmentAgeBinding
import ude.student.fadu.databinding.FragmentGenderBinding
import ude.student.fadu.viewmodel.StartViewModel
import ude.student.fadu.databinding.FragmentStartBinding
import ude.student.fadu.viewmodel.AgeViewModel
import ude.student.fadu.viewmodel.GenderViewModel

class AgeFragment : AFragment<AgeViewModel, FragmentAgeBinding>() {

	override fun getViewModelClass() = AgeViewModel::class
	override fun getLayoutID() = R.layout.fragment_age
	override fun getViewModelBindingID() = BR.vm

}
