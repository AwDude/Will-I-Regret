package ude.student.fadu.view.fragment

import ude.student.fadu.R
import ude.student.fadu.BR
import ude.student.fadu.databinding.FragmentSelectUserBinding
import ude.student.fadu.viewmodel.StartViewModel
import ude.student.fadu.databinding.FragmentStartBinding
import ude.student.fadu.viewmodel.SelectUserViewModel

class SelectUserFragment : AFragment<SelectUserViewModel, FragmentSelectUserBinding>() {

	override fun getViewModelClass() = SelectUserViewModel::class
	override fun getLayoutID() = R.layout.fragment_select_user
	override fun getViewModelBindingID() = BR.vm

}
