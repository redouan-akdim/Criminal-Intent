package com.akdim.criminalintent

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.akdim.criminalintent.databinding.ListItemCrimeBinding
import com.akdim.criminalintent.databinding.ListItemPoliceCrimeBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        //binding.crimeDate.text = crime.date.toString()
        binding.crimeDate.text = DateFormat.format("MMMM dd, yyyy", crime.date).toString()      // Convert timestamp to conventional date

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.crimeSolved.visibility = if (crime.isSolved) View.VISIBLE else View.GONE
    }
}

// Second viewHolder
class PoliceCrimeHolder(
    private val binding: ListItemPoliceCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimePoliceTitle.text = crime.title
        //binding.crimePoliceDate.text = crime.date.toString()
        binding.crimePoliceDate.text = DateFormat.format("MMMM dd, yyyy", crime.date).toString()      // Convert timestamp to conventional date

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : RecyclerView.ViewHolder {

        if (viewType == 0){
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            return CrimeHolder(binding)
        }
        else{
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemPoliceCrimeBinding.inflate(inflater, parent, false)
            return PoliceCrimeHolder(binding)
        }
    }

    // Bind data to the view holder (item of the list)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        /*holder.apply {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
        }*/

        return when (crimes[position].requiresPolice){
            false -> (holder as CrimeHolder).bind(crimes[position])
            true -> (holder as PoliceCrimeHolder).bind(crimes[position])
        }
    }

    override fun getItemCount() = crimes.size

    /**
     * 1 = requires police
     * 0 = doesn't require police
     */
    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) 1 else 0
    }
}
