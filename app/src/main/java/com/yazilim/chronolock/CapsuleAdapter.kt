package com.yazilim.chronolock

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yazilim.chronolock.databinding.ActivityMainBinding
import com.yazilim.chronolock.databinding.ItemCapsuleBinding

class CapsuleAdapter (private val capsuleList: List<Capsule>) : RecyclerView.Adapter<CapsuleAdapter.CapsuleViewHolder>() {

    class CapsuleViewHolder(val binding: ItemCapsuleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): CapsuleAdapter.CapsuleViewHolder {
        val binding = ItemCapsuleBinding.inflate(LayoutInflater.from(p0.context))
        return CapsuleViewHolder(binding)
    }

    override fun onBindViewHolder(p0: CapsuleAdapter.CapsuleViewHolder, p1: Int) {
        val currentCapsule = capsuleList[p1]

        p0.binding.itemTitle.text = currentCapsule.title
        p0.binding.itemMessage.text = currentCapsule.message
        p0.binding.itemUnlockDate.text = "Açılış Tarihi: \${currentCapsule.unlockDate}"

        //kapsül kilitlimmi açıkmı
        if (currentCapsule.isOpened == 1) {
            p0.binding.itemStatusBadge.text = "AÇILDI"
            p0.binding.itemStatusBadge.setBackgroundColor(Color.parseColor("#4CAF50"))
        } else {
            p0.binding.itemStatusBadge.text = "KİLİTLİ"
            p0.binding.itemStatusBadge.setBackgroundColor(Color.parseColor("#E53935"))
        }
    }

    override fun getItemCount(): Int {
        return capsuleList.size
    }

}