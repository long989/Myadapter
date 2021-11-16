package com.qkl.testlifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

/**
 * @title:
 * @projectName NewDriverSchool
 * @description:
 * @author qiukailong
 * @date 2021/11/2
 */
class CustomDialogFragment:DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_layout_custom,container)
    }
}