package com.example.myfirstwords

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstwords.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {
    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!
    private var adapter: WordListAdapter? = null
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Words/Phrases"
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = java.util.Locale.US
            }
        }
        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
    }

    private fun clearField() {
        binding.editTextWord.text?.clear()
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val word = binding.editTextWord.text.toString()
            if (word.isNotEmpty()) {
                val word = WordList(word)
                viewModel.insertWord(word)
                clearField()
            }
            else {
                binding.editTextWord.error = "No word"
            }
        }
        binding.findButton.setOnClickListener {
            viewModel.findWord(binding.editTextWord.text.toString())
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteWord(binding.editTextWord.text.toString())
            clearField()
        }
    }

    private fun observerSetup() {
        viewModel.getAllWords()?.observe(viewLifecycleOwner) { words ->
            words?.let {
                adapter?.setWordList(it)
            }
        }
        viewModel.getSearchResults().observe(viewLifecycleOwner) { words ->
            words?.let {
                if (it.isNotEmpty()) {
                    binding.editTextWord.setText(it[0].wordSaved)
                } else {
                    binding.editTextWord.setText("Not found")
                }
            }
        }
    }

    private fun recyclerSetup() {
        adapter = WordListAdapter(R.layout.word_list_item) { word ->
            tts.speak(word.wordSaved, TextToSpeech.QUEUE_FLUSH, null, null)
        }
        binding.wordRecycler.layoutManager = LinearLayoutManager(context)
        binding.wordRecycler.adapter = adapter
    }
}