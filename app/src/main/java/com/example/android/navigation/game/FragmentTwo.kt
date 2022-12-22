package com.example.android.navigation.game

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.android.navigation.R
import com.example.android.navigation.databinding.FragmentTwoBinding
import org.w3c.dom.Text

class FragmentTwo : Fragment() {
    private lateinit var binding : FragmentTwoBinding
    lateinit var viewModel: FragmentTwoViewModel

    lateinit var diceImage: ImageView
    lateinit var evenDiceImage : ImageView
    lateinit var oddDiceImageView: ImageView
    var previousDiceNUmber = 0
    var randomNumberForRandomRoll  = 0



    @SuppressLint("CutPasteId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_two, container, false)

        viewModel = ViewModelProvider(this).get(FragmentTwoViewModel::class.java)


        //binding.RandomRollButton.setOnClickListener{rollDice()}
        val rollButton: Button = view.findViewById(R.id.RandomRoll_Button)
        rollButton.setOnClickListener {
            //Toast.makeText(context, "THIS BUTTON IS TO ROLL DICE RANDOMLY", Toast.LENGTH_SHORT).show()
            rollDice()
        }
        val evenButton: Button = view.findViewById(R.id.Even_Button)
        evenButton.setOnClickListener {
            //Toast.makeText(context, "THIS BUTTON IS TO ROLL DICE IN EVEN ROll", Toast.LENGTH_SHORT).show()
            nonRepeatingNumberEven()
        }
        val oddButton: Button = view.findViewById(R.id.Odd_Button)
        oddButton.setOnClickListener {
            //Toast.makeText(context, "THIS BUTTON IS TO ROLL DICE IN ODD ROLL", Toast.LENGTH_SHORT).show()
            nonRepeatingNumberOdd()
        }


        // below code is to add setClickOnListener on exit button to navigate from fragment_two to fragment_three
        val exitButton: Button = view.findViewById(R.id.Exit_Button)
        val currentRoll  = rollDice()
        exitButton.setOnClickListener {
            val action = FragmentTwoDirections.actionFragmentTwoToFragmentThree(currentRoll.toString())
            Navigation.findNavController(view).navigate(action)
        }




        diceImage = view.findViewById<ImageView>(R.id.dice_image)
        evenDiceImage = view.findViewById<ImageView>(R.id.dice_image)
        oddDiceImageView = view.findViewById<ImageView>(R.id.dice_image)




        return view
    }
    private fun randomNumberGeneratorForRandomRoll():Int{
        val randomInt = java.util.Random().nextInt(6) + 1
        val diceNumber : TextView = view!!.findViewById(R.id.diceNumber)
        diceNumber.text = randomInt.toString()
        return randomInt
    }
    private fun setDiceImageForRandomRoll(randomInt: Int){
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }
    private fun rollDice()  {
        var randomNumberForRandomRoll = randomNumberGeneratorForRandomRoll()
        setDiceImageForRandomRoll(randomNumberForRandomRoll)
    }


    private fun generateRandomNumber():Int{
        val randomNumber = java.util.Random().nextInt(3) + 1
        //val diceNumber : TextView = view!!.findViewById(R.id.diceNumber)
        //diceNumber.text = randomNumber.toString()
        return randomNumber
    }
    private fun setDiceImageEven(randomNumber:Int){
            val nonRepeatingDrawableResource = when(randomNumber){
                1 -> R.drawable.dice_2
                2 -> R.drawable.dice_4
                3 -> R.drawable.dice_6
                else -> randomNumber
            }
            evenDiceImage.setImageResource(nonRepeatingDrawableResource)
        }

    private fun nonRepeatingNumberEven(){

        var setRandomNumber = generateRandomNumber()

        if (setRandomNumber == previousDiceNUmber){

            setRandomNumber= generateRandomNumber()
            setDiceImageEven(setRandomNumber)
        }
        else{
            setDiceImageEven(setRandomNumber)
        }
        previousDiceNUmber = setRandomNumber
    }

    private fun setDiceImageOdd(randomNumber: Int){
        val nonRepeatingDrawableResource = when(randomNumber){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_3
            3 -> R.drawable.dice_5
            else -> randomNumber
        }
        evenDiceImage.setImageResource(nonRepeatingDrawableResource)
    }

    private fun nonRepeatingNumberOdd(){
        var setRandomNumber_odd = generateRandomNumber()

        if ( setRandomNumber_odd == previousDiceNUmber){
             setRandomNumber_odd =generateRandomNumber()
            setDiceImageOdd(setRandomNumber_odd)
        }
        else{
            setDiceImageOdd(setRandomNumber_odd)
        }
        previousDiceNUmber = setRandomNumber_odd
    }


}


