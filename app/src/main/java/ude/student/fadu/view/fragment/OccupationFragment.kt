package ude.student.fadu.view.fragment

import ude.student.fadu.BR
import ude.student.fadu.R
import ude.student.fadu.databinding.FragmentOccupationBinding
import ude.student.fadu.viewmodel.OccupationViewModel

class OccupationFragment : AFragment<OccupationViewModel, FragmentOccupationBinding>() {

	override fun getViewModelClass() = OccupationViewModel::class
	override fun getLayoutID() = R.layout.fragment_occupation
	override fun getViewModelBindingID() = BR.vm

}
