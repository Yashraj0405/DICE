package com.example.android.navigation.gameFinish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.android.navigation.R

class FragmentThree : Fragment() {

    val args : FragmentThreeArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_three, container, false)

        val rollNumbertext = args.rollNumber

        val rollTextView : TextView = view.findViewById(R.id.LastRollText)
        rollTextView.setText(rollNumbertext)

        val playAgainButton: Button = view.findViewById(R.id.play_again_button)
        playAgainButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentThree_to_fragmentOne)
        }

        return view


    }

}
