package ude.student.fadu.view.fragment

import ude.student.fadu.BR
import ude.student.fadu.R
import ude.student.fadu.databinding.FragmentSaveUserBinding
import ude.student.fadu.viewmodel.SaveUserViewModel

class SaveUserFragment : AFragment<SaveUserViewModel, FragmentSaveUserBinding>() {

	override fun getViewModelClass() = SaveUserViewModel::class
	override fun getLayoutID() = R.layout.fragment_save_user
	override fun getViewModelBindingID() = BR.vm

}
