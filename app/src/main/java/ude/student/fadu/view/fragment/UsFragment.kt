package ude.student.fadu.view.fragment

import ude.student.fadu.BR
import ude.student.fadu.R
import ude.student.fadu.databinding.FragmentUsBinding
import ude.student.fadu.viewmodel.UsViewModel

class UsFragment : AFragment<UsViewModel, FragmentUsBinding>() {

	override fun getViewModelClass() = UsViewModel::class
	override fun getLayoutID() = R.layout.fragment_us
	override fun getViewModelBindingID() = BR.vm

}
