package com.kk191rdb020.todo.ui.displayinfo

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DisplayInfoDialogFragment : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Info")
            .setMessage("Klims Klimovs 191RDB020")
            .setNegativeButton("Close", null)
            .create()
}