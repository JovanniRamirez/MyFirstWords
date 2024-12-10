package com.example.myfirstwords

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.myfirstwords.databinding.FragmentSeventhBinding
import java.util.Locale

class SeventhFragment : Fragment() {
    private lateinit var tts: TextToSpeech
    private var _binding: FragmentSeventhBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Numbers"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using View Binding
        _binding = FragmentSeventhBinding.inflate(inflater, container, false)
        val view = binding.root

        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.US
            }
        }

        val gridLayout = binding.gridLayout

        for (i in 1..100) {
            val button = Button(context)
            button.text = i.toString()
            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            }
            button.layoutParams = params
            button.setOnClickListener { playNumber(i) }
            gridLayout.addView(button)
        }
        return view
    }

    private fun playNumber(number: Int) {
        val text = number.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
    }
}

