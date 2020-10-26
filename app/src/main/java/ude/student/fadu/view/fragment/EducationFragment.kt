package ude.student.fadu.view.fragment

import ude.student.fadu.BR
import ude.student.fadu.R
import ude.student.fadu.databinding.FragmentEducationBinding
import ude.student.fadu.viewmodel.EducationViewModel

class EducationFragment : AFragment<EducationViewModel, FragmentEducationBinding>() {

	override fun getViewModelClass() = EducationViewModel::class
	override fun getLayoutID() = R.layout.fragment_education
	override fun getViewModelBindingID() = BR.vm

}
