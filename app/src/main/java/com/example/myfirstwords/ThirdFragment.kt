package com.example.myfirstwords

import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.constraintlayout.helper.widget.Grid
import androidx.navigation.Navigation
import com.example.myfirstwords.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private lateinit var tts: TextToSpeech
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "ABC's Alphabet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        val view = binding.root

        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = java.util.Locale.US
            }
        }

        val gridLayout = binding.gridLayout

        val alphabetButtons = listOf(
            "Aa", "Bb", "Cc", "Dd", "Ee", "Ff", "Gg", "Hh", "Ii", "Jj",
            "Kk", "Ll", "Mm", "Nn", "Oo", "Pp", "Qq", "Rr", "Ss", "Tt",
            "Uu", "Vv", "Ww", "Xx", "Yy", "Zz"
        )

        val soundMap = mapOf(
            "Aa" to "Aa is for Apple, ah, ah, apple",
            "Bb" to "B is for Ball, ba, ba, ball",
            "Cc" to "C is for Cat, ca, ca, cat",
            "Dd" to "D is for Dog, da, da, dog",
            "Ee" to "E is for Elephant, eh, eh, elephant",
            "Ff" to "F is for Fish, fa, fa, fish",
            "Gg" to "G is for Girl, ga, ga, girl",
            "Hh" to "H is for Hat, ha, ha, hat",
            "Ii" to "I is for Icecream, i, i, icecream",
            "Jj" to "J is for Jelly, ja, ja, jelly",
            "Kk" to "K is for Kite, ka, ka, kite",
            "Ll" to "L is for Lion, la, la, lion",
            "Mm" to "M is for Monkey, ma, ma, monkey",
            "Nn" to "N is for Nurse, na, na, nurse",
            "Oo" to "O is for Orange, oh, oh, orange",
            "Pp" to "P is for Pen, pa, pa, pen",
            "Qq" to "Q is for Queen, q, q, queen",
            "Rr" to "R is for Rose, ra, ra, rose",
            "Ss" to "S is for Sun, sa, sa, sun",
            "Tt" to "T is for Tiger, ti, ti, tiger",
            "Uu" to "U is for Umbrella, uh, uh, umbrella",
            "Vv" to "V is for Van, va, va, van",
            "Ww" to "W is for Water, wa, wa, water",
            "Xx" to "X is for X-ray, x, x, x-ray",
            "Yy" to "Y is for Yellow, yeh, yeh, yellow",
            "Zz" to "Z is for Zebra, ze, ze, zebra"
        )

        for (text in alphabetButtons) {
            val button = Button(context)
            button.text = text
            button.isAllCaps = false
            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            }
            button.layoutParams = params
            button.setOnClickListener { playSound(text, soundMap) }
            gridLayout.addView(button)

            binding.btnNumbers.setOnClickListener {
                navigateToNumbersFragment()
            }
            binding.btnABCSong.setOnClickListener {
                playABCSong()
            }
            binding.btnWords.setOnClickListener {
                navigateToWordsFragment()
            }
        }
        return view
    }

    private fun playSound(text: String, soundMap: Map<String, String>) {
        val soundText = soundMap[text]
        if (soundText != null) {
            tts.speak(soundText, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun playABCSong() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, R.raw.abcalphabetsong)
        mediaPlayer?.start()
    }

    private fun navigateToNumbersFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_thirdFragment_to_seventhFragment)
    }

    private fun navigateToWordsFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_thirdFragmentABC_to_fourthFragmentWords)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
