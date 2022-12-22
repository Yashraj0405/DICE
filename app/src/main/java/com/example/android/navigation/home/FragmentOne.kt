package com.example.android.navigation.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.R

class FragmentOne : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Below code is to contain fragment in mainActivity.xml using container and inflate is to inflate the view.
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        // below code is to setOnClickListner on play button to navigation from fragment_one to fragment_two

        val playBotton: Button = view.findViewById(R.id.play_button)
        playBotton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentOne_to_fragmentTwo)
        }

        val enterButton : Button = view.findViewById(R.id.enter_button)
        enterButton.setOnClickListener {
            context?.hideKeyboard(view)
        }

        // To show menu
        setHasOptionsMenu(true)
        return view

    }

    // To inflate overflow menu.
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
        view!!.findNavController())
                ||super.onOptionsItemSelected(item)
    }
    // Function to Hide keyboard
    fun Context.hideKeyboard(view: View) {
        val enterText = view.findViewById<EditText>(R.id.name_text)
        val nameText = view.findViewById<TextView>(R.id.entered_name_text)

        nameText.text = enterText.text
        enterText.visibility = View.GONE
        view.findViewById<Button>(R.id.enter_button).visibility = View.GONE
        nameText.visibility = View.VISIBLE
        Toast.makeText(context, "NAME IS ENTERED", Toast.LENGTH_SHORT).show()

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}