package com.example.employeeapp.ui.employees

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.employeeapp.data.entities.Employee
import com.example.employeeapp.databinding.ItemCharacterBinding

    class EmployeeAdapter(private val listener: EmployeeItemListener) : RecyclerView.Adapter<EmployeeViewHolder>() {

        interface EmployeeItemListener {
            fun onClickedCharacter(employeeId: Int)
        }

        private val items = ArrayList<Employee>()

        fun setItems(items: ArrayList<Employee>) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
            val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return EmployeeViewHolder(binding, listener)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) = holder.bind(items[position])
    }

class EmployeeViewHolder(private val itemBinding: ItemCharacterBinding, private val listener: EmployeeAdapter.EmployeeItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var character: Employee

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Employee) {
        this.character = item
        itemBinding.name.text = item.name
        itemBinding.companyName.text = item.company?.name
        Glide.with(itemBinding.root)
            .load(item.profile_image)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(character.id)
    }
    }

