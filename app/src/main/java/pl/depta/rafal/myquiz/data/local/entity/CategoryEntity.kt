package pl.depta.rafal.myquiz.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class CategoryEntity(
        @PrimaryKey(autoGenerate = true) var id: Long,
        val name: String
)